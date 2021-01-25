package com.app.android_retrofit_tutorials.app_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resp_get_All_Notification_for_EveryOne {


    @Expose
    @SerializedName("Result")
    private List<ResultEntity> result;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("success")
    private boolean success;
    @Expose
    @SerializedName("statuscode")
    private int statuscode;

    public List<ResultEntity> getResult() {
        return result;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public static class ResultEntity {
        @Expose
        @SerializedName("value")
        private String value;
        @Expose
        @SerializedName("key")
        private String key;
        @Expose
        @SerializedName("body")
        private String body;
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("notifyTime")
        private String notifytime;
        @Expose
        @SerializedName("_id")
        private String Id;

        @Expose
        @SerializedName("isSeen")
        private boolean isSeen;

        @Expose
        @SerializedName("seenTime")
        private String seenTime;

        public boolean isSeen() {
            return isSeen;
        }

        public void setSeen(boolean seen) {
            isSeen = seen;
        }

        public String getSeenTime() {
            return seenTime;
        }

        public void setSeenTime(String seenTime) {
            this.seenTime = seenTime;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNotifytime() {
            return notifytime;
        }

        public void setNotifytime(String notifytime) {
            this.notifytime = notifytime;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }
    }

}
