package com.aizen.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

/**
 * Created by ld on 2018/11/24.
 *
 * @author ld
 * @date 2018/11/24
 * 描    述：
 */
public class StatusBarUtils {
    /**
     * 沉浸式状态栏
     * @param activity ac
     */
    public static void statusBarIn(Activity activity){
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = activity.getWindow().getDecorView();
            int opention = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(opention);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
