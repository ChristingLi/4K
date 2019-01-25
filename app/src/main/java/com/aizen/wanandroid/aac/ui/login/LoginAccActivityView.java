package com.aizen.wanandroid.aac.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.aizen.helper.RxSchedulerHelper;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.aac.BaseViewModelActivity;
import com.aizen.wanandroid.aac.ui.login.model.LoginViewModel;
import com.aizen.wanandroid.aac.ui.login.model.UserLogin;
import com.aizen.wanandroid.aac.ui.register.RegisterActivityView;
import com.aizen.widget.ClearEditText;
import com.aizen.widget.EditTextInputHelper;
import com.aizen.widget.bar.TitleBar;
import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding3.view.RxView;

import butterknife.BindView;

/**
 * Created by ld on 2018/12/28.
 *
 * @author ld
 * @date 2018/12/28
 * 描    述：
 */
public class LoginAccActivityView extends BaseViewModelActivity<LoginViewModel> {
    @BindView(R.id.login_title)
    TitleBar mLoginTitle;
    @BindView(R.id.edit_tel)
    ClearEditText mEditTel;
    @BindView(R.id.edit_pwd)
    ClearEditText mEditPwd;
    @BindView(R.id.tv_login_forget)
    TextView mTvLoginForget;
    @BindView(R.id.btn_login_commit)
    Button mBtnLoginCommit;

    private EditTextInputHelper mTextInputHelper;

    @Override
    protected Class <LoginViewModel> viewModelClass() {
        return LoginViewModel.class;
    }

    @Override
    protected Bundle arguments() {
        return getIntent().getExtras();
    }

    @Override
    protected int bindLayout() {
        return R.layout.acc_login_activity;
    }

    @Override
    protected void initViewsAndData() {
        mTextInputHelper = new EditTextInputHelper(mBtnLoginCommit);
        mTextInputHelper.addViews(mEditPwd,mEditTel);

    }

    @Override
    protected void subscribeLiveData() {

        mViewModel.addRxDisposable(RxView.clicks(mBtnLoginCommit)
                .compose(RxSchedulerHelper.view_main(1))
                .subscribe(v->
                    mViewModel.makeLogin(new UserLogin(mEditTel.getText().toString(),mEditPwd.getText().toString()))));

        mLoginTitle.getRightView().setOnClickListener(v-> startActivity(new Intent(this,RegisterActivityView.class)));
        //监听登录接口
        mViewModel.mLiveUser.observe(this,data->{
            if(data.errorCode == 0){
                ToastUtils.showShort("登录成功");
                finish();
            }else {
                ToastUtils.showShort(data.errorMsg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        mTextInputHelper.removeViews();
        super.onDestroy();
    }
}
