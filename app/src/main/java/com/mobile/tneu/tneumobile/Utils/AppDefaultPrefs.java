package com.mobile.tneu.tneumobile.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by stepanv on 12.10.16.
 */

public class AppDefaultPrefs {
  public static final String PREFS_DATE_KEY = "tneumobile.APP_PREFS.DATE";
  private static final String PREFS_KEY = "tneumobile.APP_PREFS";
  private static final int PREFS_MODE = Context.MODE_PRIVATE;

  private static SharedPreferences getSharedPrefs(@NonNull Context context) {
    return context.getSharedPreferences(PREFS_KEY, PREFS_MODE);
  }

  public static String getString(@NonNull Context context, String key, String defaultValue) {
    return getSharedPrefs(context).getString(key, defaultValue);
  }

  public static String getAppString(String key) {
    return getString(Injector.get().getContext(), key, "");
  }

  public static void putString(@NonNull Context context, String key, String value) {
    getSharedPrefs(context).edit()
        .putString(key, value)
        .apply();
  }

  public static void putAppString(String key, String value) {
    getSharedPrefs(Injector.get().getContext()).edit()
        .putString(key, value)
        .apply();
  }

  public static void remove(@NonNull Context context, String key) {
    getSharedPrefs(context).edit()
        .remove(key)
        .apply();
  }

  public static void clear(@NonNull Context context) {
    getSharedPrefs(context).edit().clear().apply();
  }

}