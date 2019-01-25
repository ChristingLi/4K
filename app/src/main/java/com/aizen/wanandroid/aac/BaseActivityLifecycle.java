package com.aizen.wanandroid.aac;

import android.content.Context;

import com.aizen.utils.LoggerUtils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by ld on 2018/12/24.
 *
 * @author ld
 * @date 2018/12/24
 * 描    述：BaseActivity生命周期监测者
 *  定义监听的Activity的函数
 */
public class BaseActivityLifecycle implements LifecycleObserver {

    private Context mContext;

    public BaseActivityLifecycle(Context context) {
        mContext = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        LoggerUtils.Logger("BaseActivityLifecycle",mContext.getClass().getSimpleName() + " -- onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        LoggerUtils.Logger("BaseActivityLifecycle",mContext.getClass().getSimpleName() + " -- onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        LoggerUtils.Logger("BaseActivityLifecycle",mContext.getClass().getSimpleName() + " -- onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        LoggerUtils.Logger("BaseActivityLifecycle",mContext.getClass().getSimpleName() + " -- onDestroy");
    }
}
