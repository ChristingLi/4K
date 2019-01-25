package com.aizen.wanandroid.ui.animation;

import android.animation.TypeEvaluator;

/**
 * Created by ld on 2018/12/20.
 *
 * @author ld
 * @date 2018/12/20
 * 描    述：自定义估值器
 * 根据 插值器计算出当前属性值改变的百分比 & 初始值 & 结束值 来计算 当前属性具体的数值
 * 如：动画进行了50%（初始值=100，结束值=200 ），那么匀速插值器计算出了当前属性值改变的百分比是50%，
 * 那么估值器则负责计算当前属性值 = 100 + （200-100）x50% = 150.
 */
public class PointEvaluator implements TypeEvaluator {
    //重写evaluate
    //在此处写对象动画过度的逻辑

    /**
     *
     * @param fraction 表示动画完成度（根据它来计算当前动画的值）
     * @param startValue 动画的初始值
     * @param endValue 动画的结束值
     * @return
     */
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;

        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = endPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        //返回对象动画过渡的逻辑计算后的值
        return new Point(x,y);
    }
}
