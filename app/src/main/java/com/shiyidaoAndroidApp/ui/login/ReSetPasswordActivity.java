package com.shiyidaoAndroidApp.ui.login;

import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivityResetPasswordBinding;

/**
 * Created by Adminidtrator on 2017/3/13.
 */

public class ReSetPasswordActivity extends BaseActivity {
    private ActivityResetPasswordBinding binding;
    @Override
    protected void initTitle() {
        titleBar.setTitle("重置密码");
    }

    @Override
    protected void initBinding() {
        binding= (ActivityResetPasswordBinding) setContenteView(R.layout.activity_reset_password);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
