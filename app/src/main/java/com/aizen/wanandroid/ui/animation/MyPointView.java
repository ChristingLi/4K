package com.aizen.wanandroid.ui.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by ld on 2018/12/20.
 *
 * @author ld
 * @date 2018/12/20
 * 描    述：PointView
 */
public class MyPointView extends View {
    //默认圆的半径
    public static final float RADIUS = 70f;
    //当前坐标
    private Point mCurrentPoint;
    //画笔
    private Paint mPaint;

    public MyPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mCurrentPoint == null){
            mCurrentPoint = new Point(RADIUS,RADIUS);
            float x = mCurrentPoint.getX();
            float y = mCurrentPoint.getY();
            canvas.drawCircle(x,y,RADIUS,mPaint);

            Point startPoint = new Point(RADIUS,RADIUS);
            Point endPoint = new Point(700,1000);
            ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
            animator.setDuration(5000);
            animator.addUpdateListener(animation -> {
                mCurrentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            });
            animator.start();
        }else {
            float x = mCurrentPoint.getX();
            float y = mCurrentPoint.getY();
            canvas.drawCircle(x,y,RADIUS,mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        // 获取宽-测量规则的模式和大小
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        // 获取高-测量规则的模式和大小
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        // 设置wrap_content的默认宽 / 高值
//        // 默认宽/高的设定并无固定依据,根据需要灵活设置
//        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
//        int mWidth = 400;
//        int mHeight = 400;
//        // 当模式是AT_MOST（即wrap_content）时设置默认值
//        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(mWidth, mHeight);
//            // 宽 / 高任意一个模式为AT_MOST（即wrap_content）时，都设置默认值
//        } else if (widthMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(mWidth, heightSize);
//        } else if (heightMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(widthSize, mHeight);
//        }

    }
}
