package com.aizen.widget.bar.style;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.aizen.common.R;

/**
 * Created by ld on 2018/12/28.
 *
 * @author ld
 * @date 2018/12/28
 * 描    述：默认透明主题样式实现
 */
public class TitleBarTransparentStyle extends BaseTitleBarStyle {

    public TitleBarTransparentStyle(Context context) {
        super(context);
    }

    @Override
    public Drawable getBackground() {
        return new ColorDrawable(0x00000000);
    }

    @Override
    public Drawable getBackIcon() {
        return getDrawable(R.mipmap.bar_icon_back_white);
    }

    @Override
    public int getLeftColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public int getTitleColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public int getRightColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public boolean isLineVisible() {
        return false;
    }

    @Override
    public Drawable getLineDrawable() {
        return new ColorDrawable(0x00000000);
    }

    @Override
    public int getLineSize() {
        return 0;
    }

    @Override
    public Drawable getLeftBackground() {
        return getDrawable(R.drawable.bar_selector_selectable_transparent);
    }

    @Override
    public Drawable getRightBackground() {
        return getDrawable(R.drawable.bar_selector_selectable_transparent);
    }
}
