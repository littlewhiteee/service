package com.liuxue86.ad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.liuxue86.ad.activity.PackageActivity;
import com.liuxue86.ad.utils.ActivityUtils;
import com.liuxue86.ad.utils.DeskTopUtils;
import com.liuxue86.ad.utils.SPUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static boolean startActivityInDeskTop(Context context) {
        if (!DeskTopUtils.isDeskTop(context)) {
            return false;
        }

        //判断是否到了加载时间，判断是否按了home键
        long lastAutoScanTime = SPUtils.getInstance().getLong("lastAutoScanTime", 0);

        if (System.currentTimeMillis() - lastAutoScanTime < (Constants.TIME_4_TEST ?
                1000
                        * 60
                        * 10 : 1000 * 3600)) {
            return false;
        }

        ActivityUtils.finishAllActivities();

        Intent screenIntent = new Intent();
        screenIntent.setClass(context, PackageActivity.class);
        screenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        screenIntent.putExtra("isFromDesk", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, screenIntent, 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return true;
    }
}