package com.aizen.wanandroid.ui;

import android.os.Bundle;

import com.aizen.wanandroid.R;
import com.aizen.widget.X5WebView;
import com.blankj.utilcode.util.StringUtils;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by ld on 2018/12/17.
 *
 * @author ld
 * @date 2018/12/17
 * 描    述：Web
 */
public class WebViewActivity extends AppCompatActivity {
    X5WebView webView;
    Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_view);
        mToolbar = findViewById(R.id.toolbar);
        webView.loadUrl("http://192.168.2.188:8080/#/");
        mToolbar.setTitle("");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                if(!StringUtils.isEmpty(webView.getTitle())){
                    mToolbar.setTitle(webView.getTitle());
                }
            }
        });
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(v -> {
            if(webView.canGoBack()){
                webView.goBack();
            }else {
                onBackPressed();
            }
        });

    }
}
