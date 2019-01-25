package com.aizen.net;

import com.aizen.utils.LoggerUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ld on 2018/11/23.
 *
 * @author ld
 * @date 2018/11/23
 * 描    述： 网络初始化单例
 */
public class NetClient {

    private OkHttpClient mOkHttpClient;


    private final static int DEFAULT_TIME = 15;

    private NetClient() {
        initOkHttpClient();
    }

    /**
     *  初始化OkHttp
     */
    private void initOkHttpClient() {
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(message ->
                        LoggerUtils.Logger("Net", message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                .build();
        LoggerUtils.Logger("OkHttpClient","网络组件初始化成功");
    }

    /**
     * 暴露API接口
     * @param baseUrl
     * @return
     */
    public ApiService exposeService(String baseUrl) {
        return getRetrofit(baseUrl).create(ApiService.class);
    }

    /**
     * 穿件RetrofitClient
     * @param baseUrl
     * @return
     */
    public Retrofit getRetrofit(String baseUrl) {
        if (null != mOkHttpClient) {
            initOkHttpClient();
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    private static class HolderClass {
        private final static NetClient INSTANCE = new NetClient();
    }

    public static NetClient getInstance() {
        return HolderClass.INSTANCE;
    }

}
