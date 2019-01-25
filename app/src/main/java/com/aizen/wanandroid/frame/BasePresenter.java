package com.aizen.wanandroid.frame;

import android.os.Bundle;

import com.aizen.utils.LoggerUtils;
import com.aizen.wanandroid.api.Api;
import com.aizen.wanandroid.api.ApiFactory;

import androidx.annotation.Nullable;


/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public abstract class BasePresenter<V extends BaseView> {

    private V baseView;
    private static final String TAG = "BasePresenter";
    protected Api mApi;

    public BasePresenter(){
        mApi = ApiFactory.getApi();
    }

    public void onCreatePresenter(@Nullable Bundle saveState){

    }

    public void onBindView(V view){
        this.baseView = view;
    }

    public void unBindView(){
        this.baseView = null;
    }

    public void onDestroyPresenter(){
        LoggerUtils.Logger(TAG,"---------> onDestroyPresenter");
    }

    public void onSaveInstanceState(Bundle outState){
        LoggerUtils.Logger(TAG,"---------> onSaveInstanceState");
    }

    public V getView(){
        return baseView;
    }
}
