package com.app.android_retrofit_tutorials.app_constants;

import android.app.Activity;
import android.content.Context;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class AppAlert {


    public static void callAlert(Context context, String message) {
        Snackbar.make((((Activity) context).findViewById(android.R.id.content)),  message, BaseTransientBottomBar.LENGTH_SHORT).show();
    }


    public static void _noInternetAlert(Context context) {
        Snackbar.make((((Activity) context).findViewById(android.R.id.content)), "Internet Not Available", BaseTransientBottomBar.LENGTH_LONG).show();
    }


}
