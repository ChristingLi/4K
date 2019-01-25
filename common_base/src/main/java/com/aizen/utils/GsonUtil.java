package com.aizen.utils;

import com.google.gson.Gson;

/**
 * Created by ld on 2018/11/30.
 *
 * @author ld
 * @date 2018/11/30
 * 描    述：
 */
public class GsonUtil {
    private static class GsonHolder{
        private static final Gson INSTANCE = new Gson();
    }
    /**
     * 获取Gson实例，由于Gson是线程安全的，这里共同使用同一个Gson实例
     */
    public static Gson getGsonInstance() {
        return GsonHolder.INSTANCE;
    }
}
