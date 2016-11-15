package com.tneu.tneumobile.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.tneu.tneumobile.service.NewsWatcherResponseReceiver;

/**
 * Created by stepanv on 10.11.16.
 */

public class AlarmUtil {
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
}
