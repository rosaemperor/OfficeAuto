package com.shiyidaoAndroidApp.ui.apps;

import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.shiyidaoAndroidApp.R;
import com.shiyidaoAndroidApp.app.Application;
import com.shiyidaoAndroidApp.base.BaseActivity;
import com.shiyidaoAndroidApp.databinding.ActivityApprovialBinding;
import com.shiyidaoAndroidApp.ui.apps.fragment.FunctionSettingFragment;
import com.shiyidaoAndroidApp.ui.apps.fragment.MyTAskFragment;
import com.shiyidaoAndroidApp.ui.apps.fragment.NewApproviaFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adminidtrator on 2017/3/15.
 */

public class ApprovialActivity extends BaseActivity {
    private ActivityApprovialBinding binding;
    private int lineWidth;
    private int offset=0;//偏移量
    private static final int TAB_COUNT=4;
    private TextView[] titles;
    private int current_index=0;
    private static final int TAB_0 = 0;
    private static final int TAB_1 = 1;
    private static final int TAB_2 = 2;
    private static final int TAB_3 = 3;
    @Override
    protected void initTitle() {
        titleBar.setTitle(getString(R.string.approvial_text));
    }

    @Override
    protected void initBinding() {
        binding=setContenteView(R.layout.activity_approvial);
        initImageView();
        initVPager();

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
    private void initImageView() {
//        lineWidth = BitmapFactory.decodeResource(getResources(), R.color.colorAccent).getWidth();
        lineWidth= Application.getApplication().getWindowWidth()/TAB_COUNT;
        ViewGroup.LayoutParams lap=binding.ivTabLine.getLayoutParams();
        lap.width=lineWidth;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels; // 获取分辨率宽度
        offset = (screenW / TAB_COUNT - lineWidth) / 2;  // 计算偏移值
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        // 设置下划线初始位置
        binding.ivTabLine.setImageMatrix(matrix);
    }
    private void initVPager() {
        List<Fragment> list=new ArrayList<>();
        list.add(new MyTAskFragment());
        list.add(new MyTAskFragment());
//        list.add(new MyApprovialFragment());
        list.add(new NewApproviaFragment());
        list.add(new FunctionSettingFragment());


        MyFragmentPagerAdapter adapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),list);
        binding.viewpager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        binding.viewpager.setCurrentItem(0);
        titles=new TextView[]{binding.tab0,binding.tab1,binding.tab2,binding.tab3};
        binding.viewpager.setOffscreenPageLimit(titles.length);
        binding.viewpager.invalidate();
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int one = offset * 2 + lineWidth;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // 下划线开始移动前的位置
                float fromX = one * current_index;
                // 下划线移动完毕后的位置
                float toX = one * position;
                Animation animation = new TranslateAnimation(fromX, toX, 0, 0);
                animation.setFillAfter(true);
                animation.setDuration(500);
                // 给图片添加动画
                binding.ivTabLine.startAnimation(animation);
                // 当前Tab的字体变色
                titles[position].setTextColor(ContextCompat.getColor(ApprovialActivity.this,R.color.colorAccent));
                titles[current_index].setTextColor(ContextCompat.getColor(ApprovialActivity.this,R.color.notice_items_offText));
                current_index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public  class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public List<Fragment> mListViews;
        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> mListViews) {
            super(fm);
            this.mListViews = mListViews;

        }



        @Override
        public int getCount() {
            return mListViews.size();
        }



        @Override
        public Fragment getItem(int position) {

            return  mListViews.get(position);
        }



    }
}
