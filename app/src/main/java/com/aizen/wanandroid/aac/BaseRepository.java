package com.aizen.wanandroid.aac;

import com.aizen.wanandroid.api.Api;
import com.aizen.wanandroid.api.ApiFactory;

/**
 * Created by ld on 2018/12/29.
 *
 * @author ld
 * @date 2018/12/29
 * 描    述：
 */
public abstract class BaseRepository {

    public Api mApi;

    public BaseRepository() {
        mApi = ApiFactory.getApi();
    }
}
