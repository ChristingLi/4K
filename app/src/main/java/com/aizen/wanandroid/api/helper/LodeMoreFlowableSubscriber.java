package com.aizen.wanandroid.api.helper;

import android.content.Context;

import com.aizen.net.ApiException;
import com.aizen.utils.NetworkUtil;
import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @author ld
 * @param <T>
 */
public abstract class LodeMoreFlowableSubscriber<T> extends ResourceSubscriber<T> {
    private Context context;

    public LodeMoreFlowableSubscriber(Context context) {
        this.context = context;
    }
    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onNext(T t) {
        if (t == null) {
            return;
        }
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        if (!NetworkUtil.isNetworkAvailable(context)) {
            ToastUtils.showShort("当前无网络连接，请先设置网络!");
        } else {
            ApiException.handelException(t);
            ToastUtils.showShort(t.getMessage());
        }
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    /**
     * 加载成功
     *
     * @return
     */
    public abstract void onSuccess(T t);
}
