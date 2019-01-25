package com.aizen.wanandroid.api;

import com.aizen.wanandroid.api.entity.BaseGankModel;
import com.aizen.wanandroid.api.entity.BaseWanModel;
import com.aizen.wanandroid.api.entity.BoonEntity;
import com.aizen.wanandroid.api.entity.UserEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ld on 2018/12/6.
 *
 * @author ld
 * @date 2018/12/6
 * 描    述：
 */
public interface Api {
    String GANK_HOST = "use_host:gank";
    String WAN_HOST = "use_host:wan";
    /**
     * 获取福利
     * @param size
     * @param page
     * @return
     */
    @Headers(GANK_HOST)
    @GET("api/data/福利/{size}/{page}")
    Flowable<BaseGankModel<List<BoonEntity>>> obtainBoons(@Path("size")int size, @Path("page")int page);

    /**
     * 玩安卓登录接口
     * @return
     */
    @Headers(WAN_HOST)
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseWanModel<UserEntity>> login(@FieldMap Map<String, String> map);

}
