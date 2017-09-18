package com.shiyidaoAndroidApp.ui.settings;

import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.FragmentSettingBinding;

/**
 * Created by Adminidtrator on 2017/3/14.
 */

public class SettingsActivity extends BaseActivity {
    private FragmentSettingBinding binding;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.company_message:
                startActivity(CompanyMessageActivity.class);
                break;
            case R.id.company_quthentication:
                startActivity(CompanyAuthenticationActivity.class);
                break;
             default:
                 break;
        }

    }

    @Override
    protected void initTitle() {
      titleBar.setTitle(getResources().getString(R.string.system_manage));
    }

    @Override
    protected void initBinding() {
        binding=setContenteView(R.layout.fragment_setting);
        binding.setEvent(this);
    }

    @Override
    protected void initData() {

    }
}
