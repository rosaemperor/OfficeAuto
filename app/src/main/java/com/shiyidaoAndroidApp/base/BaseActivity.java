package com.shiyidaoAndroidApp.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.app.AppInfoUtile;
import com.shiyidaoAndroidApp.app.Application;
import com.shiyidaoAndroidApp.widget.TitleBar;

/**
 * Created by Adminidtrator on 2017/3/9.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    protected String TAG = getClass().getSimpleName();
    private AppInfoUtile appInfoUtile;
    protected TitleBar titleBar;
    private LinearLayout mainView;
    protected LinearLayout.LayoutParams mmlayoutparams;
    private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appInfoUtile = AppInfoUtile.getInstance(getApplicationContext());
        //弹出键盘时重置布局/默认隐藏输入键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //所有界面显示垂直锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置键盘弹出是不挤占页面
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //Activity管理 添加当前Activity
        Application.getActivityTaskManager().pushActivity(this);
        initContentView();
        initTitleBar();
        initBinding();
        initData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
    }

    protected void initTitleBar() {
        titleBar = new TitleBar(this);
        titleBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        titleBar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        //Title 内边距设置
        titleBar.setPadding(appInfoUtile.dptopx(getApplicationContext(), 15), appInfoUtile.dptopx(getApplicationContext(), 10), appInfoUtile.dptopx(getApplicationContext(), 15), appInfoUtile.dptopx(getApplicationContext(), 10));
        titleBar.setLeftViewDraw(R.mipmap.back_arrow);
        titleBar.setLeftViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initTitle();
        mainView.addView(titleBar);
    }

    protected void initContentView() {
        inflater = LayoutInflater.from(this);
        mainView = new LinearLayout(this);
        mainView.setOrientation(LinearLayout.VERTICAL);
        mmlayoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mainView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mainView.setFitsSystemWindows(true);
    }
    /**
     * 初始化title
     */
    protected abstract void initTitle();
    /**
     * 初始化binding
     */
    protected abstract void initBinding();
    /**
     * 初始化数据
     */
    protected abstract void initData();
    /**
     * 隐藏Titlebar
     */
    protected void hintTitlebar() {
        if (titleBar != null) {
            titleBar.setVisibility(View.GONE);
        }
    }

    /**
     * 显示titlebar
     */
    protected void showTitleBar() {
        if (titleBar != null && titleBar.getVisibility() == View.GONE) {
            titleBar.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 获取titlebar
     */
    protected TitleBar getTitlebar() {
        return titleBar != null ? titleBar : null;
    }
    /**
     * 设置主内容
     *
     * @param layoutid
     */
    protected  <T extends ViewDataBinding> T setContenteView(int layoutid) {
//
//        getSupportActionBar().hide();
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
//        layoutParams.weight = 1;
//        mainView.addView(inflater.inflate(layoutid, null), layoutParams);
        this.setContentView(mainView);
////        View decorView = this.getWindow().getDecorView();
////        ViewGroup contentView = (ViewGroup) decorView.findViewById(android.R.id.content);
//      ViewDataBinding binding=DataBindingUtil.inflate(inflater,layoutid,mainView,true,DataBindingUtil.getDefaultComponent());
////        ViewDataBinding binding=DataBindingUtil.bindToAddedViews(DataBindingUtil.getDefaultComponent(), contentView, 0, layoutid);


         return DataBindingUtil.inflate(inflater,layoutid,mainView,true,DataBindingUtil.getDefaultComponent());
//
    }
    /**
     * 跳转Activity
     */
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转
     *
     * @param cls
     */
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * Action跳转
     *
     * @param action
     * @param bundle
     */
    public void startAction(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * Action 跳转
     *
     * @param action
     */
    public void startAction(String action) {
        startAction(action, null);
    }

    /**
     * 数据请求跳转
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * 关闭当前Activity
     */
    @Override
    public void finish() {
        //如果键盘开着，把他关掉
        collapseSoftInputMethod();
        Application.getActivityTaskManager().popActivity(this);
        super.finish();
    }
    //关闭键盘输入法
    protected void collapseSoftInputMethod() {
        if (mainView != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mainView.getWindowToken(), 0);
        }
    }

    //显示输入法
    protected void showSoftInputMethod() {
        if (mainView != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            boolean isOpen=imm.isActive();
            if(isOpen){
            }else {
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mainView, InputMethodManager.SHOW_FORCED);
            }

        }
    }
//    private static <T extends ViewDataBinding> T bindToAddedViews(android.databinding.DataBindingComponent component,
//                                                                  ViewGroup parent, int startChildren, int layoutId) {
//        final int endChildren = parent.getChildCount();
//        final int childrenAdded = endChildren - startChildren;
//        if (childrenAdded == 1) {
//            final View childView = parent.getChildAt(endChildren - 1);
//            return DataBindingUtil.bind(component, childView, layoutId);
//        } else {
//            final View[] children = new View[childrenAdded];
//            for (int i = 0; i < childrenAdded; i++) {
//                children[i] = parent.getChildAt(i + startChildren);
//            }
//            return DataBindingUtil.bind(component, children, layoutId);
//        }
//    }
@TargetApi(19)
private void setTranslucentStatus(boolean on) {
    Window win = getWindow();
    WindowManager.LayoutParams winParams = win.getAttributes();
    final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
    if (on) {
        winParams.flags |= bits;
    } else {
        winParams.flags &= ~bits;
    }
    win.setAttributes(winParams);
}
}
