package com.diyahmmt.antihoaxtoday.Response;

import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("urlToImage")
    String img;
    @SerializedName("title")
    String judul;
    @SerializedName("publishedAt")
    String tgl;
    @SerializedName("author")
    String penulis;
    @SerializedName("description")
    String deskripsi;
    @SerializedName("url")
    String sumber;
    @SerializedName("content")
    String contentNews;

    public News(String img, String judul, String tgl, String penulis, String deskripsi, String sumber, String contentNews) {
        this.img = img;
        this.judul = judul;
        this.tgl = tgl;
        this.penulis = penulis;
        this.deskripsi = deskripsi;
        this.sumber = sumber;
        this.contentNews = contentNews;
    }

    public String getImgNews() {
        return img;
    }

    public String getTitleNews() {
        return judul;
    }

    public String getDateNews() {
        return tgl;
    }

    public String getAuthorNews() {
        return penulis;
    }

    public String getDeskripsiNews() {
        return deskripsi;
    }

    public String getContentNews() {
        return contentNews;
    }

    public String getSourceNews() {
        return sumber;
    }
}
