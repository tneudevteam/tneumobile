package com.mobile.tneu.tneumobile.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mobile.tneu.tneumobile.Utils.Injector;
import com.mobile.tneu.tneumobile.Utils.Logger;

/**
 * Created by stepanv on 10.10.16.
 */

public class NewsWatcherResponseReceiver extends BroadcastReceiver {
  public static final int REQUEST_CODE = 1;
  private static final String LOG_TAG = Logger.getLogTag(NewsWatcherResponseReceiver.class);

  @Override
  public void onReceive(Context context, Intent intent) {
    Intent i = new Intent(context, NewsWatcherService.class);
    context.startService(i);
    Log.d(LOG_TAG, "Response Received");
  }

}
