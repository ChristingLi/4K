package com.aizen.helper;

import java.util.concurrent.TimeUnit;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ld on 2018/2/9.
 *
 * @author ld
 * @date 2018/2/9
 * 描    述：RxJava  的线程管理ObservableTransformer 用于切换RxJava线程 比较方便
 */
public class RxSchedulerHelper {
    /**
     *  线程切换 Observable
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 线程切换 Flowable
     * @param <T>
     * @retur
     */
    public static <T>FlowableTransformer<T,T> getFlowableScheduler(){
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * RxBinding
     * @param time
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> view_main(int time) {
        return upstream -> upstream
                .throttleFirst(time, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread());

    }
}
