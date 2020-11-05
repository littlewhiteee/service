package com.liuxue86.ad.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import com.liuxue86.ad.Constants;
import com.liuxue86.ad.service.LockerService;
import com.liuxue86.ad.service.TraceService;
import com.liuxue86.ad.utils.SPUtils;
import com.xdandroid.hellodaemon.DaemonEnv;

import java.io.File;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyApplication extends Application {
    //默认渠道
    public static String defaultChannel = "sjsjyhs";
    public static String myAppId = "shoujishujuyouhuashi";
    public static MyApplication instance;
    public static volatile boolean isLoadSplashAgain = false;
    private Reference<Activity> mCurrentActivity;

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Constants.PATH_DATA = getExternalCacheDir().getAbsolutePath() + File.separator + "data/";

        Constants.PATH_IMAGE_CACHE = Constants.PATH_DATA + "image/";

        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(),
                                                                             PackageManager.GET_META_DATA);
            if (appInfo != null && appInfo.metaData != null) {
                defaultChannel = appInfo.metaData.getString("UMENG_CHANNEL");
                //                defaultChannel = Constants.APP_STORE_CHANNEL;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //锁屏场景demo相关配置  纯净版不启动服务
        if (!Constants.APP_STORE_CHANNEL.equals(MyApplication.defaultChannel)) {
            LockerService.startService(this);
            DaemonEnv.initialize(this, TraceService.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
            TraceService.sShouldStopService = false;
            DaemonEnv.startServiceMayBind(TraceService.class);
        }

        SPUtils.getInstance().put("appStartTime", System.currentTimeMillis());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName(this);
            String packageName = this.getPackageName();
            if (!packageName.equals(processName)) {
                WebView.setDataDirectorySuffix(processName);
            }
        }
    }

    /**
     * 当前显示的activity
     */
    public Activity getCurrentActivity() {
        if (mCurrentActivity != null) {
            return mCurrentActivity.get();
        }
        return null;
    }

    public void setCurrentActivity(@NonNull Activity mCurrentActivity) {
        this.mCurrentActivity = new WeakReference<>(mCurrentActivity);
    }


    private String getProcessName(Context context) {
        if (context == null) return null;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }
}
