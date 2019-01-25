package com.aizen.wanandroid.ui.design;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Created by ld on 2018/12/20.
 *
 * @author ld
 * @date 2018/12/20
 * 描    述：简介
 */
public class CarsonView extends View {
    //如果View是在Java代码里面new的，则调用第一个构造函数
    public CarsonView(Context context) {
        super(context);
    }
    // 如果View是在.xml里声明的，则调用第二个构造函数
    // 自定义属性是从AttributeSet参数传进来的
    public CarsonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
    public CarsonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //API21之后才使用
    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CarsonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 基础知识
     */
    public void mean(){
//        Android坐标系
//        0
//         ------->
//	      |      x
//        |
//	      |
//	      |
//        v y
//
//        左上角为坐标原点
//        向右为x轴增大方向
//        向下为y轴增大方向(区别数学坐标系y轴向上)
        //获取Top位置
        getTop();
        //View的左边距离父布局左侧位置
        getLeft();
        //View的右边距离父布局左边位置
        getRight();
        //View的底边距离父布局Top位置
        getBottom();
        /**
         *  MotionEvent => event
         *  触摸点对于其所在组件的坐标
         *  event.getX()
         *  event.getY()
         *  触摸点对于屏幕默认坐标系的坐标
         *  event.getRawX()
         *  event.getRawY()
         */

        /**
         * 角度 弧度
         *
         * 默认屏幕坐标系增大方向为顺时针
         */

        /**
         * 颜色
         *
         * ARGB8888  四通道高精度(32位)
         * ARGB4444  四通道低精度(16位)
         * RGB565    Android屏幕默认模式(16位)
         * Alpha     仅有透明通道(8位)
         */

        /**
         * MeasureSpec
         * 测量规格类 -> 测量View大小的依据
         * 作用: 决定一个View的大小
         * 类型:
         * widthMeasureSpec
         * heightMeasureSpec
         * 每个MeasureSpec代表了一组高度&宽度,决定整个视图的大小
         *
         * 测量规格(MeasureSpec) = 测量模式(mode)+测量大小(size)
         * //1 获取测量模式
         * int specMode = MeasureSpec.getMode(measureSpec);
         * //2 获取测量大小
         * int specSize = MeasureSpec.getSize(MeasureSpec);
         * //3 通过Mode和size生成新的SpecMode
         * int measureSpec = MeasureSpec.makeMeasureSpec(size,mode);
         */
    }


}
