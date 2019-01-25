package com.aizen.common;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by ld on 2019/1/3.
 *
 * @author ld
 * @date 2019/1/3
 * 描    述： FragmentPagerAdapter 基类
 */
public abstract class BaseFragmentAdapter<T extends Fragment> extends FragmentPagerAdapter {
    /**
     * Fragment集合
     */
    private List<T> mFragmentSet = new ArrayList <>();
    /**
     * 当前显示的Fragment
     */
    private T mCurrentFragment;

    /**
     * 在Activity中使用ViewPager适配器
     * @param activity
     */
    public BaseFragmentAdapter(FragmentActivity activity){
        this(activity.getSupportFragmentManager());
    }

    /**
     * 在Fragment中使用ViewPager适配器
     * @param fragment
     */
    public BaseFragmentAdapter(Fragment fragment){
        this(fragment.getChildFragmentManager());
    }

    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
        init(fm,mFragmentSet);
    }

    protected abstract void init(FragmentManager fm, List<T> list);

    @Override
    public T getItem(int position) {
        return mFragmentSet.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentSet.size();
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if(getCurrentFragment() != object){
            mCurrentFragment = (T) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * 返回Fragment的集合
     * @return
     */
    public List<T> getAllFragments(){
        return mFragmentSet;
    }

    /**
     * 获取当前Fragment
     * @return
     */
    public T getCurrentFragment() {
        return mCurrentFragment;
    }
}
