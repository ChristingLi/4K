package com.aizen.wanandroid.ui.boon;

import com.aizen.wanandroid.api.entity.BoonEntity;
import com.aizen.wanandroid.frame.BaseView;

import java.util.List;

/**
 * Created by ld on 2018/12/7.
 *
 * @author ld
 * @date 2018/12/7
 * 描    述：
 */
public interface BoonView extends BaseView {
    /**
     * 加载成功
     * @param entities
     */
    void onLoadSuccess(List<BoonEntity> entities);

    void onLoadMoreSuccess(List<BoonEntity> entities);
}
