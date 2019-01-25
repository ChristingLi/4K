package com.aizen.wanandroid.aac.ui.home.sub;

import android.view.View;

import com.aizen.wanandroid.R;
import com.aizen.wanandroid.ui.page.LazyLoadFragment;

/**
 * Created by ld on 2018/12/21.
 *
 * @author ld
 * @date 2018/12/21
 * 描    述：
 */
public class PlaceFragment extends LazyLoadFragment {

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_place;
    }

    @Override
    protected void lazyLoad(View view) {

    }

    public static PlaceFragment newInstance(){
        return new PlaceFragment();
    }
}
