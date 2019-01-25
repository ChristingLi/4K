package com.aizen.wanandroid.aac.ui.home;

import android.os.Bundle;
import android.view.MenuItem;

import com.aizen.utils.LoggerUtils;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.aac.BaseViewModelActivity;
import com.aizen.wanandroid.aac.ui.home.model.HomeViewModel;
import com.aizen.widget.NoScrollViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import butterknife.BindView;

/**
 * Created by ld on 2018/12/21.
 *
 * @author ld
 * @date 2018/12/21
 * 描    述：主页
 */
public class HomeActivity extends BaseViewModelActivity<HomeViewModel> implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.vp_home_page)
    NoScrollViewPager mVpHomePage;
    @BindView(R.id.bv_home_navigation)
    BottomNavigationView mBvHomeNavigation;

    private HomeFragmentAdapter mHomeFragmentAdapter;

    @Override
    protected Class <HomeViewModel> viewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected Bundle arguments() {
        return getIntent().getExtras();
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViewsAndData() {
        //不使用默认色
        mBvHomeNavigation.setItemIconTintList(null);
        mBvHomeNavigation.setOnNavigationItemSelectedListener(this);
        mHomeFragmentAdapter = new HomeFragmentAdapter(this);
        mVpHomePage.setAdapter(mHomeFragmentAdapter);
        mVpHomePage.setOffscreenPageLimit(4);
    }

    @Override
    protected void subscribeLiveData() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.menu_home:
                mVpHomePage.setCurrentItem(0);
                return true;
            case R.id.menu_found:
                mVpHomePage.setCurrentItem(1);
                return true;
            case R.id.menu_message:
                mVpHomePage.setCurrentItem(2);
                return true;
            case R.id.menu_mine:
                mVpHomePage.setCurrentItem(3);
                return true;
        }
        return false;
    }
}
