package com.aizen.wanandroid.frame.factory;

import com.aizen.wanandroid.frame.BasePresenter;
import com.aizen.wanandroid.frame.BaseView;
import com.aizen.wanandroid.frame.CreatePresenter;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public class PresenterFactory<V extends BaseView, P extends BasePresenter <V>> implements IPresenterFactory <V, P> {

    private final Class<P> mPresenterClass;

    public PresenterFactory(Class <P> presenterClass) {
        mPresenterClass = presenterClass;
    }

    public static <V extends BaseView,P extends BasePresenter<V>> PresenterFactory<V,P> createFactory(Class<?> viewClazz){
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if(annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterFactory<V,P>(aClass);
    }

    @Override
    public P createPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter create failed!，" +
                    "please check if declaration @CreatePresenter(xxx.class) anotation or not");
        }
    }
}
