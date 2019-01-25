package com.aizen.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by ld on 2018/10/26.
 *
 * @author ld
 * @date 2018/10/26
 * 描    述：
 */
public class LoggerUtils {
    /**
     * Logger
     * @param title  输入
     * @param outMsg 输出
     */
    public static void Logger(String title,Object outMsg){
        Logger.d(title + "--->[" + outMsg +"]");
    }

}
