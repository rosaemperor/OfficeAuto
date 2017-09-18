package com.shiyidaoAndroidApp.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.shiyidaoAndroidApp.http.OkHttpHelper;


import java.io.File;

/**
 * 应用自定义
 * Created by Yang on 15/8/25.
 */
public class Application extends android.app.Application {

    private static Application application;
    private Display display;

    /**
     * Activity管理处理
     */
    private static ActivityTaskManager activityTaskManager;
    /**
     * 异常捕获处理
     */
    private HandlerException handlerException;

    /**
     * 登录用户数据
     */
//    private UserInfoEntity userInfoEntity = new UserInfoEntity();
//    private UserInfoEntity userInfoEntity;
//
//    private List<LocalstorageEntity.CacheTimeEntity> dataCache = new ArrayList<>();
//
//    public List<LocalstorageEntity.CacheTimeEntity> getDataCache() {
//        return dataCache;
//    }
//
//    public void setDataCache(List<LocalstorageEntity.CacheTimeEntity> dataCache) {
//        this.dataCache = dataCache;
//    }

    /**
     * 网路管理
     */
    private OkHttpHelper httpHelper;
    String role;

    @Override
    public void onCreate() {
        super.onCreate();
//        SDKInitializer.initialize(this);//初始化百度地图
        init();
        init1();
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "zihengkeji/Cache");
        cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "zihengkeji/FileBox");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(this)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                //.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                        //.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                        .memoryCacheSize(2 * 1024 * 1024)
                        .discCacheSize(50 * 1024 * 1024)
                                // .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5加密
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        .discCacheFileCount(100) //缓存的文件数量
//                                .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
                        .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                        .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(configuration);

    }

    /**
     * 初始化默认设置
     */
    private void init() {
        application = this;
        if (activityTaskManager == null) {
            activityTaskManager = ActivityTaskManager.getInstance();
        }
        httpHelper.init(getApplication());


    }

    /**
     * 获取Application
     *
     * @return
     */
    public static Application getApplication() {
        return application;
    }
    /**
     * 获取activityTaskManager
     *
     * @return
     */
    public static ActivityTaskManager getActivityTaskManager() {
        if (activityTaskManager == null) {
            activityTaskManager = ActivityTaskManager.getInstance();
        }
        return activityTaskManager;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    //退出程序
    public void exitApp() {
        activityTaskManager.cleanActivityStack();
        System.exit(0);
    }


    private void init1() {
        initImageLoader(getApplicationContext());
        //本地图片辅助类初始化



        if (display == null) {
            WindowManager windowManager = (WindowManager)
                    getSystemService(Context.WINDOW_SERVICE);
            display = windowManager.getDefaultDisplay();
        }
    }


    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY);
        config.denyCacheImageMultipleSizesInMemory();
        config.memoryCacheSize((int) Runtime.getRuntime().maxMemory() / 4);
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(100 * 1024 * 1024); // 100 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        //修改连接超时时间5秒，下载超时时间5秒
        config.imageDownloader(new BaseImageDownloader(application, 5 * 1000, 5 * 1000));
        //		config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    public String getCachePath() {
        File cacheDir;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = getExternalCacheDir();
        else
            cacheDir = getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
        return cacheDir.getAbsolutePath();
    }
    /**
     * @return
     * @Description： 获取当前屏幕的宽度
     */
    public int getWindowWidth() {
        Point point=new Point();
        display.getSize(point);
        return   point.x;
    }

    /**
     * @return
     * @Description： 获取当前屏幕的高度
     */
    public int getWindowHeight() {
        return display.getHeight();
    }

    /**
     * @return
     * @Description： 获取当前屏幕一半宽度
     */
    public int getHalfWidth() {
        return display.getWidth() / 2;
    }

    /**
     * @return
     * @Description： 获取当前屏幕1/4宽度
     */
    public int getQuarterWidth() {
        return display.getWidth() / 4;
    }
}
