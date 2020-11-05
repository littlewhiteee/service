package com.liuxue86.ad.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.liuxue86.ad.activity.PackageActivity;


public class PackageBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_ADDED)) {
            // 应用安装
            // 获取应用包名，和要监听的应用包名做对比
            String packName = intent.getData().getSchemeSpecificPart();
            Log.d("packName", "packName");
            PackageActivity.startActivity(context, true, getAppName(context, packName));
        } else if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_REPLACED)) {
            // 应用覆盖
            // 获取应用包名
            String packName = intent.getData().getSchemeSpecificPart();
            PackageActivity.startActivity(context, true, getAppName(context, packName));
            Log.d("packName", "packName");
        } else if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_REMOVED)) {
            // 应用卸载
            // 获取应用包名
            String packName = intent.getData().getSchemeSpecificPart();
            Log.d("packName", "packName");

            PackageActivity.startActivity(context, false, null);
        }
    }

    /**
     * @return 获取app名字，卸载的时候获取不到
     */
    private String getAppName(Context context, String packageName) {
        PackageInfo info = null;
        PackageManager pm = context.getPackageManager();
        try {
            info = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return info.applicationInfo.loadLabel(pm).toString();
    }
}