package com.shiyidaoAndroidApp.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 通用标题栏布局<br>
 * 通过实现相对布局实现。
 * Created by Yang on 15/8/25.
 */
public class TitleBar extends RelativeLayout {

    private View leftView;

    private TextView titleTv;

    private View rigthView;

    private Context context;

    public TitleBar(Context context) {
        super(context);
        this.context = context;
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * 设置对应按键是否可见
     *
     * @param left
     * @param center
     * @param rigth
     */
    public void setVisibilityView(int left, int center, int rigth) {
        if (leftView != null) {
            leftView.setVisibility(left);
        }
        if (titleTv != null) {
            titleTv.setText(center);
        }
        if (rigthView != null) {
            rigthView.setVisibility(rigth);
        }
    }

    /**
     * 设置默认Title
     */
    public void setTitle(String title) {
        if (titleTv == null) {
            titleTv = new TextView(context);
            titleTv.setTextColor(Color.WHITE);
            titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            titleTv.setGravity(Gravity.CENTER);
            titleTv.setPadding(0,0,0,10);
            titleTv.setLayoutParams(getCenterLayoutPamar());
            addView(titleTv);
        }
        titleTv.setText(title);
    }

    /**
     * 设置默认Title
     */
    public void setTitle(int strid) {
        setTitle(context.getString(strid));
    }

    /**
     * @param color
     */
    public void setTitleTextColor(int color) {
        if (titleTv != null) {
            titleTv.setTextColor(color);
        }
    }

    /**
     * 设置左边自定义视图
     *
     * @param view
     */
    public void setLeftView(View view) {
        leftView = view;
        leftView.setLayoutParams(getLeftLayoutParams());

        addView(leftView);
    }

    /**
     * 设置左边为自定义图片
     *
     * @param drawid
     */
    public void setLeftViewDraw(int drawid) {
        if (leftView!=null){
            removeView(leftView);
            leftView = null;
        }
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageDrawable(getResources().getDrawable(drawid));
        setLeftView(imageView);
    }
    /**
     * 设置右边为自定义图片
     *
     * @param drawid
     */
    public void setRigthViewDraw(int drawid) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageDrawable(getResources().getDrawable(drawid));
        setRigthView(imageView);
    }

    /**
     * 设置右边自定义视图
     *
     * @param view
     */
    public void setRigthView(View view) {
        if(view!=null){
            rigthView = view;
            rigthView.setLayoutParams(getRigthLayoutParams());
            addView(rigthView);
        }
    }

    /**
     * 设置标题点击事件
     *
     * @param clickListener
     */
    public void setTitleClickListener(OnClickListener clickListener) {
        if (titleTv != null) {
            titleTv.setOnClickListener(clickListener);
        }
    }

    /**
     * 设置左边视图点击事件
     *
     * @param clickListener
     */
    public void setLeftViewClickListener(OnClickListener clickListener) {
        if (leftView != null) {
            leftView.setOnClickListener(clickListener);
        }
    }

    /**
     * 设置右边视图的点击事件
     *
     * @param clickListner
     */
    public void setRigthViewClickListner(OnClickListener clickListner) {
        if (rigthView != null) {
            rigthView.setOnClickListener(clickListner);
        }
    }

    /**
     * 获取居中布局Layoutparams
     *
     * @return
     */
    private LayoutParams getCenterLayoutPamar() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        return layoutParams;
    }

    /**
     * 获取左边布局layoutparams
     *
     * @return
     */
    private LayoutParams getLeftLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        return layoutParams;
    }

    /**
     * 获取右边布局layoutparams
     *
     * @return
     */
    private LayoutParams getRigthLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        return layoutParams;
    }

    public void setVisiableRightView(){
        if(rigthView!=null){
            rigthView.setVisibility(VISIBLE);
        }
    }
    public void setGoneRightView(){
        if(rigthView!=null){
            rigthView.setVisibility(GONE);
        }
    }

//    public void setTitleTvDraw(){
//        titleTv.setCompoundDrawables(null,null,getResources().getDrawable(R.drawable.time1),null);
//    }

    public TextView getTitleTv() {
        return titleTv;
    }
}
