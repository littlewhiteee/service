package com.liuxue86.ad.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.liuxue86.ad.service.TraceService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;


/**
 * Start
 * <p/>
 * User:Rocky(email:1247106107@qq.com)
 * Created by Rocky on 2017/09/17  16:49
 * PACKAGE_NAME com.eagle.locker.activity
 * PROJECT_NAME LockerScreen
 * TODO:
 * Description:
 * <p/>
 * Done
 */
public class LockerActivity extends Activity {

    private View mChargeContainer;

    private TextView mLockTime, mLockDate, mChargePercent;
    private ImageView mBatteryIcon;

    private View mContainerView;

    RelativeLayout mSlideContainer;
    private int count = 1;

    private Calendar calendar = GregorianCalendar.getInstance();
    private SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MMM d", Locale.getDefault());
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int expressWidth;
    private boolean isNeedInTaskStack;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isNeedInTaskStack = false;
        super.onCreate(savedInstanceState);
    }


    public static void startActivity(Context context) {
        TraceService.mLockerShow = true;
        Intent screenIntent = getIntent(context);
        //context.startActivity(screenIntent);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, screenIntent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
    @NonNull
    private static Intent getIntent(Context context) {
        Intent screenIntent = new Intent();
        screenIntent.setClass(context, LockerActivity.class);
        screenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        screenIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        screenIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        return screenIntent;
    }
}
