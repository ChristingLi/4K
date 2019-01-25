package com.aizen.wanandroid.aac.ui.login.model;

import dagger.Component;

/**
 * Created by ld on 2018/12/29.
 *
 * @author ld
 * @date 2018/12/29
 * 描    述：
 */
@Component(modules =  UserProvider.class)
public interface UserComponent {

    void injectViewModel(LoginViewModel model);

}
