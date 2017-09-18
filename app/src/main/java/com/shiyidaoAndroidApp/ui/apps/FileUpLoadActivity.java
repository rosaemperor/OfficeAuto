package com.shiyidaoAndroidApp.ui.apps;

import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivityUpFileBinding;

/**
 * Created by Adminidtrator on 2017/4/6.
 */

public class FileUpLoadActivity extends BaseActivity {
    private ActivityUpFileBinding binding;
    @Override
    protected void initTitle() {
        titleBar.setTitle(getString(R.string.up_file));
    }

    @Override
    protected void initBinding() {
binding=setContenteView(R.layout.activity_up_file);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
