package com.mercadolibre.products.util;

public class MyLog {

    public static void i(String tag, String msg) {

        if (AppConstant.DEBUG) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (AppConstant.DEBUG) {
            android.util.Log.e(tag, msg);

        }
    }

    public static void e(String tag, String msg, Exception e) {
        if (AppConstant.DEBUG) {
            android.util.Log.e(tag, msg, e);

        }
    }

    public static void e(String tag, Exception e) {
        if (AppConstant.DEBUG) {
            android.util.Log.e(tag, "", e);

        }
    }

    public static void v(String tag, String msg) {
        if (AppConstant.DEBUG) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (AppConstant.DEBUG) {
            android.util.Log.d(tag, msg);
        }
    }

}