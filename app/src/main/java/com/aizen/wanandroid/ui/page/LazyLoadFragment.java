package com.aizen.wanandroid.ui.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by ld on 2018/12/19.
 *
 * @author ld
 * @date 2018/12/19
 * 描    述：
 */
public abstract class LazyLoadFragment extends Fragment {

    /**
     * 视图是否初始化
     */
    protected boolean initOK = false;
    protected boolean isLoad = false;
    private View mRootView;
    protected Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //加载布局
        mRootView = inflater.inflate(setLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this,mRootView);
        initOK = true;
        isCanLoadData();//初始化的时候去加载数据
        return mRootView;
    }


    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!initOK) {//未加载
            return;
        }
        if (getUserVisibleHint()) {
            lazyLoad(mRootView);
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }

    /**
     * 视图销毁的时候讲Fragment是否初始化的状态变为false
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        initOK = false;
        isLoad = false;
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    protected abstract int setLayoutId();


    /**
     * 找出对应的控件
     *
     * @param id 控件id
     * @param <T> 控件类型
     * @return 控件
     */
    protected <T extends View> T findViewById(int id) {

        return (T) mRootView.findViewById(id);
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad(View view);

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {
    }
}


