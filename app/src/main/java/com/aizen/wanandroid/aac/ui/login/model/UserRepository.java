package com.aizen.wanandroid.aac.ui.login.model;

import android.content.Context;

import com.aizen.helper.SubscribeObserverProgress;
import com.aizen.utils.GsonUtil;
import com.aizen.utils.LoggerUtils;
import com.aizen.wanandroid.aac.BaseRepository;
import com.aizen.wanandroid.api.ApiRetrofit;
import com.aizen.wanandroid.api.entity.BaseWanModel;
import com.aizen.wanandroid.api.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;

/**
 * Created by ld on 2018/12/28.
 *
 * @author ld
 * @date 2018/12/28
 * 描    述：
 */

public class UserRepository extends BaseRepository {

    public void login(Context context, UserLogin userLogin,MutableLiveData<BaseWanModel<UserEntity>> liveData){
        Map<String,String> map = new HashMap <>();
        map.put("username",userLogin.getUsername());
        map.put("password",userLogin.getPassword());
        ApiRetrofit.setObservableSubscribe(
                mApi.login(map),
                new SubscribeObserverProgress<BaseWanModel<UserEntity>>(context) {
                @Override
                public void onSuccess(BaseWanModel<UserEntity> userEntity) {
                    liveData.postValue(userEntity);
                }
        });
    }
}
