package com.app.android_retrofit_tutorials.app_network_call;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Manish Ahire
 */


public class ConnectionDetector {

    private Activity _context;

    public ConnectionDetector(Activity _context) {
        this._context = _context;
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public boolean isConnectedToInternet() {
        try {
            if (_context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(CONNECTIVITY_SERVICE);
                assert connectivityManager != null;
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isConnectedToInternet(Context _context) {
        try {
            if (_context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(CONNECTIVITY_SERVICE);
                assert connectivityManager != null;
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
