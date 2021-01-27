package com.app.android_retrofit_tutorials.app_network_call;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;

import com.app.android_retrofit_tutorials.app_constants.Web_Contants;
import com.app.android_retrofit_tutorials.app_model.Resp_get_All_Notification_for_EveryOne;
import com.app.android_retrofit_tutorials.app_model.Response_getUsers;

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
 * Created by Manish Ahire on 22,January,2021,
 * manishahire.ahire221@gmail.com.
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


    public void getUsers() {
        try {
            Retrofit mRetrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(initHttpClient().build())
                    .baseUrl(Web_Contants._baseURl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            NetworkServiceApi serviceApi = mRetrofit.create(NetworkServiceApi.class);
            Call<Response_getUsers> call = serviceApi.getUsers();
            call.enqueue(new Callback<Response_getUsers>() {
                @Override
                public void onResponse(@NonNull Call<Response_getUsers> call, @NonNull Response<Response_getUsers> response) {

                    if (response.code() == 200)
                        mNotifier.notifySuccess(response);
                    else
                        mNotifier.notifyString("Something Went Wrong Please Try Again....!!!");

                }

                @Override
                public void onFailure(@NonNull Call<Response_getUsers> call, @NonNull Throwable t) {
                    Log.e("API_Error  ", "Error Response_getUsers " + t.getMessage());

                    mNotifier.notifyError(t);
                }
            });
        } catch (Exception e) {
            Log.e("JSSOOONNN  ", "Error Response_getUsers " + e.getMessage());

            e.printStackTrace();
        }
    }


}

