package com.aizen.common.mvpbase;

import android.app.Application;
import android.content.Context;

import com.aizen.common.BuildConfig;
import com.aizen.common.manager.ActivityManager;
import com.aizen.utils.LoggerUtils;
import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.smtt.sdk.QbSdk;

import androidx.multidex.MultiDex;

/**
 * Created by ld on 2018/11/22.
 *
 * @author ld
 * @date 2018/11/22
 * 描    述：
 */
public class BaseApplication extends Application {
    //全局唯一
    private static BaseApplication application;

    private ActivityManager mActivityManager;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
        //MultiDex分包方法 必须最先初始化
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mActivityManager = new ActivityManager();
        initARouter();
        initLogger();
        Utils.init(this);
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                LoggerUtils.Logger("X5Init","Success");
            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        exitApp();
    }

    private void exitApp() {
        mActivityManager.finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public ActivityManager getActivityManager() {
        if(null == mActivityManager){
            mActivityManager = new ActivityManager();
        }
        return mActivityManager;
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .logStrategy(new LogcatLogStrategy())
                .tag("Aizen")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
//            @Override
//            public boolean isLoggable(int priority, String tag) {
//                //DEBUG模式下不打印LOG
//                return BuildConfig.DEBUG;
//            }
        });
    }

    private void initARouter() {
        if(BuildConfig.DEBUG){
//            ARouter.openLog();
//            ARouter.openDebug();
        }
//        ARouter.init(application);
    }

    /**
     * 获取全局唯一上下文
     *
     * @return BaseApplication
     */
    public static BaseApplication getApplication() {
        return application;
    }
}
