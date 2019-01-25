package com.aizen.wanandroid.ui.page;

import android.os.Bundle;

import com.aizen.wanandroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ld on 2018/12/19.
 *
 * @author ld
 * @date 2018/12/19
 * 描    述：
 */
public class ViewPagerFragmentActivity extends FragmentActivity {

    @BindView(R.id.id_vp)
    ViewPager mIdVp;
    private ArrayList<Fragment> mFragments;

    private String[] args = new String[]{"页面1","页面2","页面3","页面4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.bind(this);

        List<String> names = Arrays.asList(args);
        mFragments = new ArrayList<>();
        for (String name : names) {
            //新建Bundle对象
            Bundle arguments = new Bundle();
            //以键值对的方式放入Bundle对象中
            arguments.putString("EXTRA_CONTENT", name);
            //创建ViewPagerFragment并加入mFragments集合中
            mFragments.add(ViewPagerFragment.newInstance(arguments));
        }

        //设置适配器:这里用匿名内部类，你也可以单独抽出(就两个方法，也没太大必要)
        mIdVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
    }


}
