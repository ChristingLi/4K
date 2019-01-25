package com.aizen.wanandroid.aac.ui.register.model;

import com.blankj.utilcode.util.StringUtils;

/**
 * Created by ld on 2019/1/2.
 *
 * @author ld
 * @date 2019/1/2
 * 描    述：注册数据实体
 */
public class RegisterEntity {

    public String tel;
    public String code;
    public String pwd1;
    public String pwd2;


    /**
     * 两次密码是否相同
     * @return
     */
    public boolean isCurretPwd(){
        if(!StringUtils.isEmpty(pwd1) && !StringUtils.isEmpty(pwd2)){
            if(pwd1.equals(pwd2)){
                return true;
            }
        }
        return false;
    }

}
