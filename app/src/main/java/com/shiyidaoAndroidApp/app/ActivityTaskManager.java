package com.shiyidaoAndroidApp.app;

import android.app.Activity;


import com.shiyidaoAndroidApp.uitle.developHelp.LogUitl;

import java.util.Stack;

/**
 * <h1>Activity 管理</h1>
 * Created by Yang on 15/8/25.
 */
public class ActivityTaskManager {

    private String LogTag = getClass().getName();

    /**
     * Activity 存储栈
     */
    private volatile static Stack<Activity> activityStack = new Stack<>();

    private volatile static ActivityTaskManager instance;

    private ActivityTaskManager() {
    }

    /**
     * 获取管理者实体
     *
     * @return
     */
    public synchronized static ActivityTaskManager getInstance() {
        if (instance == null)
            instance = new ActivityTaskManager();
        return instance;
    }


    /**
     * 当前Activity压入棧当中
     *
     * @param activity
     */
    public void pushActivity(Activity activity) {
        LogUitl.D(LogTag, "-----------------push : " + activity.getLocalClassName()+"-------------");
        activityStack.add(activity);
    }

    /**
     * 退出当前Acitivyt
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            LogUitl.D(LogTag, "--------------------pop : " + activity.getLocalClassName()+"---------------");
            activityStack.remove(activity);
        }
    }

    /**
     * 获取当前顶部的Activity
     * 如果当前为获取到，则返回Null
     *
     * @return
     */
    public Activity getTopActivity() {
        Activity activity = null;
        if (!activityStack.empty()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * 退出其他Activity
     *
     * @param activity 需要保留的activity
     */
    public void popOtherActivity(Activity activity) {
        if (activity == null) {
            LogUitl.D(LogTag, "the activity is null");
            return;
        }
        for (Activity a : activityStack) {
            if (a == null || a.equals(activity)) {
                continue;
            }
            activity.finish();
        }
    }

    /**
     * 退出所有Activity
     */
    public void cleanActivityStack() {
        for (Activity a : activityStack) {
            popActivity(a);
        }
        LogUitl.D(LogTag, "clean all activity,Activity Size = " + activityStack.size());
    }

}
