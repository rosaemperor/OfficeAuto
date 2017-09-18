package com.shiyidaoAndroidApp.ui.settings;

import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivityCompanyMessageBinding;

/**
 * Created by Adminidtrator on 2017/3/31.
 */

public class CompanyMessageActivity extends BaseActivity {
    private ActivityCompanyMessageBinding binding;
    @Override
    protected void initTitle() {
        titleBar.setTitle(getString(R.string.company_message));
    }

    @Override
    protected void initBinding() {
     binding=setContenteView(R.layout.activity_company_message);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
