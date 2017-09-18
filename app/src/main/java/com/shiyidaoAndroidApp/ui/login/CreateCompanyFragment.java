package com.shiyidaoAndroidApp.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.base.BaseFragment;
import com.shiyidaoAndroidApp.databinding.FragmentCreateCompanyBinding;

/**
 * Created by Adminidtrator on 2017/3/10.
 */

public class CreateCompanyFragment extends BaseFragment {
    private FragmentCreateCompanyBinding binding;
    private BaseActivity activity;
    private View mainView;//ContentVIew 主体
    @Override
    protected View setMainView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (BaseActivity) getActivity();
        if (inflater == null) {
            inflater = LayoutInflater.from(activity);
        }

        binding=DataBindingUtil.inflate(inflater,
                R.layout.fragment_create_company, container, true,DataBindingUtil.getDefaultComponent());
        binding.setFrag(this);
        Log.d("AAA","AAA");
         return binding.getRoot();
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding=DataBindingUtil.setContentView(getActivity(), R.layout.fragment_create_company);
//    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initiData() {

    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        Log.d("AA","AA");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("AA","onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.d("AA","onDestroyView");
        super.onDestroyView();
    }
}
