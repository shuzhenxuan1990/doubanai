package com.dushu1.doubanai.moviesdetail;

import com.dushu1.doubanai.movies.BasePretenser;
import com.dushu1.doubanai.movies.BaseView;

/**
 * Created by Administrator on 2017/6/20.
 */

public interface MovieDetailContract {

    public interface MovieDetailView extends BaseView<Mpresentor>{
        /**
         * 1.展示详情页toolbar标题的方法
         * 2.展示详情页图片的的方法
         * 3.展示两个frgment内容的方法
         * @param title
         */
        void showCollapsingToolbarTitle(String title);

        void showPicassoImage(String largeImagePath);

        void setMovieInfoToFragment(String movieInfo);

        void setMovieAltToFragment(String movieAlt);


    }

    public interface Mpresentor extends BasePretenser{

        void loadMoviesDetail();
        void loadMoviesAlt();

    }

}
