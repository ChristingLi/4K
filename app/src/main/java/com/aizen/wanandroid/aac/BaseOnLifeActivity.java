package com.aizen.wanandroid.aac;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ld on 2018/12/24.
 *
 * @author ld
 * @date 2018/12/24
 * 描    述：
 */
public abstract class BaseOnLifeActivity extends AppCompatActivity {

    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        mUnbinder = ButterKnife.bind(this);
        //注册BaseLifeCycle
        getLifecycle().addObserver(new BaseActivityLifecycle(this));
    }

    protected abstract int bindLayout();

    protected abstract void initViewsAndData();

    protected abstract void subscribeLiveData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != mUnbinder){
            mUnbinder.unbind();
        }
    }
}
