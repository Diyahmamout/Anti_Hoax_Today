package com.diyahmmt.antihoaxtoday.Kategori;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.diyahmmt.antihoaxtoday.API.ApiService;
import com.diyahmmt.antihoaxtoday.API.Server;
import com.diyahmmt.antihoaxtoday.Adapter.AdapterNews;
import com.diyahmmt.antihoaxtoday.BuildConfig;
import com.diyahmmt.antihoaxtoday.MainActivity;
import com.diyahmmt.antihoaxtoday.R;
import com.diyahmmt.antihoaxtoday.Response.News;
import com.diyahmmt.antihoaxtoday.Response.ResponseNews;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Technology extends AppCompatActivity {

    private RecyclerView news;
    private AdapterNews adapterNews;
    List<News> list = new ArrayList<>();
    final String category = "technology";
    ProgressDialog loading;
    ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);

        news = findViewById(R.id.news);
        api = Server.getApiService();
        adapterNews = new AdapterNews(Technology.this, list);

        news.setHasFixedSize(true);
        news.setLayoutManager(new LinearLayoutManager(Technology.this));
        news.setAdapter(adapterNews);
        update();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void update() {
        loading = new ProgressDialog(Technology.this);
        loading.setCancelable(false);
        loading.setMessage("Loading Data ...");
        showDialog();
        api.getListNews("id", category, BuildConfig.NEWS_API_TOKEN).enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                if (response.isSuccessful()) {
                    hideDialog();
                    list = response.body().getNewsList();
                    news.setAdapter(new AdapterNews(Technology.this, list));
                    adapterNews.notifyDataSetChanged();
                } else {
                    hideDialog();
                    Toast.makeText(Technology.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                hideDialog();
                Toast.makeText(Technology.this, "Cek Koneksi Internet Anda !! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog() {
        if (!loading.isShowing())
            loading.show();
    }

    private void hideDialog() {
        if (loading.isShowing())
            loading.dismiss();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(Technology.this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Technology.this, MainActivity.class);
        startActivity(intent);
    }
}
