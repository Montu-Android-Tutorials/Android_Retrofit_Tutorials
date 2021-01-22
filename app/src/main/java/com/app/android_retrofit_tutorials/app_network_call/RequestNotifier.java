package com.app.android_retrofit_tutorials.app_network_call;

import com.google.gson.JsonObject;

import retrofit2.Response;

/**
 * Created by Manish Ahire
 */


public interface RequestNotifier {

    void notifySuccess(JsonObject response);
    void notifyNoInternet();
    void notifyError(Throwable throwable);
    void notifyString(String s);

}
