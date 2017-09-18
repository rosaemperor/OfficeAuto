package com.shiyidaoAndroidApp.ui.mine.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.base.BaseFragment;
import com.shiyidaoAndroidApp.databinding.FragmentMineBinding;
import com.shiyidaoAndroidApp.ui.mine.MessageManagerActivity;
import com.shiyidaoAndroidApp.ui.mine.SecuritySettingActivity;

/**
 * Created by Adminidtrator on 2017/3/14.
 */

public class MineFragment extends BaseFragment {
    private BaseActivity activity;
    private FragmentMineBinding binding;
    @Override
    protected View setMainView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (BaseActivity) getActivity();
        if (inflater == null) {
            inflater = LayoutInflater.from(activity);
        }

        binding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_mine, container, false,DataBindingUtil.getDefaultComponent());

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
             case R.id.message_manager:
                 startActivity(MessageManagerActivity.class);
                 break;
             case R.id.security_setting:
                 startActivity(SecuritySettingActivity.class);
                 break;
             default:
                 break;
         }
    }
}
