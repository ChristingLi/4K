package com.aizen.wanandroid.aac.ui.test;

import android.app.Application;
import android.os.Bundle;

import com.aizen.wanandroid.MyApp;
import com.aizen.wanandroid.aac.BaseViewModel;
import com.aizen.wanandroid.api.entity.BoonEntity;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

/**
 * Created by ld on 2018/12/24.
 *
 * @author ld
 * @date 2018/12/24
 * 描    述：
 */
public class AccViewModel extends BaseViewModel {

    private Bundle mBundle;

    private Application mApplication;
    /**
     * 创建一个包含String的LiveData
     */
    private MutableLiveData <String> mCurrentName;

    private MutableLiveData <List <BoonEntity>> mBoonData = new MutableLiveData <>();

    private int mCurrentPage = 1;

    public AccViewModel(MyApp application, Bundle bundle) {
        super();
        mBundle = bundle;
        this.mApplication = application;
    }

    /**
     * 刷新数据
     * @return
     */
    public MutableLiveData<List <BoonEntity>> refreshBoonData(){
        mCurrentPage = 1;
//        ApiORetrofit.setFlowableSubscribe(mApi.obtainBoons(30,mCurrentPage),
//                new LodeMoreFlowableSubscriber <List <BoonEntity>>(mApplication) {
//                    @Override
//                    public void onSuccess(List <BoonEntity> boonEntities) {
//                        mBoonData.setValue(boonEntities);
//                    }
//                });
        return mBoonData;
    }

    /**
     * 加载更多数据
     * @return
     */
    public MutableLiveData <List <BoonEntity>> moreBoonData() {
        mCurrentPage += 1;
//        ApiORetrofit.setFlowableSubscribe(mApi.obtainBoons(30, mCurrentPage),
//                new LodeMoreFlowableSubscriber <List <BoonEntity>>(mApplication) {
//                    @Override
//                    public void onSuccess(List <BoonEntity> boonEntities) {
//                        mBoonData.setValue(boonEntities);
//                    }
//                    @Override
//                    public void onError(Throwable t) {
//                        super.onError(t);
//                        mCurrentPage -= 1;
//                    }
//                });
        return mBoonData;
    }

    /**
     * 获取当前的Name
     *
     * @return
     */
    public MutableLiveData <String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData <>();
        }
        return mCurrentName;
    }

}
