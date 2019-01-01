package com.example.anny.learnmvpwithretrofit2.presenters;



import com.example.anny.learnmvpwithretrofit2.model.MovieModel;
import com.example.anny.learnmvpwithretrofit2.interfaces.MovieMain;

import java.util.ArrayList;

/**
 * Created by bpn on 12/7/17.
 */

public class MoviePresenterImplementation implements
        MovieMain.presenter,
        MovieMain.GetMovieIntractor.OnFinishedListener {

    private MovieMain.MainView mainView;
    private MovieMain.GetMovieIntractor getMovieIntractor;

    /**
     *
     * @param mainView
     * @param getMovieIntractor
     */
    public MoviePresenterImplementation(MovieMain.MainView mainView,
                                        MovieMain.GetMovieIntractor getMovieIntractor) {
        this.mainView = mainView;
        this.getMovieIntractor = getMovieIntractor;
    }


    @Override
    public void onDestroy() {

        if(mainView !=null) {
            mainView = null;
        }
    }

    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }

        getMovieIntractor.getMovieArrayList(this);

    }

    @Override
    public void requestDataFromIMDBServer() {
        getMovieIntractor.getMovieArrayList(this);
    }


    @Override
    public void onFinished(ArrayList<MovieModel> noticeArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(noticeArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
