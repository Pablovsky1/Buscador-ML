package com.mercadolibre.products.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppConstant {

    public static boolean DEBUG = true;
    public static final String BASE_URL = "https://api.mercadolibre.com";
    public static final int PAGE_LIMIT = 20;
    public static final int DEFAULT_OFFSET = 0;

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNW = cm.getActiveNetworkInfo();
        return activeNW != null && activeNW.isConnected();
    }

}
