package com.shiyidaoAndroidApp.ui.order;


import android.databinding.DataBindingUtil;
import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivityPlaceOrderBinding;

/**
 * Created by Adminidtrator on 2017/7/17.
 */

public class PlaceOrderActivity extends BaseActivity {
    private ActivityPlaceOrderBinding binding;
    @Override
    protected void initTitle() {
        titleBar.setTitle(getString(R.string.place_order));
    }

    @Override
    protected void initBinding() {
        binding=setContenteView(R.layout.activity_place_order);
    }
    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
