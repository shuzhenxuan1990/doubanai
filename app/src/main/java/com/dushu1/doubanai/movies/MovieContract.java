package com.dushu1.doubanai.movies;

import android.widget.TextView;

import com.dushu1.doubanai.bean.Movies;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public interface MovieContract {

     interface MovieView extends BaseView<Pretenser>{
        void showProgressBar(Boolean isActive);
        void showNoMoives();
        void showMoivewList(List<Movies> movies);
        void showLoadMoreMoives(List<Movies> movies);
        void showNoLoadedMoreMovies();
    }

    interface Pretenser extends BasePretenser{
        void loadMoivesPre(Boolean fristLoad);
        void loadMoreMoives(int start);
        void cancelRetrofitRequest();
    }

}
