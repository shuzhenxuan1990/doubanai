package com.dushu1.doubanai.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dushu1.doubanai.R;
import com.dushu1.doubanai.api.IdoubanService;
import com.dushu1.doubanai.api.doubanManager;
import com.dushu1.doubanai.base.BaseActivity;
import com.dushu1.doubanai.base.BaseHomeAdapter;
import com.dushu1.doubanai.books.booksFragment;
import com.dushu1.doubanai.books.booksPresentor;
import com.dushu1.doubanai.movies.MoviePresenter;
import com.dushu1.doubanai.movies.moviesFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public static final String TAG = "COLO";
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        initFloatButton();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        SetupViewPager(viewPager);
        initTablayout();
    }

    @Override
    protected void initVersible() {

    }


    private void SetupViewPager(ViewPager viewPager){

        moviesFragment mfrgment = new moviesFragment();
        booksFragment bfrgment =  new booksFragment();

        HomeAdaper homeAdaper = new HomeAdaper(getSupportFragmentManager());

        homeAdaper.addFragment(mfrgment,getApplicationContext().getResources().getString(R.string.tab_movie));
        homeAdaper.addFragment(bfrgment,getApplicationContext().getResources().getString(R.string.tab_books));

        viewPager.setAdapter(homeAdaper);
        createPreserter(bfrgment,mfrgment);
    }


    private void initTablayout() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.content_tab);

        if (tabLayout != null) {
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void initFloatButton() {
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void createPreserter(booksFragment bfrgment,moviesFragment mfrgment) {
        new booksPresentor(doubanManager.getService(),bfrgment);
        new MoviePresenter(doubanManager.getService(),mfrgment);
    }


    public static class HomeAdaper extends BaseHomeAdapter {

        public HomeAdaper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void addFragment(Fragment fragment, String title) {
            mfragments.add(fragment);
            mtitles.add(title);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
