package com.aizen.wanandroid.ui.aid;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.CompoundButton;

import com.aizen.common.mvpbase.BaseActivity;
import com.aizen.utils.GsonUtil;
import com.aizen.wanandroid.R;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.hawk.Hawk;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;

/**
 * Created by ld on 2019/1/4.
 *
 * @author ld
 * @date 2019/1/4
 * 描    述：
 */
public class ControlActivity extends BaseActivity {
    @BindView(R.id.cb_add_friends)
    AppCompatCheckBox mCbAddFriends;
    @BindView(R.id.ed_friends)
    AppCompatEditText mEdFriends;
    @BindView(R.id.btn_write)
    AppCompatButton mBtnWrite;
    @BindView(R.id.btn_reset)
    AppCompatButton mBtnReset;
    @BindView(R.id.btn_open_wechat)
    AppCompatButton mBtnOpenWechat;
    @BindView(R.id.cb_friends_square)
    AppCompatCheckBox mCbFriendsSquare;
    @BindView(R.id.cb_catch_red_packet)
    AppCompatCheckBox mCbCatchRedPacket;
    @BindView(R.id.btn_open_accessbility)
    Button mBtnOpenAccessbility;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCbAddFriends.setChecked(Hawk.get(AidConstant.ADD_FRIENDS,false));
        mCbFriendsSquare.setChecked(Hawk.get(AidConstant.FRIEND_SQUARE,false));
        mCbCatchRedPacket.setChecked(Hawk.get(AidConstant.RED_PACKET,false));

        mBtnWrite.setOnClickListener(v -> {
            Member member = GsonUtil.getGsonInstance().fromJson(mEdFriends.getText().toString(),Member.class);
            Hawk.put(AidConstant.MEMBER,member);
            ToastUtils.showShort("数据写入成功");
        });

        mBtnReset.setOnClickListener(v -> {
            Hawk.put(AidConstant.MEMBER,new Member());
            mEdFriends.setText("");
            ToastUtils.showShort("数据重置成功");
        });

        mBtnOpenWechat.setOnClickListener(v -> {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
            startActivity(intent);
        });

        mBtnOpenAccessbility.setOnClickListener(v -> {
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        });

        mCbAddFriends.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Hawk.put(AidConstant.ADD_FRIENDS,true);
            }else {
                Hawk.put(AidConstant.ADD_FRIENDS,false);
            }
        });
        mCbFriendsSquare.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Hawk.put(AidConstant.FRIEND_SQUARE,true);
            }else {
                Hawk.put(AidConstant.FRIEND_SQUARE,false);
            }
        });
        mCbCatchRedPacket.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Hawk.put(AidConstant.RED_PACKET,true);
            }else {
                Hawk.put(AidConstant.RED_PACKET,false);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aid_control;
    }
}
