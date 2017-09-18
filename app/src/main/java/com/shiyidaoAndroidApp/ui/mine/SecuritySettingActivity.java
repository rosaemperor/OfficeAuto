package com.shiyidaoAndroidApp.ui.mine;

import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivitySecuritySettingBinding;

/**
 * Created by Adminidtrator on 2017/3/30.
 */

public class SecuritySettingActivity extends BaseActivity {
    private ActivitySecuritySettingBinding binding;
    @Override
    protected void initTitle() {
titleBar.setTitle(getResources().getString(R.string.security_setting));
    }

    @Override
    protected void initBinding() {
      binding=setContenteView(R.layout.activity_security_setting);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
