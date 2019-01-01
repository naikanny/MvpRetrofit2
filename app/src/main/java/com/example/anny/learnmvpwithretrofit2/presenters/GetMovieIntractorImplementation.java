package com.example.anny.learnmvpwithretrofit2.presenters;

import android.util.Log;


import com.example.anny.learnmvpwithretrofit2.model.MovieModel;
import com.example.anny.learnmvpwithretrofit2.interfaces.MovieMain;
import com.example.anny.learnmvpwithretrofit2.interfaces.MovieServices;
import com.example.anny.learnmvpwithretrofit2.retrofit.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bpn on 12/7/17.
 */

public class GetMovieIntractorImplementation implements MovieMain.GetMovieIntractor {

    @Override
    public void getMovieArrayList(final OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        MovieServices service = RetrofitInstance.getRetrofitInstance().create(MovieServices.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<ArrayList<MovieModel>> call = service.getNoticeData();
        call.enqueue(new Callback<ArrayList<MovieModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {


                onFinishedListener.onFinished(response.body());
                Log.d("Data Rescevied:", response.body().toString()+ "");

            }

            @Override
            public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

}
