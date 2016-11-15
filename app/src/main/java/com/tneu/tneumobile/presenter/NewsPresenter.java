package com.tneu.tneumobile.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.tneu.tneumobile.Utils.AppDefaultPrefs;
import com.tneu.tneumobile.Utils.Logger;
import com.tneu.tneumobile.model.News;
import com.tneu.tneumobile.rest.NewsApiService;
import com.tneu.tneumobile.rest.ServiceFactory;
import com.tneu.tneumobile.ui.NewsView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by stepanv on 28.09.16.
 */

public class NewsPresenter extends MvpBasePresenter<NewsView> {
  private final String LOG_TAG = Logger.getLogTag(NewsPresenter.class);
  private CompositeSubscription subscriptions = new CompositeSubscription();
  private boolean isLastNews;

  private List<News> news;

  public void getNews() {
    isLastNews = true;
    getNews(0);
  }

  private void updateIfLastNewsDate() {
    if (isLastNews) {
      AppDefaultPrefs.putAppString(AppDefaultPrefs.PREFS_DATE_KEY, news.get(0).getDate());
      isLastNews = false;
      Logger.d(LOG_TAG, "Set last news date to " + news.get(0).getDate());
    }
  }

  public void getNews(int skipAmount) {
    NewsApiService service = ServiceFactory.createRetrofitService(NewsApiService.class, NewsApiService.SERVICE_ENDPOINT);
    subscriptions.add(service.getNewsByPage(NewsApiService.GET_NEWS_LIMIT, skipAmount)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<News>>() {
          @Override
          public void onCompleted() {
            if (getView() != null) {
              getView().onNewsReceived(news);
            }
            updateIfLastNewsDate();
            subscriptions.clear();
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onNext(List<News> newses) {
            news = newses;
            for (News news : newses) {
              Logger.d(LOG_TAG, news.getTitle());
            }
          }
        }));
  }

  public void onDestroy() {
    subscriptions.unsubscribe();
  }
}
