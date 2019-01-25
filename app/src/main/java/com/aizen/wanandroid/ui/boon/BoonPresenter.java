package com.aizen.wanandroid.ui.boon;

import android.content.Context;

import com.aizen.wanandroid.api.helper.ApiSubscriberFlowable;
import com.aizen.wanandroid.api.helper.LodeMoreFlowableSubscriber;
import com.aizen.wanandroid.api.ApiRetrofit;
import com.aizen.wanandroid.api.entity.BoonEntity;
import com.aizen.wanandroid.frame.BasePresenter;
import com.aizen.widget.StateView;

import java.util.List;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public class BoonPresenter extends BasePresenter<BoonView> {
    /**
     * 是否是第一次下拉刷新
     */
    private boolean isFirst = true;

    public void obtainBoonEntity(Context context, int page, StateView stateView){
        ApiRetrofit.setFlowableSubscribe(mApi.obtainBoons(30, page), new ApiSubscriberFlowable <List <BoonEntity>>(context,stateView, isFirst) {
            @Override
            public void onSuccess(List <BoonEntity> boonEntities) {
                isFirst = false;
                getView().onLoadSuccess(boonEntities);
            }
        });
    }

    public void obtainBoonMoreEntity(Context context,int page){
        ApiRetrofit.setFlowableSubscribe(mApi.obtainBoons(30, page), new LodeMoreFlowableSubscriber<List <BoonEntity>>(context) {
            @Override
            public void onSuccess(List <BoonEntity> boonEntities) {
                getView().onLoadMoreSuccess(boonEntities);
            }
        });
    }
}
