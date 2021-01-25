package com.app.android_retrofit_tutorials.app_utills;

import android.app.Activity;
import android.content.Context;

import com.app.android_retrofit_tutorials.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class AppAlert {

    public static void callError(Context context) {
        Snackbar.make((((Activity) context).findViewById(android.R.id.content)), context.getResources().getString(R.string.error) + ", " + context.getResources().getString(R.string.something_went_wrong), BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    public static void callAlert(Context context, String message) {
        Snackbar.make((((Activity) context).findViewById(android.R.id.content)),  message, BaseTransientBottomBar.LENGTH_SHORT).show();
    }


    public static void _noInternetAlert(Context context) {
        Snackbar.make((((Activity) context).findViewById(android.R.id.content)), "Internet Not Available", BaseTransientBottomBar.LENGTH_LONG).show();
    }

    public static void callNotamlAlert(Context context,String title, String message) {
        Snackbar.make((((Activity) context).findViewById(android.R.id.content)),  title+", "+message, BaseTransientBottomBar.LENGTH_LONG).show();
    }

}
