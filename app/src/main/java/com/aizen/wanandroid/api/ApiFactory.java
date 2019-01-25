package com.aizen.wanandroid.api;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public class ApiFactory {
    private static final Object MONITOR = new Object();

    private static Api api = null;

    public static Api getApi(){
        synchronized (MONITOR){
            if(api == null){
                api = new ApiRetrofit().getApi();
            }
            return api;
        }
    }
}
