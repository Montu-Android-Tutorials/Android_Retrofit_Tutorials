package com.app.android_retrofit_tutorials.app_network_call;

import com.app.android_retrofit_tutorials.app_model.Resp_get_All_Notification_for_EveryOne;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Manish Ahire on 22,January,2021,
 * manishahire.ahire221@gmail.com.
 */

public interface NetworkServiceApi {


    //Todo @Field for add data in query

    @POST("Gasco/seat-map.php")
    @FormUrlEncoded
    Call<JsonObject> testAPI(@Query("key") String key, @Field("auth_key") String auth_key, @Field("password") String password);




    @GET("notifications/get_all_notification_for_everyone/{key}/{id}/{user}")
    Call<Resp_get_All_Notification_for_EveryOne> get_All_Notification_for_EveryOne(@Path("key") String key, @Path("id") String id, @Path("user") String user,
                                                                                   @Query("limit") int limit, @Query("skip") int skip);


}
