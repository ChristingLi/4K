package com.aizen.wanandroid.ui.assort.adapter;

import com.aizen.wanandroid.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by ld on 2018/12/18.
 *
 * @author ld
 * @date 2018/12/18
 * 描    述：
 */
public class ActivityItemAdapter extends BaseQuickAdapter<Class,BaseViewHolder> {

    int[] colors;

    public ActivityItemAdapter(int layoutResId, @Nullable List<Class> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Class item) {
        if(colors == null){
            colors =  new int[]{
                    mContext.getResources().getColor(R.color.lemonchiffon),
                    mContext.getResources().getColor(R.color.lightpink),
                    mContext.getResources().getColor(R.color.lightgoldenrodyellow),
                    mContext.getResources().getColor(R.color.wheat),
                    mContext.getResources().getColor(R.color.aliceblue),
                    mContext.getResources().getColor(R.color.burlywood),
                    mContext.getResources().getColor(R.color.tan)};
        }
        helper.setText(R.id.name,item.getSimpleName());
        int position = helper.getAdapterPosition() % 7;
        helper.setBackgroundColor(R.id.item_content,colors[position]);

    }
}
