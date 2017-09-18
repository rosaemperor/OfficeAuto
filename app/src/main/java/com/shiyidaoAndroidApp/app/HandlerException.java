package com.shiyidaoAndroidApp.app;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 异常捕获处理类
 * Created by Yang on 15/8/25.
 */
public class HandlerException implements Thread.UncaughtExceptionHandler {

    private static HandlerException instance;
    private Context context;
    private Thread.UncaughtExceptionHandler mDefaultHanlder;

    /**
     * 是否需要保存异常日志
     */
    private boolean shouldSaveException = true;

    private HandlerException() {
    }

    /**
     * 异常处理捕获处理
     *
     * @return
     */
    public static HandlerException getInstance() {
        if (instance == null) {
            instance = new HandlerException();
        }
        return instance;
    }

    /**
     * 初始化异常捕获
     *
     * @param context
     */
    public void init(Context context) {
        this.context = context;
        mDefaultHanlder = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    //    异常捕获
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //捕获到到的异常
        if (!handlerException(ex) && mDefaultHanlder == null) {
            //交给系统进行处理
            mDefaultHanlder.uncaughtException(thread, ex);
        } else {
            //异常捕获
            Toast.makeText(context, "App will exit.", Toast.LENGTH_SHORT).show();

            //已被处理，手动退出App
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
    }

    //异常的处理
    public boolean handlerException(Throwable ex) {
        //异常已被处理
        if (ex == null)
            return false;
        final StackTraceElement stack[] = ex.getStackTrace();
        final String msg = ex.getMessage();

        //新开线程，把遗产信息保存到文本中去
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                String fileName = "crash" + System.currentTimeMillis() + ".log";
                File file = new File(Environment.getExternalStorageDirectory(), fileName);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file, true);
                    fos.write(msg.getBytes());
                    for (StackTraceElement s : stack) {
                        fos.write(s.toString().getBytes());
                    }
                } catch (IOException e) {
                } finally {
                    if (fos != null) {
                        try {
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                        }
                    }
                    Toast.makeText(context, "file has save .", Toast.LENGTH_SHORT).show();
                }
                Looper.loop();
            }
        }.start();
        return false;
    }
}
