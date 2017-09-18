package com.shiyidaoAndroidApp.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

import com.shiyidaoAndroidApp.uitle.common.StringUtile;


/**
 * 应用信息获取工具类
 * Created by Yang on 15/8/27.
 */
public class AppInfoUtile {

    private Context context;
    private static AppInfoUtile instance;

    private AppInfoUtile(Context context) {
        this.context = context;
    }

    public static AppInfoUtile getInstance(Context context) {
        if (instance == null) {
            instance = new AppInfoUtile(context);
        }
        return instance;
    }

    /**
     * 获取应用版本信息
     *
     * @return VersionName
     * 异常则返回null
     */
    public String getAppVersionCode() {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取IMEI
     *
     * @return
     */
    public String getIMEICode() {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if(StringUtile.isBlank(telephonyManager.getDeviceId())){
            return "000000000000000";
        }
        return telephonyManager.getDeviceId();
    }

    /**
     * 获取应用可占内存大小
     *
     * @return
     */
    public int getMemorySize() {
        return (int) (Runtime.getRuntime().maxMemory() / 1024);
    }

    /**
     * 获取SDK版本号
     *
     * @return
     */
    public int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 判断当前是否有网络
     */
    public boolean hasNetWork() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
            activeNetworkInfo.isAvailable();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取屏幕信息
     *
     * @return
     */
    public WindowManager getWindManager() {
        return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * dp 转 px
     *
     * @param dp
     * @return
     */
    public static int dptopx(Context context, int dp) {
        float sc = context.getResources().getDisplayMetrics().density;
        return (int) (dp * sc + 0.5f);
    }

    /**
     * px 转 dp
     *
     * @param px
     * @return
     */
    public static int pxtodp(Context context, int px) {
        float sc = context.getResources().getDisplayMetrics().density;
        return (int) (px / sc + 0.5f);
    }


}
