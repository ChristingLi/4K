package com.aizen.wanandroid.aac.ui.gradient.model;

import android.os.Bundle;

import com.aizen.wanandroid.MyApp;
import com.aizen.wanandroid.aac.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by ld on 2019/1/2.
 *
 * @author ld
 * @date 2019/1/2
 * 描    述：
 */
public class GradientViewModel extends BaseViewModel {

    private MyApp mMyApp;

    private Bundle mBundle;

    @Inject
    GradientRepository mGradientRepository;

    public GradientViewModel(MyApp myApp, Bundle bundle) {
        mMyApp = myApp;
        mBundle = bundle;
        DaggerGradientComponent.create().injectGradientViewModel(this);
    }
}
