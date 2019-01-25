package com.aizen.wanandroid.frame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.aizen.utils.LoggerUtils;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.frame.factory.IPresenterFactory;
import com.aizen.wanandroid.frame.factory.PresenterFactory;
import com.aizen.wanandroid.frame.proxy.IPresenterProxy;
import com.aizen.wanandroid.frame.proxy.PresenterProxy;
import com.aizen.widget.StateView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>>
        extends AppCompatActivity implements IPresenterProxy<V,P>,BaseViewInterface {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认的工厂类
     */
    private PresenterProxy<V,P> mProxy = new PresenterProxy <>(PresenterFactory.<V, P>createFactory(getClass()));
    protected Activity mActivity;
    private CompositeDisposable mDisposables;

    private Unbinder mUnbinder;

    public SmartRefreshLayout mSmartRefreshLayout;
    public StateView mStateView;
    public Context mContext;
    public int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(getContentId() != 0){
            setContentView(getContentId());
        }
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext  = this;
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        mProxy.onCreate((V) this);
        if (getContentId() != 0) {
            View rootView = getWindow().getDecorView().getRootView();
            setStateView(rootView);
        }
        mUnbinder = ButterKnife.bind(mActivity);
        LoggerUtils.Logger("mContent1",mUnbinder);
        initView();
        initData(savedInstanceState);
        initSmart();
    }

    public void setStateView(View root){
        mStateView = StateView.inject(root);
        mStateView.setOnRetryClickListener(()->initData(null));
    }

    public void initSmart(){
        mSmartRefreshLayout = findViewById(R.id.smart_refresh);
        if(mSmartRefreshLayout != null){
            mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    onLoadMoreData(refreshLayout);
                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    onRefreshData(refreshLayout);
                }
            });
        }
    }
    /**
     * 子类自己实现，onLoadMoreData
     *
     * @param refreshLayout
     */
    protected void onLoadMoreData(RefreshLayout refreshLayout) {

    }

    protected void loadSuccess(List list){
        if(list.isEmpty()){
            mSmartRefreshLayout.setNoMoreData(true);
        }else {
            mSmartRefreshLayout.setNoMoreData(false);
        }
        mSmartRefreshLayout.finishRefresh(0);
    }

    protected void loadMoreSuccess(List list){
        page ++;
        if(list.isEmpty()){
            mSmartRefreshLayout.setNoMoreData(true);
            mSmartRefreshLayout.finishLoadMore(0);
        }else {
            mSmartRefreshLayout.setNoMoreData(false);
            mSmartRefreshLayout.finishLoadMore(0);
        }
    }
    /**
     * 子类不必实现，自动调用initData刷新数据
     *
     * @param refreshLayout
     */
    protected void onRefreshData(RefreshLayout refreshLayout) {
        page = 1;
        initData(null);
    }

    /**
     * 子类自己实现，是否使用加载更多,默认使用
     *
     * @return
     */
    protected boolean useLoadMore() {
        return true;
    }

    /**
     * RxJava 添加订阅
     */
    protected void addDispose(Disposable disposable) {
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        //将所有disposable放入,集中处理
        mDisposables.add(disposable);
    }

    protected void back() {
        if (mActivity != null) {
            mActivity.finish();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mProxy.onDestroy();
        if(null != mDisposables){
            mDisposables.dispose();
        }
    }

    @Override
    public void setPresenterFactory(IPresenterFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public IPresenterFactory <V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getPresenter() {
        return mProxy.getPresenter();
    }

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    public abstract void initData(Bundle savedInstanceState);

    protected int getContentId(){
        return getLayoutId();
    }
}
