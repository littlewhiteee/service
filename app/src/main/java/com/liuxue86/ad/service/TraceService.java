package com.liuxue86.ad.service;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.liuxue86.ad.Constants;
import com.liuxue86.ad.activity.LockerActivity;
import com.liuxue86.ad.utils.ActivityForeground;
import com.liuxue86.ad.utils.DesktopAdUtil;
import com.xdandroid.hellodaemon.AbsWorkService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class TraceService extends AbsWorkService {

    //是否 任务完成, 不再需要服务运行?
    public static boolean sShouldStopService;
    public static Disposable sDisposable;
    public static volatile boolean mLockerShow = false;

    //从app返回桌面的时候置true，消耗一次置false
    public static volatile boolean lastOnTop = false;
    public static volatile boolean backToTop = false;

    //是否需要桌面弹窗
    public static volatile boolean needDestPop = true;

    public static void stopService() {
        //我们现在不再需要服务运行了, 将标志位置为 true
        sShouldStopService = true;
        //取消对任务的订阅
        if (sDisposable != null) {
            sDisposable.dispose();
        }
        //取消 Job / Alarm / Subscription
        cancelJobAlarmSub();
    }

    /**
     * 是否 任务完成, 不再需要服务运行?
     *
     * @return 应当停止服务, true; 应当启动服务, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        return sShouldStopService;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {
        sDisposable = Flowable.interval(1, TimeUnit.SECONDS)
                .onBackpressureLatest()//保证最后一个的接收
                //取消任务时取消定时唤醒
                .doOnCancel(new Action() {
                    @Override
                    public void run() throws Exception {
                        cancelJobAlarmSub();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    Log.e("YYY", "服务运行异常");
                })
                .subscribe(aLong -> {
                    Log.e("YYY", "服务运行中...");
                    try {
                        PowerManager pm =
                                (PowerManager) getBaseContext().getSystemService(
                                        Context.POWER_SERVICE);

                        KeyguardManager mKeyguardManager =
                                (KeyguardManager) getBaseContext().getSystemService(
                                        Context.KEYGUARD_SERVICE);
                        boolean flag =
                                mKeyguardManager.inKeyguardRestrictedInputMode();
                        if (flag && !mLockerShow && !ActivityForeground.isForeground(
                                getBaseContext(), LockerActivity.class.getName())) {
                            //moveAppToForeground(getBaseContext());
                            LockerActivity.startActivity(getBaseContext());
                        }
                        boolean isScreenOn = pm.isScreenOn();

                        //LogUtils.e("TraceService.needDestPop = " + needDestPop);

                        //以下为桌面弹窗队列的判断逻辑
                        DesktopAdUtil.checkDesktopAdList(aLong, getBaseContext());
                    } catch (Exception localException) {
                        localException.printStackTrace();
                    }
                });

        registerLockerReceiver();
        registerPackageReceiver();
    }


    @Override
    public void onDestroy() {
        if (!"https://cleaner.fenbishuo.com".equals(Constants.BASE_URL)) {
            Toast.makeText(getApplicationContext(), "服务被无情的杀掉", Toast.LENGTH_LONG).show();
        }
        super.onDestroy();
    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {
        unregisterLockerReceiver();
        unregisterPackageReceiver();
        stopService();
    }

    /**
     * 任务是否正在运行?
     *
     * @return 任务正在运行, true; 任务当前不在运行, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        //若还没有取消订阅, 就说明任务仍在运行.
        return sDisposable != null && !sDisposable.isDisposed();
    }

    @Override
    public IBinder onBind(Intent intent, Void v) {
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {
        //System.out.println("保存数据到磁盘。");
    }

    private LockerReceiver lockerReceiver;

    private PackageBroadCastReceiver mPackageReceiver;

    private void registerLockerReceiver() {
        if (lockerReceiver != null) {
            return;
        }
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);

        lockerReceiver = new LockerReceiver();
        registerReceiver(lockerReceiver, filter);
    }

    private void registerPackageReceiver() {
        if (mPackageReceiver != null) {
            return;
        }
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        filter.addDataScheme("package");
        mPackageReceiver = new PackageBroadCastReceiver();
        registerReceiver(mPackageReceiver, filter);
    }

    private void unregisterPackageReceiver() {
        if (mPackageReceiver == null) {
            return;
        }
        unregisterReceiver(mPackageReceiver);
        mPackageReceiver = null;
    }

    private void unregisterLockerReceiver() {
        if (lockerReceiver == null) {
            return;
        }
        unregisterReceiver(lockerReceiver);
        lockerReceiver = null;
    }

    private class LockerReceiver extends BroadcastReceiver {

        public LockerReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("YYY", "收到广播");
            String action = intent.getAction();

        }
    }
}