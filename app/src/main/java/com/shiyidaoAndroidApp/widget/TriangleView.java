package com.shiyidaoAndroidApp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.shiyidaoAndroidApp.R;


/**
 * Created by Adminidtrator on 2017/3/27.
 */

public class TriangleView extends View{
    //文本
    private String mText="测试";
    //文本颜色
    private int mTextColor;
    //字体大小
    private float mTextSize;
    //背景颜色
    private int mBackGroundColor;
    //半身颜色
    private int mHalfGroundColor;
    //半身位置
    private String mHalfLocation;
    //画笔
    private Rect mBound;

    private Rect mPathBounds;
    private Paint mPaint;
    //宽度,高度
    private int width,height;
    //用于测量Path长度的类
    public PathMeasure pathMeasure;

    public TriangleView(Context context) {

        this(context,null);

    }

    public TriangleView(Context context, AttributeSet attrs) {

        this(context, attrs,0);

    }

    public TriangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 获取自定义的样式属性
         */
        this.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView
        ,defStyleAttr,0);
        int n=typedArray.getIndexCount();
        for(int i=0;i<n;i++){
            int atrr=typedArray.getIndex(i);
            switch (atrr) {
                case R.styleable.MyView_text:
                    mText = typedArray.getString(atrr);
                    break;
                case R.styleable.MyView_textColor:
                    mTextColor=typedArray.getColor(atrr, Color.WHITE);
                    break;
                case R.styleable.MyView_textSize:
                    mTextSize=typedArray.getDimensionPixelSize(atrr,(int)
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MyView_backGroundColor:
                    mBackGroundColor=typedArray.getColor(atrr,Color.BLACK);
                    break;
                case R.styleable.MyView_halfGroundColor:
                    mHalfGroundColor=typedArray.getColor(atrr,Color.BLUE);
                    break;
//                case R.styleable.MyView_halfLocation:
//                    mHalfLocation=typedArray.getString(atrr);
//                    break;
                default:
                    break;

            }
        }
        typedArray.recycle();
        mPaint=new Paint();
        mPaint.setTextSize(mTextSize);
        mBound=new Rect();
        mPaint.getTextBounds(mText,0,mText.length(),mBound);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(widthMeasureSpec);
        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText,0,mText.length(),mBound);
            float textWidth=mBound.width();
            width=(int)(getPaddingLeft()+textWidth+getPaddingRight());
        }
        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText,0,mText.length(),mBound);
            float textHeight=mBound.height();
            height=(int)(getPaddingBottom()+textHeight+getPaddingTop());
        }
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(mBackGroundColor);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
//        mPaint.setColor(mTextColor);
//        canvas.drawText(mText,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,
//                mPaint);
        mPaint.setColor(mHalfGroundColor);
        Path path=new Path();
        path.moveTo(0,0);
        path.lineTo(width,height);
        path.lineTo(width,0);

        canvas.drawPath(path,mPaint);
        mPaint.setColor(mTextColor);
//        canvas.drawTextOnPath(mText,path,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,
//                mPaint);
        pathMeasure=new PathMeasure(path,false);
        canvas.drawTextOnPath(mText,path,(pathMeasure.getLength()-width-mBound.width())/2,-15,
                mPaint);

    }

    /**
     * 设置背景颜色
     * @param color
     */
    public void setmBackGroundColor(int color){
        mBackGroundColor=color;
    }

    /**
     * 设置字体颜色
     * @param color
     */
    public void setmTextColor(int color){
        mTextColor=color;
    }

    /**
     * 设置上半部分颜色
     * @param color
     */
    public void setmHalfGroundColor(int color){
        mTextColor=color;
    }

    /**
     * 设置文字
     * @param text
     */
    public void setmText(String text){
        mText=text;
    }

    /**
     * 设置字体大小
     * @param textSize
     */
    public void setmTextSize(float textSize){
        mTextSize=textSize;
    }
    public void buildViewAgain(int backGroundColor,int halcGroundColor,int textColor,String text,float textSize){
       mBackGroundColor=backGroundColor;
        mHalfGroundColor=halcGroundColor;
        mTextColor=textColor;
        mText=text;
        mTextSize=textSize;
    }
}
