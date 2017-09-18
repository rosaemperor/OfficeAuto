package com.shiyidaoAndroidApp.ui.login.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.base.BaseFragment;
import com.shiyidaoAndroidApp.databinding.FragmentAddCompanyBinding;
import com.shiyidaoAndroidApp.databinding.FragmentSecondAddcompanyBinding;

/**
 * Created by Adminidtrator on 2017/3/14.
 */

public class AddCompanyFragment extends BaseFragment {
    private FragmentAddCompanyBinding binding;
    private FragmentSecondAddcompanyBinding secondAddcompanyBinding;
    private BaseActivity activity;
    private SecondAddCompanyFragment nextFragment;
    @Override
    protected View setMainView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (BaseActivity) getActivity();
        if (inflater == null) {
            inflater = LayoutInflater.from(activity);
        }

        binding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_add_company, container, false,DataBindingUtil.getDefaultComponent());
        secondAddcompanyBinding=DataBindingUtil.inflate(inflater,
                R.layout.fragment_second_addcompany, container, false,DataBindingUtil.getDefaultComponent());

        return binding.getRoot();

    }

    @Override
    protected void initView() {
        secondAddcompanyBinding.setEvent(this);
        binding.setEvent(this);
    }

    @Override
    protected void initiData() {

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.next_step:
                binding.test.removeAllViews();
                binding.test.addView(secondAddcompanyBinding.getRoot());
                break;
            case R.id.second_next_button:
                Log.d("AAA","AAA");
                break;
        }

    }
}
