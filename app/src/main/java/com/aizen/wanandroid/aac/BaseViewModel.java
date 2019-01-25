package com.aizen.wanandroid.aac;

import com.aizen.utils.LoggerUtils;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ld on 2018/12/24.
 *
 * @author ld
 * @date 2018/12/24
 * 描    述：
 */
public class BaseViewModel extends ViewModel {

    protected CompositeDisposable mDisposables;

    public BaseViewModel() {
        mDisposables = new CompositeDisposable();
    }

    public void addRxDisposable(Disposable disposable){
        if(null != mDisposables){
            mDisposables.add(disposable);
        }
    }

    private void removeAllDisposables(){
        if(null != mDisposables){
            mDisposables.dispose();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LoggerUtils.Logger("ModelClear","Clear");
        removeAllDisposables();
    }
}
