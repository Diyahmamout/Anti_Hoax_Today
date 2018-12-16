package com.diyahmmt.antihoaxtoday.API;

public class Server {
    public static final String URL_API = "https://newsapi.org/";

    public static ApiService getApiService() {
        return Api.getClient(URL_API).create(ApiService.class);
    }
}
