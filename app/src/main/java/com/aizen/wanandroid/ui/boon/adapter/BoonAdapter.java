package com.aizen.wanandroid.ui.boon.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.aizen.utils.GlideUtils;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.api.entity.BoonEntity;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;


/**
 * Created by ld on 2018/12/6.
 *
 * @author ld
 * @date 2018/12/6
 * 描    述：
 */
public class BoonAdapter extends BaseQuickAdapter<BoonEntity,BaseViewHolder> {

    public BoonAdapter(int layoutResId, @Nullable List<BoonEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BoonEntity item) {
        View content = helper.getView(R.id.content);
        ViewGroup.LayoutParams params = content.getLayoutParams();
        params.width = ScreenUtils.getScreenWidth() /2;
        int w = params.width;
        int h;
//        if(helper.getAdapterPosition() % 2 == 0){
            params.height = SizeUtils.dp2px(270);
//        }else {
//        params.height = SizeUtils.dp2px(300);
//        }
        h = params.height;
        content.setLayoutParams(params);
        GlideUtils.apply(mContext,item.getUrl(),helper.getView(R.id.boon),w,h);

    }
}
