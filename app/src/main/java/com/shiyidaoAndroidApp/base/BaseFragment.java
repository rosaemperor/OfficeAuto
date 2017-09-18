package com.shiyidaoAndroidApp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.shiyidaoAndroidApp.http.OkHttpHelper;

/**
 * Created by Yang on 15/8/31.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    protected OkHttpHelper httpHelper;//此处httpHelper只用来作测试用，正式使用，请用NETAPI
    protected final String fileBox="/zihengkeji/FileBox/";
    //protected final String TAG = getClass().getName();
    protected final String TAG = "";
    protected ProgressBar progressBar;
    protected final String BTAG = "";

    private Context context;

    public BaseFragment() {
        this.context = getActivity();
    }

    private View mainview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainview = setMainView(inflater, container, savedInstanceState);
        initView();
        httpHelper= new OkHttpHelper();
        httpHelper.init(getActivityContext());
        initiData();

        return mainview;
    }

    /**
     * 设置主内容布局
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected abstract View setMainView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    //    初始化视图
    protected abstract void initView();

    //初始化数据
    protected abstract void initiData();

//    //    初始化监听
//       protected abstract void initListenes();

    /**
     * 获取 上下文
     *
     * @return
     */
    protected Context getActivityContext() {
        return context == null ? (context = getActivity()) : context;
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
        intent.setClass(getActivityContext().getApplicationContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }


    /**
     * 绑定控件
     * @param id
     * @return
     */
    protected View findViewByID(int id) {
        return mainview.findViewById(id);
    }


}
