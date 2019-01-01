package com.example.anny.learnmvpwithretrofit2.interfaces;

import com.example.anny.learnmvpwithretrofit2.model.MovieModel;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieServices {

    @GET("marvel")
    Call<ArrayList<MovieModel>> getNoticeData();



}