package com.app.android_retrofit_tutorials.app_utills;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;


/**
 * Created by Manish Ahire
 */


public class ProgressView {

    private static ProgressDialog progressDialog;

    public static void show(Context context) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        int style;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            style = android.R.style.Theme_Material_Light_Dialog;
        } else {
            //noinspection deprecation
            style = ProgressDialog.THEME_HOLO_LIGHT;
        }

        progressDialog = new ProgressDialog(context, style);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}