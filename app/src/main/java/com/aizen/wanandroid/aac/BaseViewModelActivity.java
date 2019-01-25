package com.aizen.wanandroid.aac;

import android.os.Bundle;

import com.aizen.wanandroid.MyApp;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import butterknife.Unbinder;

/**
 * Created by ld on 2018/12/24.
 *
 * @author ld
 * @date 2018/12/24
 * 描    述：创建Model数据
 */
public abstract class BaseViewModelActivity<T extends BaseViewModel> extends BaseOnLifeActivity{

    protected  T  mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = initViewModel();
        initViewsAndData();
        subscribeLiveData();
    }

    /**
     * 创建ViewModel类
     * @return
     */
    private T initViewModel(){
        /**
         * 传递Application,Bundle 数据到Model
         */
        return ViewModelProviders.of(this,new ViewModelFactory(MyApp.getApplication(),arguments())).get(viewModelClass());
    }

    /**
     * 返回当前Ac需要的Model数据
     * @return
     */
    protected abstract Class<T> viewModelClass();

    /**
     * 接受到的Bundle数据
     * @return
     */
    protected abstract Bundle arguments();


}
