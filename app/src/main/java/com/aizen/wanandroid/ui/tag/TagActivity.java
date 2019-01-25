package com.aizen.wanandroid.ui.tag;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aizen.common.mvpbase.BaseActivity;
import com.aizen.wanandroid.R;
import com.moxun.tagcloudlib.view.TagCloudView;
import com.moxun.tagcloudlib.view.TagsAdapter;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * Created by ld on 2019/1/2.
 *
 * @author ld
 * @date 2019/1/2
 * 描    述：
 */
public class TagActivity extends BaseActivity {

    @BindView(R.id.tag)
    TagCloudView mTagCloudView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTagCloudView.setAdapter(new VectorTagAdapter());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tag;
    }
}

class VectorTagAdapter extends TagsAdapter{

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public View getView(Context context, int position, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.tag_item_vector, parent, false);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getPopularity(int position) {
        return position % 5;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        ImageView imageView = view.findViewById(R.id.vector_img);
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(themeColor,
                PorterDuff.Mode.SRC_ATOP);
        if (imageView == null) {
            return;
        }
        imageView.getDrawable().setColorFilter(porterDuffColorFilter);
    }
}
