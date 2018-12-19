package com.diyahmmt.antihoaxtoday;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    String image, judul, contentBerita, tanggal, penulis, link;
    ImageView imgNews;
    TextView tvJudul, tvTanggal, tvContent, tvPenulis, tvLink;
    ImageView imgFav;
    private DatabaseReference root;
    private Boolean isFavorite = false;

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
        isFavorite = getIntent().getBooleanExtra("favorite", false);

        imgNews = findViewById(R.id.imageDetail);
        tvJudul = findViewById(R.id.tvJudul);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvPenulis = findViewById(R.id.tvPenulis);
        tvContent = findViewById(R.id.tvContent);
        tvLink = findViewById(R.id.tvLink);
        imgFav = findViewById(R.id.imageFav);

        Glide.with(getApplicationContext())
                .load(image)
                .into(imgNews);
        tvTanggal.setText(tanggal);
        tvJudul.setText(judul);
        tvPenulis.setText(penulis);
        tvContent.setText(contentBerita);

        root = FirebaseDatabase.getInstance().getReference();

        update();
        System.out.println("FAVORITEEEEEEEE " + isFavorite);

        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) {
                    removeFavorite();
                    Toast.makeText(DetailActivity.this, "Remove Favorite", Toast.LENGTH_SHORT).show();
                } else {
                    saveData();
                    isFavorite = true;
                    Toast.makeText(DetailActivity.this, "Favorite Berita", Toast.LENGTH_SHORT).show();
                    //update();
                }
            }
        });

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
        finish();
    }

    private void removeFavorite() {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("REMOVEEEEEEE");
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String key = data.getKey();
                    for (DataSnapshot data2 : data.getChildren()) {
                        System.out.println("DATAAA KEY : " + data2.getValue().toString() + "JUDUL : " + judul);
                        if (data2.getValue().toString().equals(judul)) {
                            root.child(key).removeValue();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        isFavorite = false;
        imgFav.setImageResource(R.drawable.ic_star_border_black_24dp);
    }

    private void update() {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("UPDATEEE");
                Iterator iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    Iterator iteratorFav = ((DataSnapshot)iterator.next()).getChildren().iterator();
                    while (iteratorFav.hasNext()) {
                        iteratorFav.next();
                        iteratorFav.next();
                        iteratorFav.next();
                        iteratorFav.next();
                        String title = ((DataSnapshot)iteratorFav.next()).getValue().toString();
                        if (title.equals(judul)) {
                            imgFav.setImageResource(R.drawable.ic_star_black_24dp);
                        }
                        iteratorFav.next();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void saveData() {
        String tempKey = root.push().getKey();
        DatabaseReference data = root.child(tempKey);
        Map<String, Object> map = new HashMap<>();
        map.put("urlImg", image);
        map.put("titleNews", judul);
        map.put("deskripsi", contentBerita);
        map.put("tanggal", tanggal);
        map.put("penulis", penulis);
        map.put("sumber", link);
        System.out.println("HASILLLLLLLLLLLLLLLLLLLL "+map);
        data.updateChildren(map);
        imgFav.setImageResource(R.drawable.ic_star_black_24dp);
    }
}
