package com.tneu.tneumobile.Utils;

/**
 * Created by stepanv on 28.09.16.
 */

public interface LoggingInterface {
  void v(String tag, String message);

  void d(String tag, String message);

  void i(String tag, String message);

  void w(String tag, String message);

  void e(String tag, String message);

  String stackTrace(Throwable throwable);
}
