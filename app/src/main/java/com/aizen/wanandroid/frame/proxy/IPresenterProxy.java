package com.aizen.wanandroid.frame.proxy;

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
public interface IPresenterProxy <V extends BaseView,P extends BasePresenter<V>> {
    /**
     * 创建Presenter的工长
     * @param presenterFactory
     */
    void setPresenterFactory(IPresenterFactory<V,P> presenterFactory);

    /**
     * 获取Presenter工程类
     * @return
     */
    IPresenterFactory<V,P> getPresenterFactory();

    /**
     * 获取Presenter
     * @return
     */
    P getPresenter();
}
