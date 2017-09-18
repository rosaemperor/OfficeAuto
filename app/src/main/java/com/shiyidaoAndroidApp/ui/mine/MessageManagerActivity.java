package com.shiyidaoAndroidApp.ui.mine;

import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivityMessageManagerBinding;

/**
 * Created by Adminidtrator on 2017/3/30.
 */

public class MessageManagerActivity extends BaseActivity {
    private ActivityMessageManagerBinding binding;
    @Override
    protected void initTitle() {
        titleBar.setTitle(getResources().getString(R.string.message_manager));

    }

    @Override
    protected void initBinding() {
      binding=setContenteView(R.layout.activity_message_manager);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
