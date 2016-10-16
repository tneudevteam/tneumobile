package com.mobile.tneu.tneumobile.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.mobile.tneu.tneumobile.Utils.AppDefaultPrefs;
import com.mobile.tneu.tneumobile.Utils.Logger;
import com.mobile.tneu.tneumobile.model.News;
import com.mobile.tneu.tneumobile.rest.NewsApiService;
import com.mobile.tneu.tneumobile.rest.ServiceFactory;
import com.mobile.tneu.tneumobile.ui.NewsView;

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

  private List<News> news;

    //TODO Remove
    //region Test Data
    public void getNews(){
        getNews(0);
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

                        AppDefaultPrefs.putAppString(AppDefaultPrefs.PREFS_DATE_KEY, news.get(0).getDate());

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
    //endregion

    //TODO Uncomment
//  public void getNews() {
//    NewsApiService service = ServiceFactory.createRetrofitService(NewsApiService.class, NewsApiService.SERVICE_ENDPOINT);
//    subscriptions.add(service.getNewsByPage(NewsApiService.GET_NEWS_LIMIT, 0)
//        .subscribeOn(Schedulers.newThread())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new Subscriber<List<News>>() {
//          @Override
//          public void onCompleted() {
//            if (getView() != null) {
//              getView().onNewsReceived(news);
//            }
//
//            AppDefaultPrefs.putAppString(AppDefaultPrefs.PREFS_DATE_KEY, news.get(0).getDate());
//
//            subscriptions.unsubscribe();
//          }
//
//          @Override
//          public void onError(Throwable e) {
//
//          }
//
//          @Override
//          public void onNext(List<News> newses) {
//            news = newses;
//            for (News news : newses) {
//              Logger.d(LOG_TAG, news.getTitle());
//            }
//          }
//        }));
//  }

  public void onDestroy() {
    subscriptions.unsubscribe();
  }
}
