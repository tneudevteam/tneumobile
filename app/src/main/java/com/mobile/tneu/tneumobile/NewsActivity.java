package com.mobile.tneu.tneumobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.mobile.tneu.tneumobile.Utils.Logger;
import com.mobile.tneu.tneumobile.adapter.NewsRecyclerViewAdapter;
import com.mobile.tneu.tneumobile.model.News;
import com.mobile.tneu.tneumobile.presenter.NewsPresenter;
import com.mobile.tneu.tneumobile.ui.NewsView;
import com.mobile.tneu.tneumobile.ui.activities.ClickListener;

import java.util.List;


public class NewsActivity extends MvpActivity<NewsView, NewsPresenter> implements ClickListener, NewsView {
  private NewsRecyclerViewAdapter adapter;
  private static final String LOG_TAG = Logger.getLogTag(NewsActivity.class);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    init();
    presenter.getNews();
  }

  @NonNull
  @Override
  public NewsPresenter createPresenter() {
    return new NewsPresenter();
  }

  private void init() {
    setContentView(R.layout.activity_news_viewer);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new NewsRecyclerViewAdapter();
    recyclerView.setAdapter(adapter);
  }

  @Override
  public void onNewsReceived(List<News> news) {
    adapter.replaceAll(news);
  }

  @Override
  public void onClick(RecyclerView.ViewHolder holder, View view, ClickType clickType, int position) {

  }
}