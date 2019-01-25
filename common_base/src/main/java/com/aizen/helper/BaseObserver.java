package com.aizen.helper;


import com.aizen.net.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ld on 2018/6/23.
 *
 * @author ld
 * @date 2018/6/23
 * 描    述：观察者基类
 */
public abstract class BaseObserver<T> implements Observer<T>,ISubscriber<T> {

    @Override
    public void onSubscribe(Disposable d) {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(T t) {
        doOnNext(t);
    }

    @Override
    public void onError(Throwable e) {
        String error = ApiException.handelException(e).getMessage();
        int code = ApiException.handelException(e).getCode();
        setError(error,code);
    }

    @Override
    public void onComplete() {
        doOnCompleted();
    }

    private void setError(String errorMsg,int code){
        doOnError(errorMsg, code);
    }
}
