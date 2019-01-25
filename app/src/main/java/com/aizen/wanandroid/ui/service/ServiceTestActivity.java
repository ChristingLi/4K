package com.aizen.wanandroid.ui.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.aizen.common.mvpbase.BaseActivity;
import com.aizen.utils.LoggerUtils;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.ui.notify.SpeedService;
import com.blankj.utilcode.util.ServiceUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ld on 2018/12/23.
 *
 * @author ld
 * @date 2018/12/23
 * 描    述：
 */
public class ServiceTestActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.start)
    Button mStart;
    @BindView(R.id.stop)
    Button mStop;
    @BindView(R.id.bind)
    Button mBind;
    @BindView(R.id.unbind)
    Button mUnbind;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setTitle("测试Service");
        mStart.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mBind.setOnClickListener(this);
        mUnbind.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_test;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start:
                if(!ServiceUtils.isServiceRunning(SpeedService.class)){
                    startService(new Intent(mContext,SpeedService.class));
                }
                break;
            case R.id.stop:
                stopService(new Intent(mContext,SpeedService.class));
                break;
            case R.id.bind:
                bindService(new Intent(mContext,SpeedService.class),mServiceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(mServiceConnection);
                break;
                default: break;
        }
    }
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LoggerUtils.Logger("SpeedService","onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LoggerUtils.Logger("SpeedService","onServiceDisconnected");
        }
    };
}
