package com.mobile.tneu.tneumobile.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.mobile.tneu.tneumobile.Utils.NewsUtil;
import com.mobile.tneu.tneumobile.rest.NewsApiService;
import com.mobile.tneu.tneumobile.rest.ServiceFactory;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by stepanv on 10.10.16.
 */

public class NewsWatcherService extends IntentService {

  public NewsWatcherService() {
    super("NewsWatcherService");
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    NewsApiService service = ServiceFactory.createRetrofitService(NewsApiService.class, NewsApiService.SERVICE_ENDPOINT);
    String thisMoment = NewsUtil.getISOStringForCurrentDate();
    Log.d("Test", thisMoment);
    service.getNewsAfterDate(thisMoment);
  }
}
