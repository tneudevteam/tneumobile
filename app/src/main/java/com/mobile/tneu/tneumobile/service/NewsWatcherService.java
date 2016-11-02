package com.mobile.tneu.tneumobile.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.mobile.tneu.tneumobile.HomeActivity;
import com.mobile.tneu.tneumobile.R;
import com.mobile.tneu.tneumobile.Utils.AppDefaultPrefs;
import com.mobile.tneu.tneumobile.Utils.Logger;
import com.mobile.tneu.tneumobile.model.News;
import com.mobile.tneu.tneumobile.rest.NewsApiService;
import com.mobile.tneu.tneumobile.rest.ServiceFactory;

import java.util.List;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by stepanv on 10.10.16.
 */

public class NewsWatcherService extends IntentService {
  private final String LOG_TAG = Logger.getLogTag(NewsWatcherService.class);
  private final int NOTIFICATION_ID = 1;
  private CompositeSubscription subscriptions = new CompositeSubscription();

  public NewsWatcherService() {
    super("NewsWatcherService");
  }

  @Override
  public void onDestroy() {
    subscriptions.unsubscribe();
    super.onDestroy();
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    Log.d(LOG_TAG, "Watcher handle intent");
    NewsApiService service = ServiceFactory.createRetrofitService(NewsApiService.class, NewsApiService.SERVICE_ENDPOINT);
    String lastLoadedNewsDate = AppDefaultPrefs.getAppString(AppDefaultPrefs.PREFS_DATE_KEY, getApplicationContext());
    subscriptions.add(service.getNewsAfterDate(lastLoadedNewsDate)
        .subscribe(new Subscriber<List<News>>() {
          @Override
          public void onCompleted() {
            //do nothing
          }

          @Override
          public void onError(Throwable e) {
            try {
              Log.e(LOG_TAG, e.getMessage());
            } catch (Throwable b) {
              e.printStackTrace();
            }
          }

          @Override
          public void onNext(List<News> newses) {
            if (newses.size() > 0) {
              NotificationManager notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
              if (newses.size() == 1) {
                notifManager.notify(NOTIFICATION_ID, buildNotif(getString(R.string.app_name), newses.get(0).getTitle(), 1).build());
              } else {
                notifManager.notify(NOTIFICATION_ID, buildNotif(getString(R.string.app_name), newses.get(newses.size() - 1).getTitle(), newses.size()).build());
              }

              AppDefaultPrefs.putAppString(AppDefaultPrefs.PREFS_DATE_KEY, newses.get(0).getDate());
            }
          }
        }));
  }

  private Notification.Builder buildNotif(String title, String text, int number) {
    Intent intent = new Intent(this, HomeActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
        intent, PendingIntent.FLAG_CANCEL_CURRENT);

    return new Notification.Builder(this)
        .setSmallIcon(R.drawable.ic_menu_share)
        .setContentTitle(title)
        .setContentText(text)
        .setContentIntent(pendingIntent)
        .setNumber(number)
        .setAutoCancel(true);
  }

}
