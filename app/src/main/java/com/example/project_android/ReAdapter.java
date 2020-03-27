package com.example.project_android;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.MyViewHolder> {
    private ArrayList<MoviesActivity> filmlist;

    public ReAdapter(ArrayList<MoviesActivity> list){
        this.filmlist = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_movies,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final MoviesActivity datamovies = filmlist.get(position);
        Glide.with(holder.itemView.getContext())
                .load(datamovies.getImgs())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imagePhotos);
        holder.movieName.setText(datamovies.getMovie());
        holder.movieDetail.setText(datamovies.getDeskripsi());
        holder.btnSynopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(v.getContext(),SynopsisActivity.class);
                profile.putExtra("photos", datamovies.getImgs());
                profile.putExtra("title",datamovies.getMovie());
                profile.putExtra("synopsis",datamovies.getDeskripsi());
                v.getContext().startActivity(profile);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imagePhotos;
        TextView movieName, movieDetail;
        Button btnSynopsis;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePhotos = itemView.findViewById(R.id.myImageView);
            movieName = itemView.findViewById(R.id.myText1);
            movieDetail = itemView.findViewById(R.id.myText2);
            btnSynopsis = itemView.findViewById(R.id.button_synopsis);
        }
    }


}
