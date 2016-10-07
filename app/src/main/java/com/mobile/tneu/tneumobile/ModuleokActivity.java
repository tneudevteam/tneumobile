package com.mobile.tneu.tneumobile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.mobile.tneu.tneumobile.Utils.Logger;

/**
 * Created by stepanv on 06.10.16.
 */

public class ModuleokActivity extends AppCompatActivity {
  private static final String LOG_TAG = Logger.getLogTag(ModuleokActivity.class);
  ProgressBar progress;
  WebView webView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_moduleok);
    webView = (WebView) findViewById(R.id.web_view);
    progress = (ProgressBar) findViewById(R.id.progressBar);
    startWebView("http://modulok.ml")
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

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
      }

      @Override
      public void onLoadResource(WebView view, String url) {
        if (progress != null) {
          progress.setVisibility(View.VISIBLE);
        }
      }

      @Override
      public void onPageFinished(WebView view, String url) {
          if (progress != null) {
            progress.setVisibility(View.INVISIBLE);
          }
      }
    });

    webView.getSettings().setJavaScriptEnabled(true);
    webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
    webView.setScrollbarFadingEnabled(false);

    webView.loadUrl(url);
  }

}
