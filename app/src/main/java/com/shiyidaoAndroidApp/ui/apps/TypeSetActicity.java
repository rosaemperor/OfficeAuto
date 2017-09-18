package com.shiyidaoAndroidApp.ui.apps;

import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;

import com.shiyidaoAndroidApp.databinding.ActivityTypeSettingBinding;

/**
 * Created by Adminidtrator on 2017/4/5.
 */

public class TypeSetActicity extends BaseActivity {
    private ActivityTypeSettingBinding binding;
    @Override
    protected void initTitle() {
      titleBar.setTitle(getString(R.string.type_setting));
    }

    @Override
    protected void initBinding() {
        binding=setContenteView(R.layout.activity_type_setting);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
