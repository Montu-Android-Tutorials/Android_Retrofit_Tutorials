package com.app.android_retrofit_tutorials.app_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Manish Ahire on 29,January,2021,
 * manishahire.ahire221@gmail.com.
 */
public class Resp_postUserJob {


    @Expose
    @SerializedName("createdAt")
    private String createdat;
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("job")
    private String job;
    @Expose
    @SerializedName("name")
    private String name;

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
