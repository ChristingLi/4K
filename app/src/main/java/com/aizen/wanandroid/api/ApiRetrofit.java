package com.aizen.wanandroid.api;

import com.aizen.helper.RxSchedulerHelper;
import com.aizen.utils.LoggerUtils;
import com.aizen.utils.NetworkUtil;
import com.aizen.wanandroid.MyApp;
import com.aizen.wanandroid.api.entity.BaseGankModel;
import com.aizen.wanandroid.api.entity.BaseWanModel;
import com.aizen.wanandroid.api.exception.FuncFlowableException;
import com.aizen.wanandroid.api.exception.FuncObservableException;
import com.aizen.wanandroid.api.helper.HandleFuc;
import com.aizen.wanandroid.api.helper.HandleGankFuc;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aizen.wanandroid.Constant.GANK_BASE_URL;
import static com.aizen.wanandroid.Constant.WAN_BASE_URL;


/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public class ApiRetrofit {
    private static  final int DEFAULT_TIMEOUT = 20;
    private static final String CACHE_NAME = "okhttp";
    private Api mApi;

    public Api getApi() {
        return mApi;
    }

    ApiRetrofit(){
        File cacheDirectory = new File(MyApp.getApplication().getCacheDir(),CACHE_NAME);
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheDirectory,cacheSize);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                //错误重连
                .retryOnConnectionFailure(true)
//                .addInterceptor(setheaderinterceptor())
                .addInterceptor(new BaseUrlInterceptor())
                .addInterceptor(new HttpLoggingInterceptor(message ->
                        LoggerUtils.Logger("Net", message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(rewriteCacheControlInterceptor)
                .cache(cache).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WAN_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mApi = retrofit.create(Api.class);
    }

    /**
     * 切换BASE URL
     */
    private static class BaseUrlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //获取request
            Request request = chain.request();
            //从request中获取原有的HttpUrl实例oldHttpUrl
            HttpUrl oldHttpUrl = request.url();
            //获取request的创建者builder
            Request.Builder builder = request.newBuilder();
            //从request中获取headers，通过给定的键url_name
            List<String> headerValues = request.headers("use_host");
            if (headerValues != null && headerValues.size() > 0) {
                //如果有这个header，先将配置的header删除，因此header仅用作app和ok http之间使用
                builder.removeHeader("use_host");
                //匹配获得新的BaseUrl
                String headerValue = headerValues.get(0);
                HttpUrl newBaseUrl = null;
                if ("gank".equals(headerValue)) {
                    newBaseUrl = HttpUrl.parse(GANK_BASE_URL);
                } else if ("wan".equals(headerValue)) {
                    newBaseUrl = HttpUrl.parse(WAN_BASE_URL);
                } else {
                    newBaseUrl = oldHttpUrl;
                }
                //重建新的HttpUrl，修改需要修改的url部分
                HttpUrl newFullUrl = oldHttpUrl
                        .newBuilder()
                        //更换网络协议
                        .scheme(newBaseUrl.scheme())
                        //更换主机名
                        .host(newBaseUrl.host())
                        //更换端口
                        .port(newBaseUrl.port())
//                        .removePathSegment(0)//移除第一个参数
                        .build();
                //重建这个request，通过builder.url(newFullUrl).build()；
                // 然后返回一个response至此结束修改
                return chain.proceed(builder.url(newFullUrl).build());
            }
            return chain.proceed(request);
        }
    }

    private Interceptor setheaderinterceptor() {
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
//                        .header("Authorization", "MzY2NTkgMiBIZ2pZVVJ0UA==")
                        .header("loginUserName", "")
                        .header("loginUserPassword", "")
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        return headerInterceptor;
    }
    /**
     * cache
     */
    private Interceptor rewriteCacheControlInterceptor = chain -> {
        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
        cacheBuilder.maxStale(7, TimeUnit.DAYS);
        CacheControl cacheControl = cacheBuilder.build();
        Request request = chain.request();
        if (!NetworkUtil.isNetworkAvailable(MyApp.getApplication())) {
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetworkUtil.isNetworkAvailable(MyApp.getApplication())) {
            int maxAge = 0;
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28;
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };

    public static <T> void setFlowableSubscribe(Flowable<BaseGankModel<T>> observable, FlowableSubscriber<T> observer) {
        observable.compose(RxSchedulerHelper.getFlowableScheduler())
                .onErrorResumeNext(new FuncFlowableException<>())
                .map(new HandleGankFuc<>())
                .subscribe(observer);
    }

    public static <T> void setObservableSubscribe(Observable<BaseWanModel<T>> observable, Observer<BaseWanModel<T>> observer) {
        observable.compose(RxSchedulerHelper.io_main())
                .onErrorResumeNext(new FuncObservableException<>())
                .map(new HandleFuc<>())
                .subscribe(observer);
    }

//    public static void setObservableBooleanSubscribe(Observable<BaseModel> observable, Observer<Boolean> observer) {
//        observable.compose(ApiScheduler.getObservableScheduler())
//                .onErrorResumeNext(new FuncObservableException<>())
//                .map(new HandleBooleanFuc())
//                .subscribe(observer);
//    }
}
