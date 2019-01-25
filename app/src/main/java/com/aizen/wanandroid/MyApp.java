package com.aizen.wanandroid;

import com.aizen.common.mvpbase.BaseApplication;
import com.orhanobut.hawk.Hawk;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * Created by ld on 2018/12/6.
 *
 * @author ld
 * @date 2018/12/6
 * 描    述：
 */
public class MyApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的基本设置构建器
        SmartRefreshLayout.setDefaultRefreshInitializer((context, refreshLayout) -> {
            //取消内容不满一页时开启上拉加载功能
            refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
            //是否在刷新的时候禁止列表的操作
            refreshLayout.setDisableContentWhenRefresh(true);
            //是否在加载的时候禁止列表的操作
            refreshLayout.setDisableContentWhenLoading(true);
        });

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.color_60white, R.color.color_212121);
            //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }
}
