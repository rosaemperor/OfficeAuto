package com.shiyidaoAndroidApp.config;

import android.os.Environment;


/**
 * 一些基本公用设置参数
 * Created by DYang on 15/8/25.
 */
public class AppCongif {
    /**
     * 存储文件的根目录文件名
     */
    public final static String FILE_PATH_ROOT = "/shiyidao/";//
    //存储下载文件的文件盒子
    public final static String FILE_BOX_PATH="/shiyidao/FileBox/";
    public final static String APP_PATH= Environment.getExternalStorageDirectory()+FILE_PATH_ROOT;
    /**
     * 防止暴力操作时间间隔
     */
    public final static long ACTION_TIME_INTERBAL = 1000;

    /**
     * 内网服务器服务器地址
     */

    //public final static String SERVER_IP = "http://68.icecn.ne";内网地址
//    http://ercms.icecn.net/ercms/user/toLogin
     //public final static String SERVER_IP = "http://121.199.9.68:8082/ercms/api/";//
      public final static String SERVER_IP = "http://ercms.icecn.net/ercms/api/";//
    //外网服务器地址
   
//    public final static String SERVER_IP = "";//外网服务器地址



    /**
     * 硬件加速
     */
    public final static boolean booelans = false;

    /**
     * 数据分割标示（字符串／数组拼接使用）
     */
    public final static char decollator = ',';

    /**
     * 启动页展示时间
     */
    public final static long SPLASH_TIME = 3000;

    /**
     * 中文语言字符标示
     */
    public final static String LANGUE_CH = "ch";

    /**
     * 英文 语言字符标示
     */
    public final static String LANGUE_EN = "en";

    /**
     * 获取值默认键
     */
    public final static String KEY_DEFAULTRESULT = "result";
    /**
     * 设置默认值
     */
    public final static String KEY_DEFAULVALUES = "default";

    /**
     * 缓存个人信息保存SharedPreferences文件名
     */
//    public final static String DATACACHE = Application.getApplication().getUserInfoEntity().getUserId()+"";
    /**
     * 缓存公共信息保存SharedPreferences文件名
     */
    //public final static String PUBLIC_DATACACHE = "publiccache";
}
