package com.aizen.wanandroid.frame.proxy;

import android.os.Bundle;

import com.aizen.wanandroid.frame.BasePresenter;
import com.aizen.wanandroid.frame.BaseView;
import com.aizen.wanandroid.frame.factory.IPresenterFactory;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public class PresenterProxy<V extends BaseView,P extends BasePresenter<V>> implements IPresenterProxy<V,P>{
    private static final String PRESENTER_KEY = "presenter_key";
    private IPresenterFactory<V,P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsBindView;

    public PresenterProxy(IPresenterFactory<V, P> mFactory) {
        this.mFactory = mFactory;
    }

    /**
     * 设置Presenter的工厂类,这个方法只能在创建Presenter之前调用,也就是调用getPresenter()之前，如果Presenter已经创建则不能再修改
     * @param presenterFactory
     */
    @Override
    public void setPresenterFactory(IPresenterFactory <V, P> presenterFactory) {
        if(mPresenter != null){
            throw new IllegalArgumentException("setPresenterFactory() can only be called before getPresenter() be called");
        }
        this.mFactory = presenterFactory;
    }
    /**
     * 获取Presenter工厂类
     *
     * @return
     */
    @Override
    public IPresenterFactory <V, P> getPresenterFactory() {
        return mFactory;
    }

    @Override
    public P getPresenter() {
        if(mFactory != null){
            if(mPresenter == null){
                mPresenter = mFactory.createPresenter();
            }
        }
        return mPresenter;
    }

    /**
     * 绑定Presenter View
     * @param view
     */
    public void onCreate(V view){
        getPresenter();
        if(mPresenter != null && !mIsBindView && view != null){
            mPresenter.onBindView(view);
            mIsBindView = true;
        }
    }

    /**
     * 销毁Presenter持有View
     */
    private void onUnbindView(){
        if(mPresenter != null && mIsBindView){
            mPresenter.unBindView();
            mIsBindView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy(){
        if(mPresenter != null){
            onUnbindView();
            mPresenter.onDestroyPresenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁时调用
     * @return
     */
    public Bundle onSaveInstanceState(){
        Bundle bundle = new Bundle();
        getPresenter();
        if(mPresenter != null){
            Bundle presenterBundle = new Bundle();
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY,presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭回复Presenter
     * @param saveInstanceState
     */
    public void onRestoreInstanceState(Bundle saveInstanceState){
        mBundle = saveInstanceState;
    }
}
