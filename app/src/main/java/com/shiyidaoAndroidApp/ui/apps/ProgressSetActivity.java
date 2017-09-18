package com.shiyidaoAndroidApp.ui.apps;

import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivityProgressSettingBinding;

/**
 * Created by Adminidtrator on 2017/4/5.
 */

public class ProgressSetActivity extends BaseActivity {
    private ActivityProgressSettingBinding binding;
    @Override
    protected void initTitle() {
        titleBar.setTitle(getString(R.string.process_setting));
    }

    @Override
    protected void initBinding() {
        binding=setContenteView(R.layout.activity_progress_setting);
        binding.setEvent(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_progress:
//                startActivity();
                break;
        }

    }
}
