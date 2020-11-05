package com.liuxue86.ad.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.liuxue86.ad.R;
import com.liuxue86.ad.utils.DeskTopUtils;

/**
 * create by zhangyuanlong on 2020/4/15 17:20
 * Description 心跳dialog
 **/
public class HeartBeatActivity extends Activity {

    /**
     * 弹出逻辑
     * <p>
     * 1、每天8点-22点，随机弹4次，每次弹出的间隔不小于1小时即可~
     * <p>
     * 跟高耗电弹窗一致，但是在高耗电弹窗次数用完之后才弹
     */
    public static void startActivity(Context context) {
        if (!DeskTopUtils.isDeskTop(context)) {
            return;
        }
        //ActivityUtils.finishOtherActivities(HeartBeatActivity.class);
        //LogUtils.e("DesktopAdsActivity show");
        Intent screenIntent = new Intent();
        screenIntent.setClass(context, HeartBeatActivity.class);
        screenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, screenIntent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_beat);
        Log.e("YYY", "创建heartBeat");

    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
