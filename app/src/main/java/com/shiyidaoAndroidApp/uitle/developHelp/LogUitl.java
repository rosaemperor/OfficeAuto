package com.shiyidaoAndroidApp.uitle.developHelp;

import android.util.Log;

/**
 * Log工具类<br>
 *    可通过修改内 参数进设定是否显示log和需要显示的log内容
 */
public class LogUitl {
    /**
     * 是否打印Log日志
     */
    private static boolean isShowLog = true;

    /**
     * 当前展示内容最低级别\n
     *      0 ： 展示所有\n
     *      1 ： 展示级别为1与1以上内容
     */
    private static int showIndex = 0;

    /**
     * 展示级别为1
     */
    public static void D(String TAG, String str){
        if (isShowLog && showIndex <=1){
            Log.d(TAG,str);
        }
    }

    /**
     * 展示级别为5
     */
    public static  void  E(String TAG, String str){
        if (isShowLog && showIndex <= 5){
            Log.e(TAG,str);
        }
    }
}
