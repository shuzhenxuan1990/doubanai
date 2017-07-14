package com.dushu1.doubanai.movies;

import android.util.Log;

import com.dushu1.doubanai.activity.MainActivity;
import com.dushu1.doubanai.api.IdoubanService;
import com.dushu1.doubanai.api.doubanManager;
import com.dushu1.doubanai.bean.HotMovieInfo;
import com.dushu1.doubanai.bean.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/19.
 * 这个是service和view沟通的桥梁
 */

public class MoviePresenter implements MovieContract.Pretenser {

    private IdoubanService idoubanService;

    private MovieContract.MovieView  mfrgment;

    private boolean mfristLoad = true;

    private int totalCount;

    private Call<HotMovieInfo> movieRequestCall;

    public MoviePresenter(IdoubanService idoubanService, MovieContract.MovieView mfrgment) {
        this.idoubanService = idoubanService;
        this.mfrgment = mfrgment;
        //将presenter传入view中，获得presenter对象（便于使用得到数据和方法）将加载数据与view进行分离
        //实现解耦
        mfrgment.setPresenter(this);
    }

    @Override
    public void start() {
        loadMoivesPre(true);
    }

    @Override
    public void loadMoivesPre(Boolean forceLoad) {
        loadMoives( forceLoad || mfristLoad,true);
        mfristLoad = false;
    }


    public void loadMoives(Boolean forceLoad , final Boolean showLoadingUI) {

        if (showLoadingUI) {
            mfrgment.showProgressBar(true);
        }
        if (forceLoad) {
            movieRequestCall = idoubanService.seacheHotMovie(0);
            movieRequestCall.enqueue(new Callback<HotMovieInfo>() {
                @Override
                public void onResponse(Call<HotMovieInfo> call, Response<HotMovieInfo> response) {
                    Log.d(moviesFragment.TAG, "===> onResponse: Thread.Id = " + Thread.currentThread().getId());
                    List<Movies> movies = response.body().getMovies();
                    totalCount = response.body().getTotal();
                    if (showLoadingUI) {
                        mfrgment.showProgressBar(false);
                    }
                    processData(movies);
                }

                @Override
                public void onFailure(Call<HotMovieInfo> call, Throwable t) {
                    Log.d(moviesFragment.TAG, "===> onResponse: Thread.Id = " + Thread.currentThread().getId());
                    if (showLoadingUI) {
                        mfrgment.showProgressBar(false);
                    }

                }
            });
        }
    }

    @Override
    public void loadMoreMoives(int startIndex) {

        if(startIndex >= totalCount) {
            mfrgment.showNoLoadedMoreMovies();
            return;
        }


            movieRequestCall = idoubanService.seacheHotMovie(startIndex);
            movieRequestCall.enqueue(new Callback<HotMovieInfo>() {
                @Override
                public void onResponse(Call<HotMovieInfo> call, Response<HotMovieInfo> response) {
                    Log.d(moviesFragment.TAG, "===> onResponse: Thread.Id = " + Thread.currentThread().getId());
                    List<Movies> movies = response.body().getMovies();
                    processMoreData(movies);
                }

                @Override
                public void onFailure(Call<HotMovieInfo> call, Throwable t) {
                    Log.d(moviesFragment.TAG, "===> onResponse: Thread.Id = " + Thread.currentThread().getId());
                    mfrgment.showNoMoives();
                }
            });


    }

    @Override
    public void cancelRetrofitRequest() {

        Log.e(MainActivity.TAG,   "=> cancelRetrofitRequest() isCanceled = " + movieRequestCall.isCanceled());

        if (!movieRequestCall.isCanceled()) {
            movieRequestCall.cancel();
        }
    }

    private void processData(List<Movies> movies) {

        if (movies.isEmpty()) {
            mfrgment.showNoMoives();
        }else {
            mfrgment.showMoivewList(movies);
        }
        
    }

    private void processMoreData(List<Movies> movies) {

        if (movies.isEmpty()) {
            mfrgment.showNoMoives();
        }else {
            mfrgment.showLoadMoreMoives(movies);
        }

    }

}
