package com.aizen.wanandroid.api.helper;

import com.aizen.net.exception.ServerException;
import com.aizen.utils.GsonUtil;
import com.aizen.utils.LoggerUtils;
import com.aizen.wanandroid.api.entity.BaseWanModel;

import io.reactivex.functions.Function;


/**
 * Creat by chen on 2018/9/30
 * Describe:
 * @author chenbo
 */
public class HandleFuc<T> implements Function<BaseWanModel<T>,BaseWanModel<T>> {
    @Override
    public BaseWanModel<T> apply(BaseWanModel<T> response) {
        if (response.errorCode == 404) {
            throw new ServerException(response.errorCode, response.errorMsg);
        }
        return response;
    }
}