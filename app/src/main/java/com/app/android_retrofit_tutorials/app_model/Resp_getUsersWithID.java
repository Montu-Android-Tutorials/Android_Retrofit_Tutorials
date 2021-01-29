package com.app.android_retrofit_tutorials.app_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Manish Ahire on 29,January,2021,
 * manishahire.ahire221@gmail.com.
 */
public class Resp_getUsersWithID {


    @Expose
    @SerializedName("support")
    private SupportEntity support;
    @Expose
    @SerializedName("data")
    private DataEntity data;

    public SupportEntity getSupport() {
        return support;
    }

    public void setSupport(SupportEntity support) {
        this.support = support;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class SupportEntity {
        @Expose
        @SerializedName("text")
        private String text;
        @Expose
        @SerializedName("url")
        private String url;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class DataEntity {
        @Expose
        @SerializedName("avatar")
        private String avatar;
        @Expose
        @SerializedName("last_name")
        private String lastName;
        @Expose
        @SerializedName("first_name")
        private String firstName;
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("id")
        private int id;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
