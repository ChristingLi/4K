package com.aizen.wanandroid.mvvm

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aizen.wanandroid.R
import com.aizen.wanandroid.databinding.ActivityMvvmBinding

/**
 * Created by ld on 2018/12/26.
 *
 * @author ld
 * @date 2018/12/26
 * 描    述：
 */
class MvvmMainActivity : AppCompatActivity(){

    lateinit var mBinding: ActivityMvvmBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =  DataBindingUtil.setContentView(this,R.layout.activity_mvvm)
        mBinding.userinfo = User("a","b")


    }


}