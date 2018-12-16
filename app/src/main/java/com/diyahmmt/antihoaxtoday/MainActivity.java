package com.diyahmmt.antihoaxtoday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.diyahmmt.antihoaxtoday.API.ApiService;
import com.diyahmmt.antihoaxtoday.API.Server;
import com.diyahmmt.antihoaxtoday.Adapter.AdapterNews;
import com.diyahmmt.antihoaxtoday.Kategori.Business;
import com.diyahmmt.antihoaxtoday.Kategori.Entertainment;
import com.diyahmmt.antihoaxtoday.Kategori.Health;
import com.diyahmmt.antihoaxtoday.Kategori.Science;
import com.diyahmmt.antihoaxtoday.Kategori.Sports;
import com.diyahmmt.antihoaxtoday.Kategori.Technology;
import com.diyahmmt.antihoaxtoday.Response.News;
import com.diyahmmt.antihoaxtoday.Response.ResponseNews;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView news;
    private AdapterNews adapterNews;
    List<News> list = new ArrayList<>();
    ProgressDialog loading;
    ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        news = findViewById(R.id.news);
        api = Server.getApiService();
        adapterNews = new AdapterNews(MainActivity.this, list);

        news.setHasFixedSize(true);
        news.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        news.setAdapter(adapterNews);
        update();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void update() {
        loading = new ProgressDialog(MainActivity.this);
        loading.setCancelable(false);
        loading.setMessage("Loading Data ...");
        showDialog();
        api.getListAllNews("id", BuildConfig.NEWS_API_TOKEN).enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                if (response.isSuccessful()) {
                    hideDialog();
                    list = response.body().getNewsList();
                    news.setAdapter(new AdapterNews(MainActivity.this, list));
                    adapterNews.notifyDataSetChanged();
                } else {
                    hideDialog();
                    Toast.makeText(MainActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                hideDialog();
                Toast.makeText(MainActivity.this, "Cek Koneksi Internet Anda !! ", Toast.LENGTH_SHORT).show();
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_beranda) {
            Intent beranda = new Intent(MainActivity.this, MainActivity.class);
            startActivity(beranda);
        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_business) {
            Intent bisnis = new Intent(MainActivity.this, Business.class);
            startActivity(bisnis);
        } else if (id == R.id.nav_science) {
            Intent science = new Intent(MainActivity.this, Science.class);
            startActivity(science);
        } else if (id == R.id.nav_sports) {
            Intent sports = new Intent(MainActivity.this, Sports.class);
            startActivity(sports);
        } else if (id == R.id.nav_technology) {
            Intent technology = new Intent(MainActivity.this, Technology.class);
            startActivity(technology);
        } else if (id == R.id.nav_health) {
            Intent health = new Intent(MainActivity.this, Health.class);
            startActivity(health);
        } else if (id == R.id.nav_entertainment) {
            Intent entertainment = new Intent(MainActivity.this, Entertainment.class);
            startActivity(entertainment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
