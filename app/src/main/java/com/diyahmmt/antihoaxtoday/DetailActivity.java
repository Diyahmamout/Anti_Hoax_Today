package com.diyahmmt.antihoaxtoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    String image, judul, contentBerita, tanggal, penulis;
    ImageView imgNews;
    TextView tvJudul, tvTanggal, tvContent, tvPenulis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = getIntent().getStringExtra("imgNews");
        judul = getIntent().getStringExtra("titleNews");
        contentBerita = getIntent().getStringExtra("contentNews");
        tanggal = getIntent().getStringExtra("dateNews");
        penulis = getIntent().getStringExtra("authorNews");

        imgNews = findViewById(R.id.imageDetail);
        tvJudul = findViewById(R.id.tvJudul);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvPenulis = findViewById(R.id.tvPenulis);
        tvContent = findViewById(R.id.tvContent);

        Glide.with(getApplicationContext())
                .load(image)
                .into(imgNews);
        tvTanggal.setText(tanggal);
        tvJudul.setText(judul);
        tvPenulis.setText(penulis);
        tvContent.setText(contentBerita);
    }
}
