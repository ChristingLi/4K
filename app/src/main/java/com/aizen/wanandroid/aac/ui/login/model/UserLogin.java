package com.aizen.wanandroid.aac.ui.login.model;

import com.blankj.utilcode.util.StringUtils;

/**
 * Created by ld on 2018/12/28.
 *
 * @author ld
 * @date 2018/12/28
 * 描    述：
 */
public class UserLogin {

    private String username;

    private String password;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        if(StringUtils.isEmpty(username) && StringUtils.isEmpty(password)){
            return false;
        }
        return true;
    }
}
