package com.mobile.tneu.tneumobile.Utils;

/**
 * Created by stepanv on 28.09.16.
 */

public class Logger {

  public enum LogLevel {
    ALL,
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERR,
    NONE
  }

  private static LogLevel sLogLevel = LogLevel.VERBOSE;
  private static LoggingInterface logger = new AndroidLogging();

  public static LogLevel getLogLevel() {
    return sLogLevel;
  }

  public static void setLogLevel(LogLevel logLevel) {
    Logger.sLogLevel = logLevel;
  }

  public static void setLogger(LoggingInterface logger) {
    Logger.logger = logger;
  }

  private static boolean isLogEnabled(LogLevel level) {
    return sLogLevel.ordinal() <= level.ordinal();
  }

  public static String getLogTag(Class<?> classObj) {
    return classObj.getName();
  }

  public static String getLogTag(Object obj) {
    return obj == null ? "null" : obj.getClass().getName();
  }

  private static String arrayToString(Object... arr) {
    StringBuilder builder = new StringBuilder();
    for (Object obj : arr) {
      if (obj == null) {
        builder.append("null");
      } else if (obj instanceof Throwable) {
        builder.append(stackTrace((Throwable) obj));
      } else {
        builder.append(obj.toString());
      }
      builder.append(" ");
    }
    return builder.toString();
  }

  public static void v(String tag, Object... message) {
    if (isLogEnabled(LogLevel.VERBOSE) && message != null) {
      logger.v(tag, arrayToString(message));
    }
  }

  public static void d(String tag, Object... message) {
    if (isLogEnabled(LogLevel.DEBUG) && message != null) {
      logger.d(tag, arrayToString(message));
    }
  }

  public static void i(String tag, Object... message) {
    if (isLogEnabled(LogLevel.INFO) && message != null) {
      logger.i(tag, arrayToString(message));
    }
  }

  public static void w(String tag, Object... message) {
    if (isLogEnabled(LogLevel.WARN) && message != null) {
      logger.w(tag, arrayToString(message));
    }
  }

  public static void e(String tag, Object... message) {
    if (isLogEnabled(LogLevel.ERR) && message != null) {
      logger.e(tag, arrayToString(message));
    }
  }

  private static String stackTrace(Throwable t) {
    return logger.stackTrace(t);
  }

}
