package com.liuxue86.ad.utils;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.liuxue86.ad.Constants;
import com.liuxue86.ad.MainActivity;
import com.liuxue86.ad.activity.DesktopAdsActivity;
import com.liuxue86.ad.activity.HeartBeatActivity;
import com.liuxue86.ad.base.MyApplication;
import com.liuxue86.ad.service.TraceService;

/**
 * create by zhangyuanlong on 2020/4/28 15:08
 * Description 桌面弹窗队列逻辑判断
 **/
public class DesktopAdUtil {
    public static void checkDesktopAdList(long aLong, Context context) {

        if (!Constants.APP_STORE_CHANNEL.equals(MyApplication.defaultChannel)
                && TraceService.needDestPop) {
            //桌面广告

            if (MainActivity.startActivityInDeskTop(context)) {
                return;
            }
            //桌面心跳
            if (!DesktopAdsActivity.startActivity(context, false) && aLong % 10 == 0) {
                //mLockerShow = false;
                //以下用于测试app存活时间
                Log.e("YYY","Service 运行中打印一次log");

                HeartBeatActivity.startActivity(context);
            }

            //早上8点到晚上22点，1.4.5干掉了
            //String cunrrentHour = TimeUtils.millis2String(System.currentTimeMillis(), "HH");
            //int result = Integer.parseInt(cunrrentHour);
            //if (result < 8 || result > 22) {
            //    return;
            //}

            //app启动3分钟后弹
            long appStartTime =
                    SPUtils.getInstance().getLong("appStartTime", System.currentTimeMillis());
            if (System.currentTimeMillis() - appStartTime < (Constants.TIME_4_TEST ? 1000 * 10l
                    : 1000 * 60 * 3l)) {
                return;
            }
            //
            //上一次弹窗队列出现的时间，间隔要5分钟
            long lastShowTime = SPUtils.getInstance().getLong("lastShowTime", 0);
            if (System.currentTimeMillis() - lastShowTime < (Constants.TIME_4_TEST ? 1000 * 10l
                    : 1000 * 60 * 5l)) {
                return;
            }

            //当前有桌面弹窗的话，干掉桌面弹窗
            if (!DeskTopUtils.isDeskTop(context)) {

                if (ActivityUtils.getTopActivity()
                                 .getLocalClassName()
                                 .contains("DesktopAdsActivity")) {
                    ActivityUtils.finishActivity(DesktopAdsActivity.class);
                } else {
                    return;
                }
            }

            int isLastShowPowerHigh = SPUtils.getInstance().getInt("isLastShowType", 0);

            //1、高耗电应用
            //2、手机使用次数
            //3、自动加速
            //4、剪切板
            //5、应用存在风险
//            switch (isLastShowPowerHigh) {
//                case 0:
//                    PowerHighActivity.startActivity(context);
//                    break;
//                case 1:
//                    UseCountActivity.startActivity(context);
//                    break;
//                case 2:
//                    SpeedUpActivity.startActivity(context);
//                    break;
//                case 3:
//                    ClipboardActivity.startActivity(context);
//                    break;
//                case 4:
//                    RiskActivity.startActivity(context);
//                    break;
//            }
        }
    }

//    /**
//     * 防止在打开过程中进入了app，导致这个桌面弹窗在app内出现
//     *
//     * @param currentActivity 正在打开的弹窗
//     */
//    public static void checkNeedKillSelf(Activity currentActivity) {
//        if (null != ActivityUtils.getActivityList()) {
//            for (Activity activity : ActivityUtils.getActivityList()) {
//                if (activity.getLocalClassName().contains("Splash") || activity.getLocalClassName()
//                                                                               .contains(
//                                                                                       "MainActivity")) {
//                    BaseActivity baseActivity = (BaseActivity)activity;
//                    if (baseActivity.isForeground4DeskAds) {
//                        LogUtils.e("弹窗自杀");
//                        currentActivity.finish();
//                    }
//                }
//            }
//        }
//    }
//
//    public static void checkNeedKillSelfInSpalsh(Activity currentActivity) {
//        if (null != ActivityUtils.getActivityList()) {
//            for (Activity activity : ActivityUtils.getActivityList()) {
//                if (activity.getLocalClassName().contains("Splash")) {
//                    BaseActivity baseActivity = (BaseActivity)activity;
//                    if (baseActivity.isForeground4DeskAds) {
//                        LogUtils.e("弹窗自杀");
//                        currentActivity.finish();
//                    }
//                }
//            }
//        }
//    }

    /**
     * 抖动动画，eg:startShakeByPropertyAnim(view, 1, 1, 1, 200);
     */
    public static void startShakeByPropertyAnim(View view, float scaleSmall, float scaleLarge,
                                                float shakeDegrees, long duration) {
        if (view == null) {
            return;
        }
        //先变小后变大
        PropertyValuesHolder scaleXValuesHolder =
                PropertyValuesHolder.ofKeyframe(View.SCALE_X, Keyframe.ofFloat(0f, 1.0f),
                                                Keyframe.ofFloat(0.25f, scaleSmall),
                                                Keyframe.ofFloat(0.5f, scaleLarge),
                                                Keyframe.ofFloat(0.75f, scaleLarge),
                                                Keyframe.ofFloat(1.0f, 1.0f));
        PropertyValuesHolder scaleYValuesHolder =
                PropertyValuesHolder.ofKeyframe(View.SCALE_Y, Keyframe.ofFloat(0f, 1.0f),
                                                Keyframe.ofFloat(0.25f, scaleSmall),
                                                Keyframe.ofFloat(0.5f, scaleLarge),
                                                Keyframe.ofFloat(0.75f, scaleLarge),
                                                Keyframe.ofFloat(1.0f, 1.0f));

        //先往左再往右
        PropertyValuesHolder rotateValuesHolder =
                PropertyValuesHolder.ofKeyframe(View.ROTATION, Keyframe.ofFloat(0f, 0f),
                                                Keyframe.ofFloat(0.1f, -shakeDegrees),
                                                Keyframe.ofFloat(0.2f, shakeDegrees),
                                                Keyframe.ofFloat(0.3f, -shakeDegrees),
                                                Keyframe.ofFloat(0.4f, shakeDegrees),
                                                Keyframe.ofFloat(0.5f, -shakeDegrees),
                                                Keyframe.ofFloat(0.6f, shakeDegrees),
                                                Keyframe.ofFloat(0.7f, -shakeDegrees),
                                                Keyframe.ofFloat(0.8f, shakeDegrees),
                                                Keyframe.ofFloat(0.9f, -shakeDegrees),
                                                Keyframe.ofFloat(1.0f, 0f));

        ObjectAnimator objectAnimator =
                ObjectAnimator.ofPropertyValuesHolder(view, scaleXValuesHolder, scaleYValuesHolder,
                                                      rotateValuesHolder);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }
}
