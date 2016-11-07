package com.mobile.tneu.tneumobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mobile.tneu.tneumobile.Utils.Logger;

/**
 * Created by stepanv on 06.10.16.
 */

public class ModuleokActivity extends AppCompatActivity {
  private static final String LOG_TAG = Logger.getLogTag(ModuleokActivity.class);
  //  ProgressBar progress;
  WebView webView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_moduleok);

    webView = (WebView) findViewById(R.id.web_view_moduleok);
//    progress = (ProgressBar) findViewById(R.id.progressBar);
    startWebView("http://modulok.ml/auth");
  }

  @Override
  public void onBackPressed() {
    if (webView.canGoBack()) {
      webView.goBack();
    } else {
      super.onBackPressed();
    }
  }

  private void startWebView(String url) {
    webView.setWebViewClient(new WebViewClient() {
      //TODO: fix loading view
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        view.loadUrl(url);
        return false;
      }

      @Override
      public void onLoadResource(WebView view, String url) {
//        if (progress != null) {
//          progress.setVisibility(View.VISIBLE);
//        }
      }

      @Override
      public void onPageFinished(WebView view, String url) {
//        if (progress != null) {
//          progress.setVisibility(View.INVISIBLE);
//        }
      }
    });

    WebSettings webSettings = webView.getSettings();
    webSettings.setDomStorageEnabled(true);
    webSettings.setJavaScriptEnabled(true);
    webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
    webView.setScrollbarFadingEnabled(false);

    webView.loadUrl(url);
  }

}
