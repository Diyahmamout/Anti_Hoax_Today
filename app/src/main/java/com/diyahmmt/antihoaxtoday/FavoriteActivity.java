package com.diyahmmt.antihoaxtoday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.diyahmmt.antihoaxtoday.Adapter.AdapterFavorite;
import com.diyahmmt.antihoaxtoday.Kategori.Business;
import com.diyahmmt.antihoaxtoday.Response.FavoriteNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private String image, judul, contentBerita, tanggal, penulis, link;
    private RecyclerView news;
    private AdapterFavorite adapterFavorite;
    List<FavoriteNews> list = new ArrayList<>();
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        news = findViewById(R.id.news);
        adapterFavorite = new AdapterFavorite(FavoriteActivity.this, list);

        news.setHasFixedSize(true);
        news.setLayoutManager(new LinearLayoutManager(FavoriteActivity.this));
        news.setAdapter(adapterFavorite);

        update();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void update() {
        root = FirebaseDatabase.getInstance().getReference().getRoot();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    FavoriteNews favoriteNews = data.getValue(FavoriteNews.class);
                    list.add(favoriteNews);
                    System.out.println(favoriteNews.getUrlImg());
                }

                news.setAdapter(new AdapterFavorite(FavoriteActivity.this, list));
                adapterFavorite.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(FavoriteActivity.this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FavoriteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
