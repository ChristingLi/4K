package com.aizen.wanandroid.aac.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.aizen.helper.RxSchedulerHelper;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.aac.BaseViewModelActivity;
import com.aizen.wanandroid.aac.ui.login.LoginAccActivityView;
import com.aizen.wanandroid.aac.ui.register.model.RegisterEntity;
import com.aizen.wanandroid.aac.ui.register.model.RegisterViewModel;
import com.aizen.widget.CountdownView;
import com.aizen.widget.bar.TitleBar;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding3.view.RxView;

import butterknife.BindView;

/**
 * Created by ld on 2019/1/2.
 *
 * @author ld
 * @date 2019/1/2
 * 描    述：注册
 */
public class RegisterActivityView extends BaseViewModelActivity<RegisterViewModel> {
    @BindView(R.id.tb_register_title)
    TitleBar mTbRegisterTitle;
    @BindView(R.id.et_register_phone)
    EditText mEtRegisterPhone;
    @BindView(R.id.cv_register_countdown)
    CountdownView mCvRegisterCountdown;

    @BindView(R.id.et_register_code)
    EditText mEtRegisterCode;
    @BindView(R.id.et_register_password1)
    EditText mEtRegisterPassword1;
    @BindView(R.id.et_register_password2)
    EditText mEtRegisterPassword2;
    @BindView(R.id.btn_register_commit)
    Button mBtnRegisterCommit;

    private RegisterEntity mRegisterEntity;

    @Override
    protected Class <RegisterViewModel> viewModelClass() {
        return RegisterViewModel.class;
    }

    @Override
    protected Bundle arguments() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.aac_register_activity;
    }

    @Override
    protected void initViewsAndData() {

        mTbRegisterTitle.getLeftView().setOnClickListener(v->startActivity(new Intent(this,LoginAccActivityView.class)));

        mViewModel.addRxDisposable(RxView.clicks(mBtnRegisterCommit).compose(RxSchedulerHelper.view_main(1))
        .subscribe(v->{
            if(mRegisterEntity == null){
                mRegisterEntity = new RegisterEntity();
            }
            String tel = mEtRegisterPhone.getText().toString();
            String code = mEtRegisterCode.getText().toString();
            String pwd1 = mEtRegisterPassword1.getText().toString();
            String pwd2 = mEtRegisterPassword2.getText().toString();
            if(StringUtils.isEmpty(tel)){
                ToastUtils.showShort("请输入手机号");
                return;
            }
            mRegisterEntity.tel = tel;
            if(StringUtils.isEmpty(code)){
                ToastUtils.showShort("请输入验证码");
                return;
            }
            mRegisterEntity.code = code;
            if(StringUtils.isEmpty(pwd1)){
                ToastUtils.showShort("请输入您的密码");
                return;
            }
            mRegisterEntity.pwd1 = pwd1;
            if(StringUtils.isEmpty(pwd2)){
                ToastUtils.showShort("请输入您的确认密码");
                return;
            }
            mRegisterEntity.pwd2 = pwd2;
            if(!mRegisterEntity.isCurretPwd()){
                ToastUtils.showShort("您两次输入的密码不一致");
                return;
            }
            mViewModel.mRegisterLD.postValue(mRegisterEntity);
        }));

    }

    @Override
    protected void subscribeLiveData() {

        mViewModel.mRegisterLD.observe(this,registerEntity -> {
            if(registerEntity!= null && registerEntity.isCurretPwd()){
                ToastUtils.showShort("密码一致");
                finish();
            }
        });

    }

}
