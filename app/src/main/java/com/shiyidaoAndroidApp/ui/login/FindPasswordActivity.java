package com.shiyidaoAndroidApp.ui.login;

import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivityFindPasswordBinding;

/**
 * Created by Adminidtrator on 2017/3/13.
 */

public class FindPasswordActivity extends BaseActivity {
    private ActivityFindPasswordBinding binding;
    @Override
    protected void initTitle() {
        titleBar.setTitle("找回密码");
    }

    @Override
    protected void initBinding() {
        binding= (ActivityFindPasswordBinding) setContenteView(R.layout.activity_find_password);
        binding.setEvent(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.neststep:
                startActivity(ReSetPasswordActivity.class);
                break;
            default:
                break;
        }

    }
}
