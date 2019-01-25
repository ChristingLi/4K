package com.aizen.common.mvpbase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.noober.background.BackgroundLibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;

/**
 * Created by ld on 2018/11/23.
 *
 * @author ld
 * @date 2018/11/23
 * 描    述：
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends LazyLoadFragment {

    protected LoadService mLoadService;

    protected P mPresenter;

    protected View mRootView;

    public Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            BackgroundLibrary.inject(this.getContext());
            mRootView = inflater.inflate(setLayoutId(), container, false);
            ButterKnife.bind(this, mRootView);
            initView();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        mLoadService = LoadSir.getDefault().register(mRootView, (Callback.OnReloadListener) v -> loadData());
        mLoadService.showSuccess();
        return mLoadService.getLoadLayout();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    /**
     * 创建 Presenter
     *
     * @return P
     */
    public abstract P createPresenter();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 绑定布局
     *
     * @return 0
     */
    protected abstract int setLayoutId();

    /**
     * 加载数据
     */
    protected abstract void loadData();
}
