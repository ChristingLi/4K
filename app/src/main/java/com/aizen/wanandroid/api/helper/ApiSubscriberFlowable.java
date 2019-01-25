package com.aizen.wanandroid.api.helper;

import android.content.Context;

import com.aizen.net.ApiException;
import com.aizen.utils.NetworkUtil;
import com.aizen.widget.StateView;
import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public abstract class ApiSubscriberFlowable<T> extends ResourceSubscriber<T> {
    private Context mContext;
    private StateView mStateView;
    private boolean isFirst;

    public ApiSubscriberFlowable(Context context, StateView stateView,boolean isFirst) {
        mContext = context;
        mStateView = stateView;
        this.isFirst = isFirst;
    }


    @Override
    protected void onStart() {
        super.onStart();
       if(isFirst){
           mStateView.showLoading();
       }
    }

    @Override
    public void onNext(T t) {
        if(t == null){
            mStateView.showEmpty();
            return;
        }
        mStateView.showContent();
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        if(!NetworkUtil.isNetworkAvailable(mContext)){
            ToastUtils.showShort("当前无网络连接,请先设置网络");
        }else {
            ApiException.handelException(t);
            ToastUtils.showShort(t.getMessage());
        }
        mStateView.showRetry();
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
