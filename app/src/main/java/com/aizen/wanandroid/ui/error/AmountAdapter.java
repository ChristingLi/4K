package com.aizen.wanandroid.ui.error;

import com.aizen.wanandroid.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by ld on 2018/12/27.
 *
 * @author ld
 * @date 2018/12/27
 * 描    述：
 */
public class AmountAdapter extends BaseQuickAdapter<AmountBean, BaseViewHolder> {

    public int mCurrentPosition = 0;


    public AmountAdapter(int layoutResId, @Nullable List<AmountBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AmountBean item) {
        if(item.isChecked()){
//            helper.setBackgroundRes(R.id.item_amount,R.drawable.bg_amount_item_selected);
            helper.setTextColor(R.id.amount_price,mContext.getResources().getColor(R.color.red));
            helper.setTextColor(R.id.diamond_gift,mContext.getResources().getColor(R.color.red));
        }else {
//            helper.setBackgroundRes(R.id.item_amount,R.drawable.bg_amount_item_no_selected);
            helper.setTextColor(R.id.amount_price,mContext.getResources().getColor(R.color.black));
            helper.setTextColor(R.id.diamond_gift,mContext.getResources().getColor(R.color.black));
        }
        helper.setText(R.id.amount_price,item.getAmountDesc());
        helper.setText(R.id.diamond_gift,item.getGiftDesc());
    }


    public void change(int position){
        if(mCurrentPosition != position){
            for(AmountBean data : mData){
                if(data.isChecked()){
                    data.setChecked(false);
                }
            }
            mData.get(position).setChecked(true);
            notifyDataSetChanged();
            mCurrentPosition = position;
        }
    }
}
