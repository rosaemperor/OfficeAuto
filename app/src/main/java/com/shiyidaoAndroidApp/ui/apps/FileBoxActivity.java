package com.shiyidaoAndroidApp.ui.apps;

import android.view.View;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivityFileBoxBinding;

/**
 * Created by Adminidtrator on 2017/4/1.
 */

public class FileBoxActivity extends BaseActivity {
    private ActivityFileBoxBinding binding;
    @Override
    protected void initTitle() {
        titleBar.setTitle(getString(R.string.file_box));
    }

    @Override
    protected void initBinding() {
       binding=setContenteView(R.layout.activity_file_box);
        binding.setEvent(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
           switch (v.getId()){
               case R.id.add_new_file:
                   startActivity(FileUpLoadActivity.class);
                   break;
           }
    }
}
