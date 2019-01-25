package com.aizen.wanandroid.ui.error;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.aizen.common.mvpbase.BaseActivity;
import com.aizen.wanandroid.R;
import com.blankj.utilcode.util.SizeUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by ld on 2018/12/27.
 *
 * @author ld
 * @date 2018/12/27
 * 描    述：
 */
public class SizeErrorActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.btn_intent)
    Button mIntent;
    private List <AmountBean> mAmountBeans;

    private AmountAdapter mAmountAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createData();

        mAmountAdapter = new AmountAdapter(R.layout.item_order_amount,mAmountBeans);
        mRecycler.setAdapter(mAmountAdapter);
        mRecycler.setLayoutManager(new GridLayoutManager(mContext,3));
        mRecycler.addItemDecoration(new GridSpacingItemDecoration(3,SizeUtils.dp2px(9),true));
        mAmountAdapter.setOnItemClickListener((adapter, view, position) -> {
            mAmountAdapter.change(position);
        });


        mIntent.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(mContext,H5PayDemoActivity.class);
            intent.putExtra("URL","https://wgapi.gezhongxinqun1.com/wechat-group/order/alipay/web?userId="+
                    1 +
                    "&rechargeAmount=" + 10 +"");
            startActivity(intent);
        });
    }

    private void createData() {
        mAmountBeans = new ArrayList <>();
        AmountBean b1 = new AmountBean("1", 10, true);
        AmountBean b2 = new AmountBean("2", 10, false);
        AmountBean b3 = new AmountBean("3", 10, false);
        AmountBean b4 = new AmountBean("4", 10, false);
        AmountBean b5 = new AmountBean("5", 10, false);
        AmountBean b6 = new AmountBean("6", 10, false);
        mAmountBeans.add(b1);
        mAmountBeans.add(b2);
        mAmountBeans.add(b3);
        mAmountBeans.add(b4);
        mAmountBeans.add(b5);
        mAmountBeans.add(b6);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_size_error;
    }
}
