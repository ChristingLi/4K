package com.aizen.wanandroid.ui.page;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aizen.utils.LoggerUtils;
import com.aizen.wanandroid.R;

/**
 * Created by ld on 2018/12/19.
 *
 * @author ld
 * @date 2018/12/19
 * 描    述：
 */
public class ViewPagerFragment extends LazyLoadFragment{

    private static final String TAG = "ViewPagerFragment";
    private String mName;

    /**
     *
     * @param bundle Activity向Fragment的数据
     * @return ViewPagerFragment对象
     */
    public static ViewPagerFragment newInstance(Bundle bundle) {
        //新建ViewPagerFragment对象
        ViewPagerFragment instance = new ViewPagerFragment();
        //设置参数并返回出ViewPagerFragment对象
        instance.setArguments(bundle);
        return instance;
    }



    @Override
    protected int setLayoutId() {
        return R.layout.fragment_viewpager;
    }

    @Override
    protected void lazyLoad(View view) {
//        view.setBackgroundColor(ColUtils.randomRGB());
        TextView tv = findViewById(R.id.tv_vp_fg_name);
        mName = getArguments().getString("EXTRA_CONTENT");
        LoggerUtils.Logger("Name",mName);
        tv.setText(mName);
        Log.e(TAG, "onCreateView: " + mName);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + mName);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " + mName);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " + mName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: " + mName);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: " + mName);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: " + mName);
        super.onDestroy();
    }

}
