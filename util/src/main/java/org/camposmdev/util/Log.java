package org.camposmdev.util;

import com.almasb.fxgl.logging.Logger;

public class Log {
    private Log() {}

    private static Class<?> getCallerClass() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        try {
            return Class.forName(stackTrace[3].getClassName());
        } catch (ClassNotFoundException e) {
            return Object.class;
        }
    }

    public static void debug(String message) {
        Logger.get(getCallerClass()).debug(message);
    }

    public static void debugf(String format, Object... args) {
        Logger.get(getCallerClass()).debugf(format, args);
    }

    public static void info(String message) {
        Logger.get(getCallerClass()).info(message);
    }

    public static void infof(String format, Object... args) {
        Logger.get(getCallerClass()).infof(format, args);
    }

    public static void warn(String message) {
        Logger.get(getCallerClass()).warning(message);
    }

    public static void warn(String message, Throwable error) {
        Logger.get(getCallerClass()).fatal(message, error);
    }

    public static void warnf(String format, Object... args) {
        Logger.get(getCallerClass()).warningf(format, args);
    }

    public static void fatal(String message) {
        Logger.get(getCallerClass()).fatal(message);
    }

    public static void fatal(String message, Throwable error) {
        Logger.get(getCallerClass()).fatal(message, error);
    }

    public static void fatalf(String format, Object... args) {
        Logger.get(getCallerClass()).fatalf(format, args);
    }
}
