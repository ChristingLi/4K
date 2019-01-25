package com.aizen.wanandroid.ui.assort;

import android.content.Intent;
import android.os.Bundle;

import com.aizen.utils.ClassUtils;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.aac.ui.register.RegisterActivityView;
import com.aizen.wanandroid.frame.BaseActivity;
import com.aizen.wanandroid.frame.CreatePresenter;
import com.aizen.wanandroid.ui.assort.adapter.ActivityItemAdapter;
import com.aizen.wanandroid.ui.launcher.LauncherActivity;
import com.aizen.wanandroid.ui.photo.PhotoViewerActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by ld on 2018/12/18.
 *
 * @author ld
 * @date 2018/12/18
 * 描    述：
 */
@CreatePresenter(AssortPresenter.class)
public class AssortActivity extends BaseActivity <AssortView, AssortPresenter> implements AssortView {

    @BindView(R.id.content)
    RecyclerView mContent;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ActivityItemAdapter mItemAdapter;

    private List <Class> mClasses = new ArrayList <>();

    @Override
    public void initData(Bundle savedInstanceState) {
        List<Class> excludeClass = new ArrayList <>();
        excludeClass.add(PermissionUtils.PermissionActivity.class);
        excludeClass.add(this.getClass());
        excludeClass.add(PhotoViewerActivity.class);
        excludeClass.add(LauncherActivity.class);
        excludeClass.add(RegisterActivityView.class);
        mClasses.addAll(ClassUtils.getActivitiesClass(mContext,excludeClass));
        if(mClasses != null && !mClasses.isEmpty()){
            mItemAdapter.addData(mClasses);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_assort;
    }

    @Override
    public void initView() {
        mItemAdapter = new ActivityItemAdapter(R.layout.item_actitity,new ArrayList <>());
        mContent.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        mContent.setAdapter(mItemAdapter);
        mItemAdapter.setOnItemClickListener((adapter, view, position) -> {
            Class currentClass = mClasses.get(position);
            ActivityUtils.startActivity(new Intent(mContext,currentClass));
        });

        mToolbar.setTitle("Activity列表");
    }

}
