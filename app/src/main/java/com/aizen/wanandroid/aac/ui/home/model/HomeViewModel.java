package com.aizen.wanandroid.aac.ui.home.model;

import android.os.Bundle;

import com.aizen.wanandroid.MyApp;
import com.aizen.wanandroid.aac.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by ld on 2019/1/3.
 *
 * @author ld
 * @date 2019/1/3
 * 描    述：
 */
public class HomeViewModel extends BaseViewModel {

    private MyApp mMyApp;

    private Bundle mBundle;
    @Inject
    HomeRepository mHomeRepository;

    public HomeViewModel(MyApp myApp, Bundle bundle) {
        mMyApp = myApp;
        mBundle = bundle;
        DaggerHomeComponent.create().inject(this);
    }


}
