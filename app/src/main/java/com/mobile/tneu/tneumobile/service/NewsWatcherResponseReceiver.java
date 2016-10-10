package com.mobile.tneu.tneumobile.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mobile.tneu.tneumobile.HomeActivity;
import com.mobile.tneu.tneumobile.Utils.Logger;

/**
 * Created by stepanv on 10.10.16.
 */

public class NewsWatcherResponseReceiver extends BroadcastReceiver {
  private static final String LOG_TAG = Logger.getLogTag(NewsWatcherResponseReceiver.class);
  public static final int REQUEST_CODE = 1;

  @Override
  public void onReceive(Context context, Intent intent) {
    Intent i = new Intent(context, NewsWatcherService.class);
    context.startService(i);
    Log.d(LOG_TAG, "Response Received");
  }

}
