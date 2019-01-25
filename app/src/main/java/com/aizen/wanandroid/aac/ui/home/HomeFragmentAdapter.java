package com.aizen.wanandroid.aac.ui.home;

import com.aizen.common.BaseFragmentAdapter;
import com.aizen.wanandroid.aac.ui.home.sub.PlaceFragment;
import com.aizen.wanandroid.ui.page.LazyLoadFragment;

import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * Created by ld on 2019/1/3.
 *
 * @author ld
 * @date 2019/1/3
 * 描    述：主页界面ViewPager + Fragment适配器
 */
public class HomeFragmentAdapter extends BaseFragmentAdapter<LazyLoadFragment> {

    public HomeFragmentAdapter(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected void init(FragmentManager fm, List<LazyLoadFragment> list) {
        list.add(PlaceFragment.newInstance());
        list.add(PlaceFragment.newInstance());
        list.add(PlaceFragment.newInstance());
        list.add(PlaceFragment.newInstance());
    }
}
