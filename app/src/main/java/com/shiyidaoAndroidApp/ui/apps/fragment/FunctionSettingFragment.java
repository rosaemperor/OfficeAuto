package com.shiyidaoAndroidApp.ui.apps.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.base.BaseFragment;
import com.shiyidaoAndroidApp.databinding.FragmentFunctionSettingBinding;
import com.shiyidaoAndroidApp.ui.apps.ProgressSetActivity;
import com.shiyidaoAndroidApp.ui.apps.TypeSetActicity;

/**
 * Created by Adminidtrator on 2017/3/24.
 */

public class FunctionSettingFragment extends BaseFragment {
    private BaseActivity activity;
    private FragmentFunctionSettingBinding binding;

    @Override
    protected View setMainView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (BaseActivity) getActivity();
        if (inflater == null) {
            inflater = LayoutInflater.from(activity);
        }

        binding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_function_setting, container, false,DataBindingUtil.getDefaultComponent());

        return binding.getRoot();
    }

    @Override
    protected void initView() {
         binding.setEvent(this);
    }

    @Override
    protected void initiData() {

    }



    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.progress_setting://流程设置
                 startActivity(ProgressSetActivity.class);
                 break;
             case R.id.type_setting://类型设置
                 startActivity(TypeSetActicity.class);
                 break;
             default:
                 break;
         }
    }
}
