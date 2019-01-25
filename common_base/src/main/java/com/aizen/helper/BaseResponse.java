package com.aizen.helper;

/**
 * Created by ld on 2018/2/26.
 *
 * @author ld
 * @date 2018/2/26
 * 描    述：统一返回数据结构 暂未使用！
 */
public class BaseResponse {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误描述
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseResponse{" + "code=" + code + ", msg='" + msg + '\'' + '}';
    }
}
