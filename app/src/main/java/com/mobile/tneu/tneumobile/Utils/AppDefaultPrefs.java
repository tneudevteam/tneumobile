package com.mobile.tneu.tneumobile.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by stepanv on 12.10.16.
 */

public class AppDefaultPrefs {
  public static final String PREFS_DATE_KEY = "tneumobile.APP_PREFS.DATE";
  public static final String PREFS_SETTINGS_SHOW_NOTIF = "tneumobile.APP_PREFS.SETTINGS.SHOW_NOTIF";

  private static final String PREFS_KEY = "tneumobile.APP_PREFS";
  private static final int PREFS_MODE = Context.MODE_PRIVATE;

  private static SharedPreferences getSharedPrefs(@NonNull Context context) {
    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE);
  }

  public static String getAppString(String key) {
    return getSharedPrefs(Injector.get().getContext()).getString(key, "");
  }

  public static String getAppString(String key, Context context) {
    return getSharedPrefs(context).getString(key, "");
  }

  public static Boolean getAppBoolean(String key) {
    return getSharedPrefs(Injector.get().getContext()).getBoolean(key, true);
  }

  public static void putAppString(String key, String value) {
    getSharedPrefs(Injector.get().getContext()).edit()
        .putString(key, value)
        .apply();
  }

  public static void putAppBoolean(String key, Boolean value) {
    getSharedPrefs(Injector.get().getContext()).edit()
        .putBoolean(key, value)
        .apply();
  }

  public static void remove(String key) {
    getSharedPrefs(Injector.get().getContext()).edit()
        .remove(key)
        .apply();
  }

  public static void clear(@NonNull Context context) {
    getSharedPrefs(context).edit().clear().apply();
  }

}