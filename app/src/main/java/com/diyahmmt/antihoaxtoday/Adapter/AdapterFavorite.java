package com.diyahmmt.antihoaxtoday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diyahmmt.antihoaxtoday.DetailActivity;
import com.diyahmmt.antihoaxtoday.R;
import com.diyahmmt.antihoaxtoday.Response.FavoriteNews;

import java.util.List;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder>{
    private String image, judul, contentBerita, tanggal, penulis, link;
    private Context context;
    private List<FavoriteNews> list;

    public AdapterFavorite(Context context, List<FavoriteNews> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_news, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new AdapterFavorite.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        final FavoriteNews favoriteNews = list.get(i);
        Glide.with(context).load(favoriteNews.getUrlImg()).into(viewHolder.imageView);
        viewHolder.tvjudul.setText(favoriteNews.getTitleNews());
        viewHolder.tvtanggal.setText(favoriteNews.getTanggal());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("contentNews", favoriteNews.getDeskripsi());
                i.putExtra("dateNews", favoriteNews.getTanggal());
                i.putExtra("authorNews", favoriteNews.getPenulis());
                i.putExtra("sourceNews", favoriteNews.getSumber());
                i.putExtra("titleNews", favoriteNews.getTitleNews());
                i.putExtra("imgNews", favoriteNews.getUrlImg());
                i.putExtra("favorite", true);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView;
        private TextView tvjudul, tvtanggal, tvpenulis;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cv);
            imageView = itemView.findViewById(R.id.gambar);
            tvjudul = itemView.findViewById(R.id.titleNews);
            tvtanggal = itemView.findViewById(R.id.tgl);
            //penulis = itemView.findViewById(R.id.penulis);
        }
    }
}
