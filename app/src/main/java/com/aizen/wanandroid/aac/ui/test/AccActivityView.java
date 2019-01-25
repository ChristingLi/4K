package com.aizen.wanandroid.aac.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.aizen.utils.LoggerUtils;
import com.aizen.wanandroid.Constant;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.aac.BaseViewModelActivity;
import com.aizen.wanandroid.api.entity.BoonEntity;
import com.aizen.wanandroid.ui.boon.adapter.BoonAdapter;
import com.aizen.wanandroid.ui.photo.PhotoViewerActivity;
import com.aizen.widget.progressbar.NumberProgressBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;

/**
 * Created by ld on 2018/12/24.
 *
 * @author ld
 * @date 2018/12/24
 * 描    述：
 */
public class AccActivityView extends BaseViewModelActivity<AccViewModel> {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.change)
    Button mChange;
    @BindView(R.id.boon_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.progress_bar)
    NumberProgressBar mNumberProgressBar;

    private BoonAdapter mBoonAdapter;
    private int CODE = 0;

    @Override
    protected Class <AccViewModel> viewModelClass() {
        return AccViewModel.class;
    }

    @Override
    protected Bundle arguments() {
        return getIntent().getExtras();
    }

    @Override
    protected int bindLayout() {
        return R.layout.ac_test;
    }


    @Override
    protected void initViewsAndData() {
        mToolbar.setTitle("测试AAC");
    }

    @Override
    protected void subscribeLiveData() {
        //干什么
        Observer<String> nameObserver = s -> mName.setText(s);
        //订阅干什么
        mViewModel.getCurrentName().observe(this,nameObserver);
        String[] strs = new String[]{"张三","李四","赵武","王炸","蘑菇头"};
        mChange.setOnClickListener(v -> {
            //干吧
            int random = new Random().nextInt(strs.length) % (strs.length);
            mViewModel.getCurrentName().setValue(strs[random]);
        });











        initAdapter();

        Observer<List<BoonEntity>> boonData = entities -> {
            if(CODE == 0){
                mBoonAdapter.setNewData(entities);
                mSmartRefreshLayout.finishRefresh(0);
            }else {
                mBoonAdapter.addData(entities);
                mSmartRefreshLayout.finishLoadMore(0);
            }
        };

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                CODE = 1;
                mViewModel.moreBoonData().observe(AccActivityView.this,boonData);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                CODE = 0;
                mViewModel.refreshBoonData().observe(AccActivityView.this,boonData);
            }
        });

    }

    private void initAdapter() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(manager);
        mBoonAdapter = new BoonAdapter(R.layout.item_boon,new ArrayList<>());
        mBoonAdapter.setOnItemClickListener((adapter1, view, position) -> {
            Intent intent = null;
            if(intent == null){
                intent = new Intent();
                intent.setClass(this,PhotoViewerActivity.class);
            }
            intent.putExtra(Constant.BundleData.IMG_URL,((BoonEntity)adapter1.getData().get(position)).getUrl());
            startActivity(intent);
        });
        mRecyclerView.setAdapter(mBoonAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoggerUtils.Logger("ModelClear","onDestroy");
    }
}
