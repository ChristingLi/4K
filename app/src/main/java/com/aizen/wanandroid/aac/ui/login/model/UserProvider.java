package com.aizen.wanandroid.aac.ui.login.model;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ld on 2018/12/29.
 *
 * @author ld
 * @date 2018/12/29
 * 描    述：
 */
@Module
public class UserProvider {
    @Provides
    public UserRepository getUse(){
        return new UserRepository();
    }
}
