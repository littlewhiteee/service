package com.liuxue86.ad.activity;

import android.app.Instrumentation;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;


import com.liuxue86.ad.Constants;
import com.liuxue86.ad.MainActivity;
import com.liuxue86.ad.R;
import com.liuxue86.ad.service.TraceService;
import com.liuxue86.ad.utils.ActivityUtils;
import com.liuxue86.ad.utils.DeskTopUtils;
import com.liuxue86.ad.utils.DesktopAdUtil;
import com.liuxue86.ad.utils.SPUtils;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * create by zhangyuanlong on 2020/4/15 17:20
 * Description 桌面广告页面
 **/
public class DesktopAdsActivity extends AppCompatActivity {

    private boolean isPreloadVideo = true;

    protected AppCompatActivity activity;
    protected Context context;

    ImageView iv_colse;
    FrameLayout rl_desktop;

    String positionId;

    public static boolean startActivity(Context context, boolean isUnlock) {
        if (!DeskTopUtils.isDeskTop(context)) {
            return false;
        }

        if (!TraceService.lastOnTop) {
            Log.e("YYY", "lastOnTop = false");
            TraceService.backToTop = true;
        }
        TraceService.lastOnTop = true;

        //解锁和从app返回桌面，立刻弹桌面广告
        if (!isUnlock && !TraceService.backToTop) {
            long lastDeskTopTime = SPUtils.getInstance().getLong("lastDeskTopTime", 0);
            if (System.currentTimeMillis() - lastDeskTopTime < (Constants.TIME_4_TEST ? 1000 * 15
                    : 1000 * Constants.DESKTOP_INTERVAL_TIME)) {
                return false;
            }
        }

        Log.e("YYY", "startActivity Constants.DESKTOP_INTERVAL_TIME = " + Constants.DESKTOP_INTERVAL_TIME);
        TraceService.backToTop = false;
        ActivityUtils.finishActivity(MainActivity.class);
        Intent screenIntent = new Intent();
        screenIntent.setClass(context, DesktopAdsActivity.class);
        screenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, screenIntent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop_ads);
        //setStatusBarBackgroundTrans();
        activity = this;
        context = getApplicationContext();
        iv_colse = findViewById(R.id.iv_colse);
        rl_desktop = findViewById(R.id.rl_desktop);
        //解锁进入桌面立即弹出，间隔（30s-180s）随机弹出下一次
        //从APP返回桌面立即弹出，间隔（（30s-180s）随机弹出下一次
        Constants.DESKTOP_INTERVAL_TIME = new Random().nextInt(150) + 30;
        startShowAd();
        iv_colse.setOnClickListener((View v) -> {
            finish();
        });
    }

    private void startShowAd() {
        Log.e("YYY","弹出广告");
    }
}