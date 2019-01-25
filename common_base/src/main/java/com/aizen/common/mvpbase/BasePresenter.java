package com.aizen.common.mvpbase;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ld on 2018/11/23.
 *
 * @author ld
 * @date 2018/11/23
 * 描    述：
 */
public abstract class BasePresenter<M, V extends BaseView> {
    /**
     * 管理Destroy取消订阅者者
     */
    private CompositeDisposable disposables;

    protected M mModel;

    protected V mView;

    protected Context mContext;

    public BasePresenter(V view) {
        mView = view;
        initContext(view);
        mModel = createModel();
    }

    protected void initContext(V view) {
        if (view instanceof Activity) {
            //Activity
            mContext = (Activity) view;
        } else {
            mContext = ((Fragment) view).getActivity();
        }

    }

    public boolean addRxJava(Disposable disposable) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
        return true;
    }


    public void removeRxJava(Disposable disposable) {
        if (disposables != null) {
            disposables.remove(disposable);
        }
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * 可进行监听模型创建
     *
     * @return
     */
    protected abstract M createModel();

    /**
     * 与View视图分离
     */
    public void detachView() {
        if (disposables != null) {
            disposables.dispose();
            disposables = null;
        }
    }
}
