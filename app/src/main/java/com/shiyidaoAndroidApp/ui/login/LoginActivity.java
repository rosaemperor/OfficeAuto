package com.shiyidaoAndroidApp.ui.login;


import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.bean.stu;
import com.shiyidaoAndroidApp.databinding.ActivityLoginLayoutBinding;
import com.shiyidaoAndroidApp.ui.HomePageActivity;

/**
 * Created by Adminidtrator on 2017/3/8.
 */

public class LoginActivity extends BaseActivity {
       private ActivityLoginLayoutBinding binding;

    @Override
    protected void initTitle() {
        hintTitlebar();


    }

    @Override
    protected void initBinding() {
        binding= setContenteView(R.layout.activity_login_layout);
        binding.setEvent(this);




    }

    @Override
    protected void initData() {
        binding.setStu(new stu("22","小旭旭"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reginst_button:
                startActivity(ReginstActivity.class);
                break;
            case R.id.forget_password:
                startActivity(FindPasswordActivity.class);
                break;
            case R.id.login_button:
                startActivity(HomePageActivity.class);
                break;
            default:
                break;
        }

    }
}
