package com.aizen.utils;

import android.content.Context;
import android.widget.ImageView;

import com.aizen.GlideApp;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public class GlideUtils {
    /**
     * With CenterCrop
     * @param context
     * @param url
     * @param target
     */
    public static void apply(Context context,String url, ImageView target,int w,int h){
        GlideApp
                .with(context)
                .load(url)
                .apply(new RequestOptions().centerCrop())
                .transition(new DrawableTransitionOptions().crossFade(800))
                .override(w,h)
                .into(target);

    }

    public static void apply(Context context, String url, PhotoView photoView){
        GlideApp.with(context)
                .load(url)
                .apply(new RequestOptions().fitCenter())
                .into(photoView);
    }
}
