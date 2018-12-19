package com.diyahmmt.antihoaxtoday.Response;

public class FavoriteNews {
    String urlImg, titleNews, sumber, deskripsi, tanggal, penulis;

    public FavoriteNews() {}

    public FavoriteNews (String deskripsi, String penulis, String sumber, String tanggal, String titleNews, String urlImg) {
        this.deskripsi = deskripsi;
        this.penulis = penulis;
        this.sumber = sumber;
        this.tanggal = tanggal;
        this.titleNews = titleNews;
        this.urlImg = urlImg;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
}
