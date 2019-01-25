package com.aizen.helper;

import io.reactivex.disposables.Disposable;

/**
 * Created by ld on 2018/2/26.
 *
 * @author ld
 * @date 2018/2/26
 * 描    述：RxJava统一返回处理
 */
public interface ISubscriber<T> {
    /**
     * doOnSubscribe
     * @param d
     */
    void doOnSubscribe(Disposable d);

    /**
     * doOnError
     * @param errorMsg
     * @param code
     */
    void doOnError(String errorMsg, int code);

    /**
     * doOnNext
     * @param t
     */
    void doOnNext(T t);

    /**
     * doOnCompleted
     */
    void doOnCompleted();
}
