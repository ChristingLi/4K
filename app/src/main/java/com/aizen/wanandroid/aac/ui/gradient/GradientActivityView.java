package com.aizen.wanandroid.aac.ui.gradient;

import android.os.Bundle;
import android.widget.TextView;

import com.aizen.wanandroid.R;
import com.aizen.wanandroid.aac.BaseViewModelActivity;
import com.aizen.wanandroid.aac.ui.gradient.model.GradientViewModel;
import com.aizen.widget.XCollapsingToolbarLayout;
import com.aizen.widget.bar.TitleBar;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

/**
 * Created by ld on 2019/1/2.
 *
 * @author ld
 * @date 2019/1/2
 * 描    述：渐进式Toolbar
 */
public class GradientActivityView extends BaseViewModelActivity<GradientViewModel> implements XCollapsingToolbarLayout.OnScrimsListener{

    @BindView(R.id.ctl_test_bar)
    XCollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.t_test_title)
    Toolbar mToolbar;
    @BindView(R.id.tb_test_a_bar)
    TitleBar mTitleBar;

    @BindView(R.id.tv_test_address)
    TextView mAddressView;
    @BindView(R.id.tv_test_search)
    TextView mSearchView;

    @Override
    protected Class <GradientViewModel> viewModelClass() {
        return GradientViewModel.class;
    }

    @Override
    protected Bundle arguments() {
        return getIntent().getExtras();
    }

    @Override
    protected int bindLayout() {
        return R.layout.acc_gradient_activity;
    }

    @Override
    protected void initViewsAndData() {
        //设置渐变监听
        mCollapsingToolbarLayout.setOnScrimsListener(this);
    }

    @Override
    protected void subscribeLiveData() {

    }

    @Override
    public void onScrimsStateChange(boolean shown) {
        if (shown) {
            mAddressView.setTextColor(getResources().getColor(R.color.black));
            mSearchView.setBackgroundResource(R.drawable.bg_home_search_bar_gray);
        }else {
            mAddressView.setTextColor(getResources().getColor(R.color.white));
            mSearchView.setBackgroundResource(R.drawable.bg_home_search_bar_transparent);
        }
    }
}
