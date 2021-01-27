package com.app.android_retrofit_tutorials.app_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Manish Ahire on 27,January,2021,
 * manishahire.ahire221@gmail.com.
 */
public class Response_getUsers {


    @Expose
    @SerializedName("support")
    private SupportEntity Support;
    @Expose
    @SerializedName("data")
    private List<DataEntity> Data;
    @Expose
    @SerializedName("total_pages")
    private int TotalPages;
    @Expose
    @SerializedName("total")
    private int Total;
    @Expose
    @SerializedName("per_page")
    private int PerPage;
    @Expose
    @SerializedName("page")
    private int Page;

    public SupportEntity getSupport() {
        return Support;
    }

    public void setSupport(SupportEntity Support) {
        this.Support = Support;
    }

    public List<DataEntity> getData() {
        return Data;
    }

    public void setData(List<DataEntity> Data) {
        this.Data = Data;
    }

    public int getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(int TotalPages) {
        this.TotalPages = TotalPages;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public int getPerPage() {
        return PerPage;
    }

    public void setPerPage(int PerPage) {
        this.PerPage = PerPage;
    }

    public int getPage() {
        return Page;
    }

    public void setPage(int Page) {
        this.Page = Page;
    }

    public static class SupportEntity {
        @Expose
        @SerializedName("text")
        private String Text;
        @Expose
        @SerializedName("url")
        private String Url;

        public String getText() {
            return Text;
        }

        public void setText(String Text) {
            this.Text = Text;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }

    public static class DataEntity {
        @Expose
        @SerializedName("avatar")
        private String Avatar;
        @Expose
        @SerializedName("last_name")
        private String LastName;
        @Expose
        @SerializedName("first_name")
        private String FirstName;
        @Expose
        @SerializedName("email")
        private String Email;
        @Expose
        @SerializedName("id")
        private int Id;

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String LastName) {
            this.LastName = LastName;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String FirstName) {
            this.FirstName = FirstName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
    }
}
