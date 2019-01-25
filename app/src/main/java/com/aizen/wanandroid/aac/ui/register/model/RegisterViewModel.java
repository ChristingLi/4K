package com.aizen.wanandroid.aac.ui.register.model;

import android.os.Bundle;

import com.aizen.wanandroid.MyApp;
import com.aizen.wanandroid.aac.BaseViewModel;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;

/**
 * Created by ld on 2019/1/2.
 *
 * @author ld
 * @date 2019/1/2
 * 描    述：
 */
public class RegisterViewModel extends BaseViewModel {

    private MyApp mMyApp;

    private Bundle mBundle;

    public MutableLiveData<RegisterEntity> mRegisterLD = new MutableLiveData <>();

    @Inject
    RegisterRepository mRegisterRepository;

    public RegisterViewModel(MyApp myApp, Bundle bundle) {
        mMyApp = myApp;
        mBundle = bundle;
        DaggerRegisterComponent.create().injectRegisterVM(this);
    }




}
