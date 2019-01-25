package com.aizen.wanandroid.ui.notify;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.aizen.common.mvpbase.BaseActivity;
import com.aizen.helper.RxSchedulerHelper;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.ui.boon.BoonActivity;
import com.jakewharton.rxbinding3.view.RxView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import butterknife.BindView;

import static com.aizen.wanandroid.Constant.NotificationType.DEFAULT_CHANNEL_ID;
import static com.aizen.wanandroid.Constant.NotificationType.DEFAULT_CHANNEL_NAME;
import static com.aizen.wanandroid.Constant.NotificationType.DEFAULT_RESIDENT_ID;

/**
 * Created by ld on 2018/12/22.
 *
 * @author ld
 * @date 2018/12/22
 * 描    述：
 */
public class NotificationManagerActivity extends BaseActivity {
    @BindView(R.id.open_notify)
    Button mOpenNotify;
    @BindView(R.id.close_notify)
    Button mCloseNotify;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private NotificationManager mManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolbar.setTitle("状态栏Nav");

        addRxJava(RxView.clicks(mOpenNotify).compose(RxSchedulerHelper.view_main(1))
        .subscribe(o-> createResidentNav()));

        addRxJava(RxView.clicks(mCloseNotify).compose(RxSchedulerHelper.view_main(1))
        .subscribe(o-> getManager().cancel(DEFAULT_RESIDENT_ID)));

    }

    /**
     * 创建Nav
     */
    private void createResidentNav() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_notification);
        remoteViews.setImageViewResource(R.id.place_icon,R.mipmap.ic_launcher);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext,DEFAULT_CHANNEL_NAME);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews)
                .setTicker("Default_Nav")
                .setAutoCancel(false)
                .setOngoing(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_LOW;
            createNotificationChannel(DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME,importance);
            builder.setChannelId(DEFAULT_CHANNEL_ID);
        }
        mManager.notify(DEFAULT_RESIDENT_ID,builder.build());
    }

    /**
     * 获取Manager
     * @return
     */
    private NotificationManager getManager(){
        if(null == mManager){
            mManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    /**
     * 如果通知栏被禁止,先进行启用 后进行通知
     */
    private void notifyWithisHaved() {
        NotificationManager manager = getManager();
        //to open 通知设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel("chat");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁沿线30万商铺抢购中！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.route)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.route))
                .setAutoCancel(true)
                .build();
        manager.notify(2, notification);
    }

    /**
     * 通知栏点击跳转意图
     */
    private void notifyWithIntent() {
        Intent intent = new Intent(mContext,BoonActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent,0);
        NotificationManager manager = getManager();
        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        manager.notify(1, notification);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance){
        NotificationChannel channel = new NotificationChannel(channelId,channelName,importance);
        getManager().createNotificationChannel(channel);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification_manager;
    }
}
