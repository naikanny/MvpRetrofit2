package com.example.anny.learnmvpwithretrofit2.interfaces;



import com.example.anny.learnmvpwithretrofit2.model.MovieModel;

import java.util.ArrayList;


public interface MovieMain {


    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<MovieModel> noticeArrayList);

        void onResponseFailure(Throwable throwable);

    }


    interface presenter{

        void onDestroy();

        void requestDataFromIMDBServer();

    }


    interface GetMovieIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<MovieModel> noticeArrayList);
            void onFailure(Throwable t);
        }

        void getMovieArrayList(OnFinishedListener onFinishedListener);
    }
}
