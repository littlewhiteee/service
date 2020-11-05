package com.liuxue86.ad;


import com.liuxue86.ad.base.MyApplication;
import com.xdandroid.hellodaemon.BuildConfig;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String USER_AGREEMENT_URL = "user_agreement_url";

    public static final String APP_STORE_CHANNEL = "store_sjsjyhs";

    public static final String GDT_APPID = "1110465156";

    public static String BASE_URL = BuildConfig.DEBUG ? "https://cleaner-testing.fenbishuo.com"
            : "https://cleaner-testing.fenbishuo.com";

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_CHILD = 1;

    public static boolean ACTION_SCREEN_ON = false;

    public static final int MAIN_INTERACTION_SHOW_TIME = 20000;  //全局定时广告显示时间,单位为毫秒
    public static final int PERFORM_CLEANUP = 1;  //1 执行清理功能

    //定时器是否用测试时间，避免测试的时候消耗太长时间，发布正式版需要修改这个参数
    public static boolean TIME_4_TEST = BuildConfig.DEBUG;

    public static int DESKTOP_INTERVAL_TIME = 30;

    //public static boolean TIME_4_TEST = !BASE_URL.equals("https://cleaner.fenbishuo.com");
    public static String KEY_IS_FIRST_AUTOSCAN =
            "isFirstInCleaner_" + MyApplication.defaultChannel;

    public static String PATH_DATA = "";

    public static String PATH_IMAGE_CACHE = "";

    public static final int UPDATE_IMAGE_MANAGER_ACITON = 0x101;
    public static final int UPDATE_VIDEO_MANAGER_ACITON = 0x102;
    public static final int UPDATE_DOWNLOAD_PROGRESS_ACITON = 0x107;
    public static final int AUTO_SCAN_BACK_ACITON = 0x108;

    public static int DESKTOP_SHOW_COUNT = 0;

    //本次进入app进入过主页没
    public static volatile boolean HAD_SHOW_MAIN = false;
    //以下2个需要SP实现
    //================= sp_key ====================
    public static final String SHAREDPREFERENCES_NAME = "dou_ya_sp";
    public static final String BACK_AD_SHOW_COUNT = "back_ad_show_count";
    //public static int POWER_HIGH_SHOW_COUNT = 0;
    //public static int USE_COUNT_SHOW_COUNT = 0;

    public static final String AD_CHANNEL_CHUANSHANJIA = "chuanshanjia";
    public static final String AD_CHANNEL_GUANGDIANTONG = "guangdiantong";

    //广告code
    public static final String AD_FEEDS_CODE = "945121262";
    public static final String AD_WEBDETAIL_CODE = "945121264";

    public static final String SPLASH_AD_CODE = "887313288";
    public static final String INTERACTION_AD_CODE = "945121184";
    public static final String BACK_AD_CODE = "945135991";
    public static final String BACK_SCAN_DIALOG_AD_CODE = "945135991";
    public static final String BACK_SCAN_DIALOG_AD_FULL_CODE = "945124995";

    public static final String CLEANER_BANNER1_AD_CODE = "945121673";
    public static final String CLEANER_BANNER2_AD_CODE = "945133311";
    public static final String CLEANER_BANNER3_AD_CODE = "945133313";
    public static final String CLEANER_BANNER4_AD_CODE = "945125001";
    public static final String CLEANER_BANNER5_AD_CODE = "945121673";
    public static final String CLEANER_BANNER6_AD_CODE = "945133311";

    public static final String CLEANER_BANNER1_GUANG_AD_CODE = "1031311143806241";
    public static final String CLEANER_BANNER2_GUANG_AD_CODE = "5071814143407243";
    public static final String CLEANER_BANNER3_GUANG_AD_CODE = "1001010153308215";
    public static final String CLEANER_BANNER4_GUANG_AD_CODE = "1071115143108206";
    public static final String CLEANER_BANNER5_GUANG_AD_CODE = "6041516173408278";
    public static final String CLEANER_BANNER6_GUANG_AD_CODE = "1091811133702340";

    public static final String AD_LOCKER1_CODE = "945121673";
    public static final String AD_LOCKER2_CODE = "945133311";
    public static final String AD_LOCKER3_CODE = "945133313";
    public static final String AD_LOCKER4_CODE = "945121677";

    public static final String AD_LOCKER1_GUANG_CODE = "9001519143800188";
    public static final String AD_LOCKER2_GUANG_CODE = "1001218048902979";
    public static final String AD_LOCKER3_GUANG_CODE = "9041615163400109";
    public static final String AD_LOCKER4_GUANG_CODE = "6081917038115171";

    public static final String MAIN_INTERACTION_AD_CODE = "945121184";
    public static final String APP_UNINSTALL_INTERACTION_AD_CODE = "945134611";
    public static final String APP_UNINSTALL_FULL_AD_CODE = "945134606";
    public static final String CLEANER_VIRUS_FULL1_AD_CODE = "945124994";
    public static final String CLEANER_VIRUS_FULL2_AD_CODE = "945124995";
    public static final String CLEANER_VIRUS_INTERACTION_AD_CODE = "945124996";

    public static final String STORAGE_SPACE_FULL_AD_CODE = "945121198";

    public static final String STORAGE_SPACE_BANNER1_AD_CODE = "945133313";
    public static final String STORAGE_SPACE_BANNER2_AD_CODE = "945125001";
    public static final String STORAGE_SPACE_BANNER3_AD_CODE = "945121673";

    public static final String CLEANER_FILES_FULL1_AD_CODE = "945121250";
    public static final String CLEANER_FILES_FULL2_AD_CODE = "945121254";
    public static final String CLEANER_FILES_INTERACTION_AD_CODE = "945121446";
    public static final String CLEANER_FILES_INTERACTION_FRIST_AD_CODE = "945134621";

    public static final String CLEANER_WECHAT_FULL1_AD_CODE = "945121256";
    public static final String CLEANER_WECHAT_FULL2_AD_CODE = "945121258";
    public static final String CLEANER_WECHAT_INTERACTION_AD_CODE = "945121446";

    public static final String CLEANER_PROCESS_FULL1_AD_CODE = "945121261";
    public static final String CLEANER_PROCESS_FULL2_AD_CODE = "945125007";
    public static final String CLEANER_PROCESS_INTERACTION_AD_CODE = "945121446";
    public static final String CLEANER_PROCESS_INTERACTION_DETAIL_AD_CODE = "945124996";

    public static final String CLEANER_IMAGES_FULL1_AD_CODE = "945124997";
    public static final String CLEANER_IMAGES_FULL2_AD_CODE = "945124999";
    public static final String CLEANER_IMAGES_BANNER_AD_CODE = "945134682";
    public static final String CLEANER_IMAGES_INTERACTION_AD_CODE = "945125000";

    public static final String CLEANER_VIDEOS_FULL1_AD_CODE = "945134613";
    public static final String CLEANER_VIDEOS_FULL2_AD_CODE = "945121198";
    public static final String CLEANER_VIDEOS_BANNER_AD_CODE = "945134682";
    public static final String CLEANER_VIDEOS_INTERACTION_AD_CODE = "945134621";

    public static final String CLEANER_AUDIOS_FULL1_AD_CODE = "945134606";
    public static final String CLEANER_AUDIOS_FULL2_AD_CODE = "945134613";
    public static final String CLEANER_AUDIOS_BANNER_AD_CODE = "945134682";
    public static final String CLEANER_AUDIOS_INTERACTION_AD_CODE = "945125000";

    public static final String CLEANER_DOCS_FULL1_AD_CODE = "945134606";
    public static final String CLEANER_DOCS_FULL2_AD_CODE = "945134613";
    public static final String CLEANER_DOCS_BANNER_AD_CODE = "945134682";
    public static final String CLEANER_DOCS_INTERACTION_AD_CODE = "945125000";

    public static final String CLEANER_ZIPS_FULL1_AD_CODE = "945134606";
    public static final String CLEANER_ZIPS_FULL2_AD_CODE = "945134613";
    public static final String CLEANER_ZIPS_BANNER_AD_CODE = "945134682";
    public static final String CLEANER_ZIPS_INTERACTION_AD_CODE = "945125000";

    public static final String CLEANER_APKS_FULL1_AD_CODE = "945134606";
    public static final String CLEANER_APKS_FULL2_AD_CODE = "945134613";
    public static final String CLEANER_APKS_BANNER_AD_CODE = "945134682";
    public static final String CLEANER_APKS_INTERACTION_AD_CODE = "945125000";

    public static final String CLEANER_FOLDER_FULL1_AD_CODE = "945134606";
    public static final String CLEANER_FOLDER_FULL2_AD_CODE = "945124999";
    public static final String CLEANER_FOLDER_INTERACTION_AD_CODE = "945121677";

    public static final String AUTO_SCAN_DIALOG_AD_CODE = "945125001";
    public static final String AUTO_SCAN_INTERACTION_CODE = "945125003";
    public static final String AUTO_SCAN_EXIT_AD_CODE = "945125005";
    public static final String AUTO_SCAN_END_AD_CODE = "945125007";
    public static final String AUTO_SCAN_INTERACTION_CODE5 = "945121446";

    public static final String CLEANER_ENCOURAGE_INTERACTION_AD_CODE = "945135788";
    public static final String CLEANER_ENCOURAGE_FULL1_AD_CODE = "945121258";
    public static final String CLEANER_ENCOURAGE_FULL2_AD_CODE = "945125007";

    public final static String MONITOR_TIMEOUT = "monitor timeout";
    public final static String MONITOR_TIME_COUNT = "monitor_time_count";

    public final static String USE_COUNT_INTERACTION_AD_CODE = "945135788";

    public static final String RISK_OP_INTERACTION_CODE1 = "945125000";
    public static final String RISK_OP_INTERACTION_CODE2 = "945121677";
    public static final String RISK_OP_INTERACTION_CODE3 = "945133313";
    public static final String RISK_OP_FULL_CODE1 = "945121258";
    public static final String RISK_OP_FULL_CODE2 = "945121258";
    public static final String RISK_FULL_CODE1 = "945121261";

    public static final String PACKAGE_INTERACTION_CODE = "945121673";
    public static final String PACKAGE_FULL_CODE = "945125007";

    public static final String PACKAGE_OP_INTERACTION_CODE1 = "945135788";
    public static final String PACKAGE_OP_INTERACTION_CODE = "945125000";
    public static final String PACKAGE_OP_FULL_CODE1 = "945121250";
    public static final String PACKAGE_OP_FULL_CODE2 = "945121261";

    public final static List<String> DESKTOP_AD_LIST =
            Arrays.asList("6011917193408105", "945121184", "9031916123209106", "945124996",
                          "1001218048902979", "945135788", "8041711008443980", "945125000");

    public final static List<String> AUTOSCAN_LIST1 =
            Arrays.asList("1081717153507352", "945125003", "7031513008810220", "945124996",
                          "1091314103903354", "945125587", "8031515038017233", "945121446",
                          "7051511173101356", "945125000", "7001319048016225", "945134621",
                          "7031011143902387", "945124996", "1041818048516277", "945121677",
                          "3071416123308349", "945121446", "5011413068819370", "945134611",
                          "9011814123204490", "945134621", "2081219068418391", "945125003",
                          "1051717113100492", "945121677", "7061012018614374", "945125587",
                          "6081615183901434", "945134611", "2011611038411345", "945125000",
                          "4031513123404406", "945125003", "3061718058512366", "945121677",
                          "4061714163501438", "945125587", "8041910048615387", "945134611",
                          "9091313163109520", "945125000", "1041211098516490", "945134621");
    public final static List<String> AUTOSCAN_LIST1_END =
            Arrays.asList("3061718058512366", "945121677");

    public final static List<String> AUTOSCAN_LIST2 =
            Arrays.asList("9031211123202532", "945125003", "4031612068212401", "945121677",
                          "9031014113501503", "945125587", "5091316058010433", "945134611",
                          "1081718113906565", "945125000", "7061118068417405", "945134621",
                          "7061319183203556", "945124996", "7041913068616427", "945121677",
                          "9071211153703518", "945121446", "5021417078319530", "945125000",
                          "6051911193302569", "945134621", "2091512058414583", "945124996");

    public final static List<String> AUTOSCAN_LIST2_END =
            Arrays.asList("4031513123404406", "945125003");

    public static final String CLIPBOARD_FULL_AD1 = "945121261";
    public static final String CLIPBOARD_FULL_AD2 = "945121258";
    public static final String CLIPBOARD_FULL_AD3 = "945121258";
    public static final String CLIPBOARD_INTERACTION_AD1 = "945133311";
    public static final String CLIPBOARD_INTERACTION_AD2 = "945125000";

    public static final String SpeedUp_FULL_AD1 = "945125007";
    public static final String SpeedUp_FULL_AD2 = "945125007";
    public static final String SpeedUp_INTERACTION_AD1 = "945121673";
    public static final String SpeedUp_INTERACTION_AD2 = "945124996";
    public static final String SpeedUp_INTERACTION_AD3 = "945124996";

    public static final String SOC_FULL_AD1 = "945125007";
    public static final String SOC_INTERACTION_AD1 = "945121673";
}
