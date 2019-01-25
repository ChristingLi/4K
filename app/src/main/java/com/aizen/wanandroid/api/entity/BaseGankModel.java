package com.aizen.wanandroid.api.entity;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public class BaseGankModel<T> {
    public boolean error;
    public T results;

    public boolean isFalse() {
        return error;
    }

}