package com.aizen.wanandroid.ui.boon;

import android.content.Intent;
import android.os.Bundle;

import com.aizen.wanandroid.R;
import com.aizen.wanandroid.Constant;
import com.aizen.wanandroid.api.entity.BoonEntity;
import com.aizen.wanandroid.frame.BaseActivity;
import com.aizen.wanandroid.frame.CreatePresenter;
import com.aizen.wanandroid.ui.boon.adapter.BoonAdapter;
import com.aizen.wanandroid.ui.photo.PhotoViewerActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;

/**
 * Created by ld on 2018/12/6.
 *
 * @author ld
 * @date 2018/12/6
 * 描    述：
 */
@CreatePresenter(BoonPresenter.class)
public class BoonActivity extends BaseActivity <BoonView, BoonPresenter> implements BoonView {
    @BindView(R.id.boon_recycler)
    RecyclerView mBoonRecycler;

    private BoonAdapter mBoonAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        getPresenter().obtainBoonEntity(mContext,page,mStateView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_boon;
    }

    @Override
    public void initView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
        );
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        mBoonRecycler.setLayoutManager(manager);

        mBoonAdapter = new BoonAdapter(R.layout.item_boon,new ArrayList<>());
        mBoonAdapter.setOnItemClickListener((adapter1, view, position) -> {
            Intent intent = null;
            if(intent == null){
                intent = new Intent();
                intent.setClass(mContext,PhotoViewerActivity.class);
            }
            intent.putExtra(Constant.BundleData.IMG_URL,((BoonEntity)adapter1.getData().get(position)).getUrl());
            startActivity(intent);
        });
        mBoonRecycler.setAdapter(mBoonAdapter);
    }

    @Override
    public void onLoadSuccess(List <BoonEntity> entities) {
       super.loadSuccess(entities);
        mBoonAdapter.setNewData(entities);
    }

    @Override
    public void onLoadMoreSuccess(List <BoonEntity> entities) {
        super.loadMoreSuccess(entities);
        mBoonAdapter.addData(entities);
    }

    @Override
    protected void onLoadMoreData(RefreshLayout refreshLayout) {
        getPresenter().obtainBoonMoreEntity(mContext,page + 1);
    }
}
