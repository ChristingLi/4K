package com.aizen.wanandroid.ui.distribute;

import android.os.Bundle;

import com.aizen.common.mvpbase.BaseActivity;
import com.aizen.wanandroid.R;
import com.aizen.wanandroid.ui.page.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Created by ld on 2018/12/27.
 *
 * @author ld
 * @date 2018/12/27
 * 描    述：模拟滑动事件冲突
 */
public class EventDistributeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.group)
    ViewPager mGroup;
    List<Fragment> mFragments = new ArrayList <>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setTitle("测试滑动事件冲突");
        mFragments.add(ListFragment.newInstance());
        Bundle arguments = new Bundle();
        arguments.putString("EXTRA_CONTENT", "1");
        mFragments.add(ViewPagerFragment.newInstance(arguments));
        mFragments.add(ViewPagerFragment.newInstance(arguments));

        mGroup.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event_distribute;
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
