package com.liuxue86.ad.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.liuxue86.ad.Constants;
import com.liuxue86.ad.R;
import com.liuxue86.ad.utils.ActivityUtils;
import com.liuxue86.ad.utils.DeskTopUtils;
import com.liuxue86.ad.utils.DesktopAdUtil;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * create by zhangyuanlong on 2020/4/15 17:20
 * Description 安装卸载app的提示弹窗
 **/
public class PackageActivity extends Activity {

    Timer mTimer;
    private static boolean isShow = false;
    private boolean isEnd = false;

    private volatile long totalTime = 3000l;

    //是否是安装app后弹窗
    boolean isInstall;
    String appName;

    String interactionAdPositionId = "autoclear_ad01";
    String fullAdPositionId = "autoclear_complete_ad01";

    public static void startActivity(Context context, boolean isInstall, String appName) {

        if (DeskTopUtils.isDeskTop(context)) {
            ActivityUtils.finishOtherActivities(PackageActivity.class);
        } else {
            return;
        }


        Intent screenIntent = new Intent();
        screenIntent.setClass(context, PackageActivity.class);
        screenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        screenIntent.putExtra("isInstall", isInstall);
        screenIntent.putExtra("appName", appName);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, screenIntent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public static void startActivityInApp(Context context) {
        Intent screenIntent = new Intent();
        screenIntent.setClass(context, PackageActivity.class);
        screenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        screenIntent.putExtra("isInstall", false);
        screenIntent.putExtra("appName", "");
        screenIntent.putExtra("isInApp", true);
        context.startActivity(screenIntent);

        //1、在应用内卸载软件后，需退出应用弹出
        ActivityUtils.finishOtherActivities(PackageActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locker);
        Log.e("YYY","打开应用");
        isShow = true;

        isInstall = getIntent().getBooleanExtra("isInstall", false);
        appName = getIntent().getStringExtra("appName");

    }
}
