package com.dushu1.doubanai.moviesdetail;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Movie;

import com.dushu1.doubanai.R;
import com.dushu1.doubanai.bean.Movies;

/**
 * Created by Administrator on 2017/6/21.
 */

public class DetailPresenter implements MovieDetailContract.Mpresentor {

    private MovieDetailContract.MovieDetailView detailView;
    private Movies movies;


    public DetailPresenter(MovieDetailContract.MovieDetailView detailView, Movies movies) {
        this.detailView = detailView;
        this.movies = movies;
        detailView.setPresenter(this);
    }

    @Override
    public void start() {
       showMoviesDetail();
    }

    private void showMoviesDetail() {
        detailView.showCollapsingToolbarTitle(movies.getTitle());
        detailView.showPicassoImage(movies.getImages().getLarge());
    }

    @Override
    public void loadMoviesDetail() {

        StringBuilder sb = new StringBuilder();
        Resources resources = ((Activity)detailView).getResources();

        sb.append("导演：");
        for (Movies.DirectorsBean mdrector:movies.getDirectors()) {
              sb.append(mdrector.getName());
              sb.append(" ");
        }
        sb.append("\n");

        sb.append(resources.getString(R.string.movie_casts));
        for (Movies.CastsBean cast : movies.getCasts()) {
            sb.append(cast.getName());
            sb.append(" ");
        }
        sb.append("\n");

        //又名
        sb.append(resources.getString(R.string.movie_aka));
        sb.append(movies.getOriginal_title());
        sb.append("\n");

        sb.append(resources.getString(R.string.movie_year));
        sb.append(movies.getYear());
        sb.append("\n");

        sb.append(resources.getString(R.string.movie_genres));
        for (int index = 0; index <movies.getGenres().size(); index++) {
            sb.append(movies.getGenres().get(index));
            sb.append(" / ");
        }
        sb.append("\n");

        sb.append(resources.getString(R.string.movie_during));
        sb.append(resources.getString(R.string.movie_not_find));
        sb.append("\n");

        sb.append(resources.getString(R.string.movie_countries));
        sb.append(resources.getString(R.string.movie_not_find));
        sb.append("\n");

        sb.append(resources.getString(R.string.movie_languages));
        sb.append(resources.getString(R.string.movie_not_find));
        sb.append("\n");

        sb.append(resources.getString(R.string.movie_imdb));
        sb.append(resources.getString(R.string.movie_not_find));
        sb.append("\n");

        detailView.setMovieInfoToFragment(sb.toString());
    }

    @Override
    public void loadMoviesAlt() {
        detailView.setMovieAltToFragment(movies.getAlt());
    }
}
