package com.aizen.wanandroid.ui.notify;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.TrafficStats;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.aizen.helper.RxSchedulerHelper;
import com.aizen.utils.LoggerUtils;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.aizen.wanandroid.Constant.NotificationType.DEFAULT_CHANNEL_ID;

/**
 * Created by ld on 2018/12/22.
 *
 * @author ld
 * @date 2018/12/22
 * 描    述：
 */
public class SpeedService extends Service {
    private final String TAG = this.getClass().getSimpleName();

    private SimpleBinder mBinder;
    private Disposable mDisposable;
    private NotificationManager mManager;

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerUtils.Logger(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LoggerUtils.Logger(TAG,"onStartCommand");
        if(null == mManager){
            mManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        }
        if(mManager != null){
            Observable.interval(0,2,TimeUnit.SECONDS)
                    .compose(RxSchedulerHelper.io_main())
                    .subscribe(new Observer <Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(Long aLong) {
                            calculateDownUpSpeed();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        return super.onStartCommand(intent, flags, startId);
    }
    private long mDownloadBytesPre,mUploadBytesPre;
    private long mRxBytes, mSeBytes;

    private void calculateDownUpSpeed(){
        //设备开机以来接收的字节数
        mRxBytes = TrafficStats.getTotalRxBytes();
        //设备开机以来传输的字节数 - 收到的数据包
        mSeBytes = TrafficStats.getTotalTxBytes() - mRxBytes;
        double downloadSpeed = (mRxBytes - mDownloadBytesPre) / 2;
        double uploadSpeed = (mSeBytes - mUploadBytesPre) / 2;
        mDownloadBytesPre = mRxBytes;
        mUploadBytesPre = mSeBytes;
        if(downloadSpeed > 1024 * 1024){
            downloadSpeed /= (1024 * 1024);
            LoggerUtils.Logger("Speed-- Down",downloadSpeed + "MB/s");
        }else if(downloadSpeed < 1024 * 1024){
            downloadSpeed /= 1024;
            LoggerUtils.Logger("Speed-- Down",downloadSpeed + "KB/s");
        }else {
            LoggerUtils.Logger("Speed-- Down",downloadSpeed + "B/s");
        }
        if(uploadSpeed > 1024 * 1024){
            uploadSpeed /= (1024 * 1024);
            LoggerUtils.Logger("Speed-- Upload",uploadSpeed + "MB/s");
        }else if(uploadSpeed < 1024 * 1024){
            uploadSpeed /= 1024;
            LoggerUtils.Logger("Speed-- Upload",uploadSpeed + "KB/s");
        }else {
            LoggerUtils.Logger("Speed-- Upload",uploadSpeed + "B/s");
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LoggerUtils.Logger(TAG,"onDestroy");
        if(null != mDisposable){
            mDisposable.dispose();
        }
    }
    class SimpleBinder extends Binder {

        public void doTask() {
            Log.d(TAG, "doTask");
        }
    }
    //
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LoggerUtils.Logger(TAG,"onBind");
        if(mBinder != null){
            return mBinder;
        }
        return null;
}


    @Override
    public boolean onUnbind(Intent intent) {
        LoggerUtils.Logger(TAG,"onUnbind");
        return super.onUnbind(intent);
    }
}
