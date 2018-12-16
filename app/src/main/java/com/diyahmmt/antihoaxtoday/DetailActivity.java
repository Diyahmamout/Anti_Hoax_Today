package com.diyahmmt.antihoaxtoday;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    String image, judul, contentBerita, tanggal, penulis, link;
    ImageView imgNews;
    TextView tvJudul, tvTanggal, tvContent, tvPenulis, tvLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = getIntent().getStringExtra("imgNews");
        judul = getIntent().getStringExtra("titleNews");
        contentBerita = getIntent().getStringExtra("contentNews");
        tanggal = getIntent().getStringExtra("dateNews");
        penulis = getIntent().getStringExtra("authorNews");
        link = getIntent().getStringExtra("sourceNews");

        imgNews = findViewById(R.id.imageDetail);
        tvJudul = findViewById(R.id.tvJudul);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvPenulis = findViewById(R.id.tvPenulis);
        tvContent = findViewById(R.id.tvContent);
        tvLink = findViewById(R.id.tvLink);

        Glide.with(getApplicationContext())
                .load(image)
                .into(imgNews);
        tvTanggal.setText(tanggal);
        tvJudul.setText(judul);
        tvPenulis.setText(penulis);
        tvContent.setText(contentBerita);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void Link(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
