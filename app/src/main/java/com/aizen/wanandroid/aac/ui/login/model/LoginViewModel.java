package com.aizen.wanandroid.aac.ui.login.model;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;

import com.aizen.utils.LoggerUtils;
import com.aizen.wanandroid.MyApp;
import com.aizen.wanandroid.aac.BaseViewModel;
import com.aizen.wanandroid.api.entity.BaseWanModel;
import com.aizen.wanandroid.api.entity.UserEntity;
import com.blankj.utilcode.util.ToastUtils;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;

/**
 * Created by ld on 2018/12/28.
 *
 * @author ld
 * @date 2018/12/28
 * 描    述：登录Model
 */
public class LoginViewModel extends BaseViewModel {

    private Application mApplication;

    private Bundle mBundle;
    @Inject
    UserRepository mUserRepository;

    public MutableLiveData<BaseWanModel<UserEntity>> mLiveUser = new MutableLiveData <>();

    public LoginViewModel(MyApp application, Bundle bundle) {
        mBundle = bundle;
        mApplication = application;
        DaggerUserComponent.create().injectViewModel(this);
    }


    public void makeLogin(UserLogin userLogin){
        mUserRepository.login(mApplication,userLogin,mLiveUser);
    }

}
