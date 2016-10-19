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

  public static void sheduleAlarm() {
    Context context = Injector.get().getContext();
    Intent intent = new Intent(context, NewsWatcherResponseReceiver.class);

    final PendingIntent pIntent = PendingIntent.getBroadcast(context, NewsWatcherResponseReceiver.REQUEST_CODE,
        intent, PendingIntent.FLAG_UPDATE_CURRENT);

    long firstMillis = System.currentTimeMillis();
    AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
        AlarmManager.INTERVAL_HALF_HOUR, pIntent);
  }

  public static void stopAlarm() {
    Context context = Injector.get().getContext();
    Intent intent = new Intent(context, NewsWatcherResponseReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NewsWatcherResponseReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    alarmManager.cancel(pendingIntent);
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    Intent i = new Intent(context, NewsWatcherService.class);
    context.startService(i);
    Log.d(LOG_TAG, "Response Received");
  }

}
