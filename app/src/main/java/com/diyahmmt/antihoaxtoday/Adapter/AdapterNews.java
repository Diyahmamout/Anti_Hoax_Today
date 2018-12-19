package com.diyahmmt.antihoaxtoday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.diyahmmt.antihoaxtoday.Response.News;

import java.util.List;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {

    private Context context;
    private List<News> list;

    public AdapterNews(Context context, List<News> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_news, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final News news = list.get(i);
        Glide.with(context).load(news.getImgNews())
                .into(viewHolder.imageView);
        viewHolder.judul.setText(news.getTitleNews());
        viewHolder.tanggal.setText(news.getDateNews().substring(0,10) + " " + news.getDateNews().substring(11,16));
        /*if (news.getAuthorNews() == null) {
            viewHolder.penulis.setText("Penulis Tidak diketahui");
        } else {
            viewHolder.penulis.setText(news.getAuthorNews());
        } */

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iDetail = new Intent(context, DetailActivity.class);
                iDetail.putExtra("imgNews", news.getImgNews());
                iDetail.putExtra("titleNews", news.getTitleNews());
                if (news.getContentNews() == null) {
                    iDetail.putExtra("contentNews", "Tidak ada Konten berita");
                } else {
                    iDetail.putExtra("contentNews", news.getDeskripsiNews());
                }
                iDetail.putExtra("dateNews", news.getDateNews().substring(0, 10) + " " + news.getDateNews().substring(11, 16));
                if (news.getAuthorNews() == null) {
                    iDetail.putExtra("authorNews", "Penulis tidak diketahui");
                } else {
                    iDetail.putExtra("authorNews", news.getAuthorNews());
                }
                iDetail.putExtra("sourceNews", news.getSourceNews());
                context.startActivity(iDetail);
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
        private TextView judul, tanggal, penulis;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cv);
            imageView = itemView.findViewById(R.id.gambar);
            judul = itemView.findViewById(R.id.titleNews);
            tanggal = itemView.findViewById(R.id.tgl);
            //penulis = itemView.findViewById(R.id.penulis);
        }
    }
}
