package com.aizen.wanandroid.api.helper;

import com.aizen.net.exception.ServerException;
import com.aizen.wanandroid.api.entity.BaseGankModel;

import io.reactivex.functions.Function;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public class HandleGankFuc<T> implements Function<BaseGankModel<T>, T> {
    @Override
    public T apply(BaseGankModel<T> response) {
        if (response.isFalse()) {
            throw new ServerException(400, "error");
        }
        return response.results;
    }
}
