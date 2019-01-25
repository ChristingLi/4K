package com.aizen.wanandroid.ui.photo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.aizen.utils.GlideUtils;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.Constant;
import com.aizen.wanandroid.frame.BaseActivity;
import com.aizen.wanandroid.frame.CreatePresenter;
import com.blankj.utilcode.util.BarUtils;
import com.github.chrisbanes.photoview.PhotoView;

import androidx.annotation.RequiresApi;
import butterknife.BindView;

/**
 * Created by ld on 2018/12/21.
 *
 * @author ld
 * @date 2018/12/21
 * 描    述：
 */
@CreatePresenter(PhotoViewerPresenter.class)
public class PhotoViewerActivity extends BaseActivity <PhotoViewerView, PhotoViewerPresenter> implements PhotoViewerView {
    @BindView(R.id.viewer)
    PhotoView mViewer;

    private String mIngUrl = "";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData(Bundle savedInstanceState) {
        BarUtils.setNavBarColor(this,getResources().getColor(R.color.black));
        BarUtils.setStatusBarColor(this,getResources().getColor(R.color.black));
        Intent intent = getIntent();
        mIngUrl = intent.getStringExtra(Constant.BundleData.IMG_URL);
        if(mViewer!=null){
            GlideUtils.apply(mContext,mIngUrl,mViewer);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo_viewer;
    }

    @Override
    public void initView() {

    }
}
