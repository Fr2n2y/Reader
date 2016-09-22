package com.example.zhangyang.Reader.UI.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.zhangyang.Reader.R;
import com.example.zhangyang.Reader.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by zhangyang on 16/9/9.
 */
public class NewsArticleActivity extends SwipeBackActivity {
    @BindView(R.id.news_webview)
    WebView newsWebview;
    @BindView(R.id.progressbar)
    CircularProgressBar progressbar;
    private String pk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsarticle);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        pk = getIntent().getStringExtra("pk");
        newsWebview.setVisibility(View.GONE);
        initWebView();
        newsWebview.loadUrl(Constants.URL + pk);
        newsWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress >= 100) {
                    newsWebview.setVisibility(View.VISIBLE);
                    progressbar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void initWebView() {
        WebSettings settings = newsWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webCache");
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

}
