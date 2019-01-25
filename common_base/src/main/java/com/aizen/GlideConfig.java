package com.aizen;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

import androidx.annotation.NonNull;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
@GlideModule
public class GlideConfig extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int diskCacheSizeBytes = 1024 * 1024 * 75;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,diskCacheSizeBytes));
//        super.applyOptions(context, builder);
    }
}
