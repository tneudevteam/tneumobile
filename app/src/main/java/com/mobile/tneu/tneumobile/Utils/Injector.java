package com.mobile.tneu.tneumobile.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

/**
 * Created by stepanv on 12.10.16.
 */

public class Injector {

  private static final String LOG_TAG = Logger.getLogTag(Injector.class);
  @SuppressLint("StaticFieldLeak")
  private static Injector instance = new Injector();

  private Context context;

  public static Injector get() {
    if (instance == null) {
      Logger.w(LOG_TAG, "instance == null");
      instance = new Injector();
    }
    return instance;
  }

  @VisibleForTesting
  static void set(Injector injector) {
    Injector.instance = injector;
  }

  public Context getContext() {
    return get().context;
  }

  public void setContext(Context context) {
    get().context = context;
  }

}
