package com.example.anny.learnmvpwithretrofit2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anny.learnmvpwithretrofit2.model.MovieModel;
import com.example.anny.learnmvpwithretrofit2.R;
import com.example.anny.learnmvpwithretrofit2.interfaces.RecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends   RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{

    private ArrayList<MovieModel> movieModelArrayList;
    private Context context;
    private RecyclerItemClickListener recyclerMovieItemClickListener;


    public MovieAdapter(ArrayList<MovieModel> movieModelArrayList,
                        Context context,
                        RecyclerItemClickListener recyclerMovieItemClickListener) {
        this.movieModelArrayList = movieModelArrayList;
        this.context = context;
        this.recyclerMovieItemClickListener = recyclerMovieItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.row_item_movie,viewGroup,false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, final int i) {

       // movieViewHolder.ivMovieImage.setImageURI(movieModelArrayList.get(i).getImageurl());
        movieViewHolder.tvMovieName.setText(movieModelArrayList.get(i).getName());
        movieViewHolder.tvMovieTeam.setText(movieModelArrayList.get(i).getTeam());
        movieViewHolder.tvMovieDes.setText(movieModelArrayList.get(i).getPublisher());
        movieViewHolder.tvMovieCreatedBy.setText(movieModelArrayList.get(i).getCreatedby());



        Picasso.with(context).load(movieModelArrayList.get(i).getImageurl()).into(movieViewHolder.ivMovieImage);
        movieViewHolder.ivMovieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    recyclerMovieItemClickListener.onItemClick(movieModelArrayList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModelArrayList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMovieImage;
        TextView tvMovieName, tvMovieDes, tvMovieTeam,tvMovieCreatedBy;

        MovieViewHolder(View view) {
            super(view);

            ivMovieImage = (ImageView) view.findViewById(R.id.iv_movie_image);
            tvMovieName = (TextView) view.findViewById(R.id.tv_movie_name);
            tvMovieDes = (TextView) view.findViewById(R.id.tv_movie_des);
            tvMovieTeam = (TextView) view.findViewById(R.id.tv_movie_team);
            tvMovieCreatedBy =(TextView) view.findViewById(R.id.tv_movie_created_by);

        }
    }
}