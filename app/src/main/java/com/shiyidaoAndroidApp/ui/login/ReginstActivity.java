package com.shiyidaoAndroidApp.ui.login;


import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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
import com.shiyidaoAndroidApp.databinding.ActivityReginstLayoutBinding;
import com.shiyidaoAndroidApp.ui.login.fragment.AddCompanyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adminidtrator on 2017/3/10.
 */

public class ReginstActivity extends BaseActivity {
    private ActivityReginstLayoutBinding binding;
    private int lineWidth;
    private int offset=0;//偏移量
    private static final int TAB_COUNT=2;
    private TextView[] titles;
    private int current_index=0;
    private static final int TAB_0 = 0;
    private static final int TAB_1 = 1;
    @Override
    protected void initTitle() {
        titleBar.setTitle(getResources().getString(R.string.reginst));
    }

    @Override
    protected void initBinding() {
        binding=  setContenteView(R.layout.activity_reginst_layout);
//        binding= DataBindingUtil.setContentView(this, R.layout.activity_reginst_layout);
        initImageView();
        initVPager();
        binding.setEvent(this);

    }

    private void initVPager() {
        List<Fragment> list=new ArrayList<>();
        list.add(new CreateCompanyFragment());
        list.add(new AddCompanyFragment());



        MyFragmentPagerAdapter adapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),list);
        binding.viewpager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        binding.viewpager.setCurrentItem(0);
        titles=new TextView[]{binding.tab1,binding.tab2};
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
                titles[position].setTextColor(getResources().getColor(R.color.colorAccent));
                titles[current_index].setTextColor(getResources().getColor(R.color.fragment_background_color));
                current_index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initImageView() {
//        lineWidth = BitmapFactory.decodeResource(getResources(), R.color.colorAccent).getWidth();
        lineWidth= Application.getApplication().getWindowWidth()/2;
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

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.tab1:
               if (binding.viewpager.getCurrentItem() != TAB_0) {
                   binding.viewpager.setCurrentItem(TAB_0);
               }
               break;
           case R.id.tab2:
               if (binding.viewpager.getCurrentItem() != TAB_1) {
                   binding.viewpager.setCurrentItem(TAB_1);
               }
               break;
           default:
               break;
       }
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
