package com.shiyidaoAndroidApp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivityHomepageBinding;
import com.shiyidaoAndroidApp.ui.apps.AppsFragment;
import com.shiyidaoAndroidApp.ui.login.fragment.Fragment1;
import com.shiyidaoAndroidApp.ui.mine.fragment.MineFragment;

/**
 * Created by Adminidtrator on 2017/3/13.
 */

public class HomePageActivity extends BaseActivity{
    private ActivityHomepageBinding binding;
    private LayoutInflater mLayoutInflater;
    private Class mFragmentArray[] = { AppsFragment.class, Fragment1.class,
            Fragment1.class,MineFragment.class};
    private String[] mTextArray;
    private int mImageArray[] ={R.drawable.image_apps, R.drawable.image_message,
            R.drawable.image_persons,R.drawable.image_mine};
    private String[] titles={"XX省XX市XXX有限公司","2","3","我的"};
    @Override
    protected void initTitle() {
     titleBar.setTitle("XX省XX市XXX有限公司");
    }

    @Override
    protected void initBinding() {
     binding= setContenteView(R.layout.activity_homepage);
//        binding= DataBindingUtil.setContentView(this, R.layout.activity_homepage);
        mLayoutInflater = LayoutInflater.from(this);
        binding.tabHost.setup(HomePageActivity.this, getSupportFragmentManager(), R.id.realtabcontent);
        binding.tabHost.getTabWidget().setDividerDrawable(R.color.color_White);
        mTextArray=getResources().getStringArray(R.array.boom_string);
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = binding.tabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            binding.tabHost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab按钮的背景
            binding.tabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.color.color_White);//白色

        }

    }

    @Override
    protected void initData() {

        binding.tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                titleBar.setTitle(titles[binding.tabHost.getCurrentTab()]);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item_home_activity, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);
        return view;
    }

}
