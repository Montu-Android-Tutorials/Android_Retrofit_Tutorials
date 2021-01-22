package com.app.android_retrofit_tutorials.app_network_call;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;

import com.app.smsipltest.model.Resp_testAPI;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Manish Ahire
 */

public class NetworkCall {

    private Activity mActivity;
    private Context mContext;
    private RequestNotifier mNotifier;
    private ConnectivityManager connectivityManager;
    private NetworkInfo activeNetworkInfo;

    public NetworkCall(Activity mActivity, RequestNotifier mNotifier) {
        this.mActivity = mActivity;
        this.mNotifier = mNotifier;
    }


    public NetworkCall(Context mContext1, RequestNotifier mNotifier1) {
        this.mContext = mContext1;
        this.mNotifier = mNotifier1;
    }

    public boolean isNetworkAvailable() {

        if (mActivity != null) {
            connectivityManager = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        } else if (mContext != null) {
            connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private OkHttpClient.Builder initHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES);

        httpClient.addInterceptor(logging).build();// when makig live comment this link
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request =
                        chain.request().newBuilder()
                                .addHeader("Token", "")
                                .build();
                return chain.proceed(request);
            }
        }).build();
        return httpClient;
    }


/*
    public void testAPI(String key, String auth_key, String password) {
        if (isNetworkAvailable()) {
            try {
                Retrofit mRetrofit = new Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(initHttpClient().build())
                        .baseUrl("https://www.smsipl.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                NetworkServiceApi serviceApi = mRetrofit.create(NetworkServiceApi.class);
                Call<Resp_testAPI> call = serviceApi.testAPI(key, auth_key, password);
                call.enqueue(new Callback<Resp_testAPI>() {
                    @Override
                    public void onResponse(@NonNull Call<Resp_testAPI> call, @NonNull Response<Resp_testAPI> response) {
                        mNotifier.notifySuccess(response);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Resp_testAPI> call, @NonNull Throwable t) {
                        Log.e("API_Error  ", "Error  Resp_testAPI->" + t.getMessage());

                        mNotifier.notifyError(t);
                    }
                });
            } catch (Exception e) {
                Log.e("Error-->", "Resp_testAPI->" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            mNotifier.notifyNoInternet();
        }
    }
*/


    public void testAPI(String key, String auth_key, String password) {
        if (isNetworkAvailable()) {
            try {
                Retrofit mRetrofit = new Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(initHttpClient().build())
                        .baseUrl("https://www.smsipl.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                NetworkServiceApi serviceApi = mRetrofit.create(NetworkServiceApi.class);
                Call<JsonObject> call = serviceApi.testAPI(key, auth_key, password);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                        mNotifier.notifySuccess(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                        Log.e("API_Error  ", "Error  Resp_testAPI->" + t.getMessage());

                        mNotifier.notifyError(t);
                    }
                });
            } catch (Exception e) {
                Log.e("Error-->", "Resp_testAPI->" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            mNotifier.notifyNoInternet();
        }
    }


}

