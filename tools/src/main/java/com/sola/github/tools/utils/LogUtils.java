package com.sola.github.tools.utils;

import android.util.Log;

import java.util.Locale;


/**
 * Created by 禄骥
 * 2016/6/7.
 */
@SuppressWarnings("unused")
public class LogUtils {
    // ===========================================================
    // Constants
    // ===========================================================

    // TODO: 2016/6/7 打包的时候要注意修改这个DEBUG
    public static boolean DEBUG = true;

    private static final String DEFAULT_TAG = "Sola";

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public LogUtils() {
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    private static void log(String tag, Class<?> cls, String message, int logType, boolean isAlwaysShow) {
        if (isAlwaysShow || DEBUG)
            switch (logType) {
                case Log.DEBUG:
                    Log.d(tag, String.format(Locale.getDefault(),
                            "%s called() with: %s", cls == null ? "" : cls.getSimpleName(),
                            (message == null || message.isEmpty() ? "(null)" : message)));
                    break;
                case Log.ERROR:
                    Log.e(tag, String.format(Locale.getDefault(),
                            "%s error() with: %s", cls == null ? "" : cls.getSimpleName(),
                            (message == null || message.isEmpty() ? "(null)" : message)));
                    break;
                case Log.WARN:
                    Log.w(tag, String.format(Locale.getDefault(),
                            "%s warn() with: %s", cls == null ? "" : cls.getSimpleName(),
                            (message == null || message.isEmpty() ? "(null)" : message)));
                    break;
                case Log.INFO:
                    Log.i(tag, String.format(Locale.getDefault(),
                            "%s info() with: %s", cls == null ? "" : cls.getSimpleName(),
                            (message == null || message.isEmpty() ? "(null)" : message)));
                    break;
                case Log.VERBOSE:
                    Log.v(tag, String.format(Locale.getDefault(),
                            "%s verbose() with: %s", cls == null ? "" : cls.getSimpleName(),
                            (message == null || message.isEmpty() ? "(null)" : message)));
                    break;
            }
    }

    public static void d(String tag, Class<?> cls, String message) {
        d(tag, cls, message, false);
    }

    public static void d(String tag, Class<?> cls, String message, boolean isAlwaysShow) {
        log(tag, cls, message, Log.DEBUG, isAlwaysShow);
    }

    public static void d(Class<?> cls, String message, boolean isAlwaysShow) {
        d(cls.getSimpleName(), null, message, isAlwaysShow);
    }

    public static void d(String tag, String message) {
        d(tag, null, message);
    }

    public static void d(String tag, String message, boolean isAlwaysShow) {
        d(tag, null, message, isAlwaysShow);
    }

    public static void d(Class<?> tag, String message) {
        d(DEFAULT_TAG, tag, message);
    }

    public static void d(String message) {
        d(DEFAULT_TAG, message);
    }

    public static void v(String tag, Class<?> cls, String message, boolean isAlwaysShow) {
        log(tag, cls, message, Log.VERBOSE, isAlwaysShow);
    }

    public static void v(String tag, String message) {
        v(tag, null, message);
    }

    public static void v(String tag, Class<?> cls, String message) {
        log(tag, cls, message, Log.VERBOSE, false);
    }

    public static void v(String tag, String message, boolean isAlwaysShow) {
        v(tag, null, message, isAlwaysShow);
    }

    public static void v(Class<?> tag, String message) {
        v(DEFAULT_TAG, tag, message);
    }

    public static void v(String message) {
        v(DEFAULT_TAG, message);
    }

    public static void w(String tag, Class<?> cls, String message) {
        log(tag, cls, message, Log.WARN, false);
    }

    public static void w(String tag, String message) {
        w(tag, null, message);
    }

    public static void w(String tag, Class<?> cls, String message, boolean isAlwaysShow) {
        log(tag, cls, message, Log.WARN, isAlwaysShow);
    }

    @SuppressWarnings("UnusedDeclaration")
    public static void w(String tag, String message, boolean isAlwaysShow) {
        w(tag, null, message, isAlwaysShow);
    }

    @SuppressWarnings("UnusedDeclaration")
    public static void w(Class<?> tag, String message) {
        w(DEFAULT_TAG, tag, message);
    }

    @SuppressWarnings("UnusedDeclaration")
    public static void w(String message) {
        w(DEFAULT_TAG, message);
    }

    public static void i(String tag, Class<?> cls, String message) {
        log(tag, cls, message, Log.INFO, false);
    }

    public static void i(String tag, String message) {
        i(tag, null, message);
    }

    public static void i(String tag, Class<?> cls, String message, boolean isAlwaysShow) {
        log(tag, cls, message, Log.INFO, isAlwaysShow);
    }

    public static void i(String tag, String message, boolean isAlwaysShow) {
        i(tag, null, message, isAlwaysShow);
    }

    public static void i(Class<?> tag, String message) {
        i(DEFAULT_TAG, tag, message);
    }

    public static void i(String message) {
        i(DEFAULT_TAG, message);
    }

    public static void e(String tag, String message, Throwable thr) {
        Log.e(tag, message, thr);
    }

    public static void e(String tag, Class<?> cls, String message) {
        log(tag, cls, message, Log.ERROR, false);
    }

    public static void e(String tag, String message) {
        e(tag, null, message);
    }

    public static void e(String tag, Class<?> cls, String message, boolean isAlwaysShow) {
        log(tag, cls, message, Log.ERROR, isAlwaysShow);
    }

    public static void e(String tag, String message, boolean isAlwaysShow) {
        e(tag, null, message, isAlwaysShow);
    }

    public static void e(Class<?> tag, String message) {
        e(DEFAULT_TAG, tag, message);
    }

    public static void e(String message) {
        e(DEFAULT_TAG, message);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}
