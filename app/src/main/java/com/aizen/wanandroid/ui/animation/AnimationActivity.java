package com.aizen.wanandroid.ui.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;

import com.aizen.common.mvpbase.BaseActivity;
import com.aizen.wanandroid.R;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * Created by ld on 2018/12/20.
 *
 * @author ld
 * @date 2018/12/20
 * 描    述：
 */
public class AnimationActivity extends BaseActivity {

    @BindView(R.id.btn_animation)
    Button mBtnAnimation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        float curTranslationX = mBtnAnimation.getTranslationX();
        // 设置插值器
        // 创建动画对象 & 设置动画
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴平移
        // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBtnAnimation,"translationX",curTranslationX,300,curTranslationX);
        animator.setDuration(5000);
        animator.setInterpolator(new DecelerateAccelerateInterpolator());
        animator.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animation;
    }

}
