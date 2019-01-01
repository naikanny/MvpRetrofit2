package com.example.anny.learnmvpwithretrofit2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.anny.learnmvpwithretrofit2.model.MovieModel;
import com.example.anny.learnmvpwithretrofit2.R;
import com.example.anny.learnmvpwithretrofit2.adapter.MovieAdapter;
import com.example.anny.learnmvpwithretrofit2.presenters.GetMovieIntractorImplementation;
import com.example.anny.learnmvpwithretrofit2.interfaces.MovieMain;
import com.example.anny.learnmvpwithretrofit2.presenters.MoviePresenterImplementation;
import com.example.anny.learnmvpwithretrofit2.interfaces.RecyclerItemClickListener;

import java.util.ArrayList;

public class FirstPage extends AppCompatActivity
        implements MovieMain.MainView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MovieMain.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        initializeToolbarAndRecyclerView();
        initProgressBar();


        presenter = new MoviePresenterImplementation(this, new GetMovieIntractorImplementation());
        presenter.requestDataFromIMDBServer();

    }


    /**
     * Initializing Toolbar and RecyclerView
     */
    private void initializeToolbarAndRecyclerView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_movie);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FirstPage.this);
        recyclerView.setLayoutManager(layoutManager);


    }


    /**
     * Initializing progressbar programmatically
     * */
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }


    /**
     * RecyclerItem click event listener
     *
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(MovieModel notice) {

            Toast.makeText(FirstPage.this,
                    "MovieTitle:  " + notice.getName(),
                    Toast.LENGTH_LONG).show();

        }
    };


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<MovieModel> noticeArrayList) {


        Log.d("Movie:", noticeArrayList.toString()+ "");
        MovieAdapter adapter = new MovieAdapter(
                noticeArrayList ,
                this,
                recyclerItemClickListener);

        recyclerView.setAdapter(adapter);
    }




    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(FirstPage.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }



}

