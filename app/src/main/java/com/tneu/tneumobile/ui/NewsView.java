package com.tneu.tneumobile.ui;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.tneu.tneumobile.model.News;

import java.util.List;

/**
 * Created by stepanv on 28.09.16.
 */

public interface NewsView extends MvpView {
  void onNewsReceived(List<News> news);
}
