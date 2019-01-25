package com.aizen.wanandroid.api.exception;

import com.aizen.net.ApiException;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public class FuncFlowableException<T> implements Function<Throwable, Flowable<T>> {

    @Override
    public Flowable<T> apply(Throwable throwable) {
        return Flowable.error(ApiException.handelException(throwable));
    }
}