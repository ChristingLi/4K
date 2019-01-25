package com.aizen.net.download;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ld on 2018/11/23.
 *
 * @author ld
 * @date 2018/11/23
 * 描    述：
 */
public abstract class DownLoadObserver implements Observer <DownloadInfo> {
    /**
     * 可以用于取消注册的监听者
     */
    protected Disposable d;
    protected DownloadInfo downloadInfo;

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(DownloadInfo downloadInfo) {
        this.downloadInfo = downloadInfo;
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

}
