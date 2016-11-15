package com.tneu.tneumobile.Utils;

import android.util.Log;

/**
 * Created by stepanv on 28.09.16.
 */

public class AndroidLogging implements LoggingInterface {
  @Override
  public void v(String tag, String message) {
    Log.v(tag, message);
  }

  @Override
  public void d(String tag, String message) {
    Log.d(tag, message);
  }

  @Override
  public void i(String tag, String message) {
    Log.i(tag, message);
  }

  @Override
  public void w(String tag, String message) {
    Log.w(tag, message);
  }

  @Override
  public void e(String tag, String message) {
    Log.e(tag, message);
  }

  @Override
  public String stackTrace(Throwable throwable) {
    return Log.getStackTraceString(throwable);
  }
}
