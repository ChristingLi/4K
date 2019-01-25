package com.aizen.common.mvpbase;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Created by ld on 2018/11/23.
 *
 * @author ld
 * @date 2018/11/23
 * 描    述：
 */

public abstract class BaseMvpActivity <P extends BasePresenter> extends BaseActivity{

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    /**
     * 创建 Presenter
     *
     * @return
     */
    public abstract P createPresenter();
    /**
     * 初始化数据
     */
    protected abstract void initData();
    /**
     * 初始化操作
     */
    protected abstract void initView();
}
