package com.shiyidaoAndroidApp.ui.apps.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.base.BaseFragment;
import com.shiyidaoAndroidApp.databinding.FragmentMytaskBinding;

/**
 * Created by Adminidtrator on 2017/3/15.
 */

public class MyTAskFragment extends BaseFragment {
    private FragmentMytaskBinding binding;
    private BaseActivity activity;
    @Override
    protected View setMainView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (BaseActivity) getActivity();
        if (inflater == null) {
            inflater = LayoutInflater.from(activity);
        }

        binding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_mytask, container, false,DataBindingUtil.getDefaultComponent());

        return binding.getRoot();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initiData() {

    }



    @Override
    public void onClick(View v) {

    }
}
