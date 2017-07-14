package com.dushu1.doubanai.moviesdetail;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dushu1.doubanai.R;

/**
 * Created by Administrator on 2017/6/22.
 */

public class WebMovieDetailAcitivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
    }

    private void initView() {
        WebView webView = (WebView) findViewById(R.id.act_webview);
        String mUrl = getIntent().getStringExtra("url");
        if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(mUrl);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }

    }
}
