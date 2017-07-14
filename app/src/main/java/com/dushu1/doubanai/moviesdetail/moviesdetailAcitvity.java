package com.dushu1.doubanai.moviesdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.dushu1.doubanai.R;
import com.dushu1.doubanai.base.BaseActivity;
import com.dushu1.doubanai.base.BaseHomeAdapter;
import com.dushu1.doubanai.bean.Movies;
import com.dushu1.doubanai.utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */

public class moviesdetailAcitvity extends BaseActivity implements MovieDetailContract.MovieDetailView{


    private String mMovieInfo = null;
    private String mMovieAlt = null;
    private MovieDetailContract.Mpresentor presentor;


    @Override
    protected void initView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_movie_detail);
        Movies movies = (Movies)getIntent().getSerializableExtra(AppConstant.INTENT_EXTRA_MOVIE);
        new DetailPresenter(this,movies);
        ViewPager viewPager = (ViewPager) findViewById(R.id.movie_viewpager1);
        setUpViewPager(viewPager);
        setUpTabLayout(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presentor.start();
    }

    @Override
    public void setPresenter(MovieDetailContract.Mpresentor presenter) {
        this.presentor = presenter;
    }

    private void setUpTabLayout(ViewPager viewPager) {

        TabLayout tabLayout = (TabLayout) findViewById(R.id.movie_sliding_tabs);
        if (tabLayout != null) {
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void setUpViewPager(ViewPager viewPager) {

        presentor.loadMoviesDetail();
        presentor.loadMoviesAlt();

        MoivewDetaliAdapter adapter = new MoivewDetaliAdapter(getSupportFragmentManager());
        moviesdetailFragment moviesMsg = (moviesdetailFragment) moviesdetailFragment.getInstance(mMovieInfo,AppConstant.TYPE_MOVIE_INFO);
        moviesdetailFragment moviesInterduce = (moviesdetailFragment) moviesdetailFragment.getInstance(mMovieAlt,AppConstant.TYPE_MOVIE_WEBSITE);
        adapter.addFragment(moviesMsg,getApplicationContext().getResources().getString(R.string.movie_detail_info));
        adapter.addFragment(moviesInterduce,getApplicationContext().getResources().getString(R.string.movie_detail_interduce));
        viewPager.setAdapter(adapter);

    }




    @Override
    protected void initVersible() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }





    @Override
    public void showCollapsingToolbarTitle(String title) {

        CollapsingToolbarLayout toolBar = (CollapsingToolbarLayout) findViewById(R.id.movie_collapsing_toolbar);
        toolBar.setTitle(title);
    }

    @Override
    public void showPicassoImage(String largeImagePath) {
        ImageView imageView = (ImageView) findViewById(R.id.movie_image);
        Picasso.with(this)
                .load(largeImagePath)
                .placeholder(getResources().getDrawable(R.mipmap.ic_launcher))
                .into(imageView);
    }

    @Override
    public void setMovieInfoToFragment(String movieInfo) {
        mMovieInfo = movieInfo;
    }

    @Override
    public void setMovieAltToFragment(String movieAlt) {
        mMovieAlt = movieAlt;
    }


   static class MoivewDetaliAdapter extends BaseHomeAdapter{


       public MoivewDetaliAdapter(FragmentManager fm) {
           super(fm);
       }

       @Override
       public void addFragment(Fragment fragment, String title) {
            mfragments.add(fragment);
            mtitles.add(title);
       }
   }

}
