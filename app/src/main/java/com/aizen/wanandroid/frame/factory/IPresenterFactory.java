package com.aizen.wanandroid.frame.factory;

import com.aizen.wanandroid.frame.BasePresenter;
import com.aizen.wanandroid.frame.BaseView;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public interface IPresenterFactory <V extends BaseView,P extends BasePresenter<V>> {
    /**
     * 创建 Presenter
     * @return Presenter
     */
    P createPresenter();
}
