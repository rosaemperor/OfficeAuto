package com.shiyidaoAndroidApp.ui.apps;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.base.BaseFragment;
import com.shiyidaoAndroidApp.databinding.FragmentAppsBinding;
import com.shiyidaoAndroidApp.ui.settings.SettingsActivity;

/**
 * Created by Adminidtrator on 2017/3/14.
 */

public class AppsFragment extends BaseFragment {
    private FragmentAppsBinding binding;
    private BaseActivity activity;
    @Override
    protected View setMainView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (BaseActivity) getActivity();
        if (inflater == null) {
            inflater = LayoutInflater.from(activity);
        }

        binding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_apps, container, false,DataBindingUtil.getDefaultComponent());

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
               case R.id.approvial://审批
                   startActivity(ApprovialActivity.class);
                   break;
               case R.id.files_box://文件库
                   startActivity(FileBoxActivity.class);
                   break;
               case R.id.sysetings://系统设置
                   startActivity(SettingsActivity.class);
                   break;
               case R.id.notice://公告通知
                   startActivity(NoticeActivity.class);
                   break;
               case R.id.coordination://工作协同
                   startActivity(CoordinationActivity.class);
                   break;
               default:
                   break;
           }
    }
}
