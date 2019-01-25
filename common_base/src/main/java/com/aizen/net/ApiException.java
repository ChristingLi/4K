package com.aizen.net;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.EOFException;
import java.io.IOException;
import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Objects;

import retrofit2.HttpException;

/**
 * Created by ld on 2018/6/23.
 *
 * @author ld
 * @date 2018/6/23
 * 描    述：自定义Exception 约定
 */
public class ApiException extends Exception{

    private final int code;

    private String message;

    public ApiException(Throwable throwable, int code){
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    /**
     * 向外提供Code
     * @return
     */
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static ApiException handelException(Throwable e){
        ApiException ex;
        if(e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            ex = new ApiException(httpException,httpException.code());
            Logger.e(" [ApiException ] -- " + ex.getMessage());
            try {
                ex.message = Objects.requireNonNull(httpException.response().errorBody()).string();
            } catch (IOException e1) {
                e1.printStackTrace();
                ex.message = e1.getMessage();
                Logger.e(" [ApiException ] -- " + e1.getMessage());
            }
            return ex;
        }else if (e instanceof SocketTimeoutException) {
            ex = new ApiException(e, Error.TIMEOUT_ERROR);
            ex.message = "网络连接超时，请检查您的网络状态，稍后重试！";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, Error.TIMEOUT_ERROR);
            ex.message = "网络连接异常，请检查您的网络状态，稍后重试！";
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ApiException(e, Error.TIMEOUT_ERROR);
            ex.message = "网络连接超时，请检查您的网络状态，稍后重试！";
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new ApiException(e, Error.TIMEOUT_ERROR);
            ex.message = "网络连接异常，请检查您的网络状态，稍后重试！";
            return ex;
        } else if (e instanceof NullPointerException) {
            ex = new ApiException(e, Error.NULL_POINTER_EXCEPTION);
            ex.message = "空指针异常";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ApiException(e, Error.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof ClassCastException) {
            ex = new ApiException(e, Error.CAST_ERROR);
            ex.message = "类型转换错误";
            return ex;
        } else if (e instanceof JsonParseException ||
                e instanceof JSONException ||
                e instanceof JsonSyntaxException ||
                e instanceof JsonSerializer ||
                e instanceof NotSerializableException ||
                e instanceof ParseException) {

            ex = new ApiException(e, Error.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof IllegalStateException) {
            ex = new ApiException(e, Error.ILLEGAL_STATE_ERROR);
            ex.message = e.getMessage();
            return ex;
        } else if (e instanceof EOFException) {
            ex = new ApiException(e, Error.RESPONSE_NO_BODY);
            ex.message = "该请求接口无响应实体";
            return ex;
        }else{
            ex = new ApiException(e, Error.UNKNOWN);
            Logger.d("未知错误: "+ex);
            ex.message = "未知错误";
            return ex;
        }
    }

    /**
     * 约定异常
     */
    public static class Error {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1001;
        /**
         * 空指针错误
         */
        public static final int NULL_POINTER_EXCEPTION = 1002;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1003;

        /**
         * 类转换错误
         */
        public static final int CAST_ERROR = 1004;

        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1005;

        /**
         * 非法数据异常
         */
        public static final int ILLEGAL_STATE_ERROR = 1006;
        /**
         * 无相应实体
         */
        public static final int RESPONSE_NO_BODY = 1007;


        public static final int UPLOAD_ERROR = 1008;
    }
}