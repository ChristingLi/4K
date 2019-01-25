package com.aizen.wanandroid.ui.distribute;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aizen.wanandroid.R;
import com.aizen.wanandroid.ui.page.LazyLoadFragment;

import butterknife.BindView;

/**
 * Created by ld on 2018/12/27.
 *
 * @author ld
 * @date 2018/12/27
 * 描    述：
 */
public class ListFragment extends LazyLoadFragment {


    @BindView(R.id.list_view)
    ListView mListView;

    private String[] data = {"Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void lazyLoad(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter <>(this.getContext(), android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);
    }

    public static ListFragment newInstance(){
        return new ListFragment();
    }
}
