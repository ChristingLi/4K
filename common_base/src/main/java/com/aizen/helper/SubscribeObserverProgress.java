package com.aizen.helper;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aizen.net.ApiException;
import com.aizen.utils.GsonUtil;
import com.aizen.utils.LoggerUtils;
import com.aizen.utils.NetworkUtil;
import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：Abs Subscriber
 */
public abstract class SubscribeObserverProgress<T> implements Observer<T> {

    private Context mContext;
    private Disposable mDisposable;
//    private MaterialDialog mDialog;

    public SubscribeObserverProgress(Context context) {
        mContext = context;
    }

    @Override
    public void onError(Throwable e) {
        if(!NetworkUtil.isNetworkAvailable(mContext)){
            ToastUtils.showShort("当前无网络连接,请先设置网络");
        }else{
            ApiException.handelException(e);
            ToastUtils.showShort(ApiException.handelException(e).getMessage());
        }
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.mDisposable = d;
//        mDialog = new MaterialDialog.Builder(mContext)
//                .progress(true,0)
//                .content("请稍后")
//                .canceledOnTouchOutside(false)
//                .cancelable(false)
//                .show();
    }

    @Override
    public void onNext(T t) {
        if(t == null){
            return;
        }
        onSuccess(t);
    }

    /**
     * 关闭流
     */
    private void dispose() {
        if(mDisposable != null){
            mDisposable.dispose();
        }
//        if(mDialog != null){
//            mDialog.dismiss();
//        }
    }

    /**
     * 加载成功
     * @param t
     */
    public abstract void onSuccess(T t);
}
