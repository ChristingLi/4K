package com.aizen.wanandroid.ui.animation;

import android.animation.TimeInterpolator;

/**
 * Created by ld on 2018/12/20.
 *
 * @author ld
 * @date 2018/12/20
 * 描    述：正弦函数来实现先减速后加速
 * 插值器
 * 作用：设置 属性值 从初始值过渡到结束值 的变化规律
 * 如匀速、加速 & 减速 等等
 * 即确定了 动画效果变化的模式，如匀速变化、加速变化 等等
 * 本质：根据动画的进度（0%-100%）计算出当前属性值改变的百分比
 * 具体使用：自定义插值器需要实现 Interpolator / TimeInterpolator接口 & 复写getInterpolation（）
 * 补间动画 实现 Interpolator接口；属性动画实现TimeInterpolator接口
 * TimeInterpolator接口是属性动画中新增的，用于兼容Interpolator接口，这使得所有过去的Interpolator实现类都可以直接在属性动画使用
 */
public class DecelerateAccelerateInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float input) {
        float result;
        if(input <= 0.5){
            result = (float)(Math.sin(Math.PI * input)) / 2;
            // 使用正弦函数来实现先减速后加速的功能，逻辑如下：
            // 因为正弦函数初始弧度变化值非常大，刚好和余弦函数是相反的
            // 随着弧度的增加，正弦函数的变化值也会逐渐变小，这样也就实现了减速的效果。
            // 当弧度大于π/2之后，整个过程相反了过来，现在正弦函数的弧度变化值非常小，渐渐随着弧度继续增加，
            // 变化值越来越大，弧度到π时结束，这样从0过度到π，也就实现了先减速后加速的效果
        }else {
            result = (float)(2 - Math.sin((Math.PI * input))) /2;
         }

        return result;
    }
}
