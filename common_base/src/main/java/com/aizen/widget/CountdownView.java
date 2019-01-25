package com.aizen.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by ld on 2019/1/2.
 *
 * @author ld
 * @date 2019/1/2
 * 描    述：
 */
@SuppressLint("AppCompatCustomView")
public class CountdownView extends TextView implements Runnable {
    /**
     * 倒计时时间
     */
    private int mTotalTime = 60;
    /**
     * 倒计时单位
     */
    private static final String TIME_UNIT = "S";
    /**
     * 当前秒数
     */
    private int mCurrentTime;
    /**
     * 记录原有文本
     */
    private CharSequence mRecordText;
    /**
     * 是否重置了倒计时
     */
    private boolean mFlag;

    public CountdownView(Context context) {
        super(context);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTotalTime(int totalTime) {
        mTotalTime = totalTime;
    }

    /**
     * 重置倒计时
     */
    public void resetState(){
        mFlag = true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //设置可点击属性
        setClickable(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        //移除延时任务
        removeCallbacks(this);
        super.onDetachedFromWindow();
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        mRecordText = getText();
        setEnabled(false);
        mCurrentTime = mTotalTime;
        post(this);
        return click;
    }

    @Override
    public void run() {
        if (mCurrentTime == 0 || mFlag) {
            setText(mRecordText);
            setEnabled(true);
            mFlag = false;
        } else {
            mCurrentTime--;
            setText(mCurrentTime + "\t" + TIME_UNIT);
            postDelayed(this, 1000);
        }
    }
}
