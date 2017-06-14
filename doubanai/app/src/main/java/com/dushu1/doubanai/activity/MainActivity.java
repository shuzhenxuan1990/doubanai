package com.dushu1.doubanai.activity;

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
import com.dushu1.doubanai.books.booksFragment;
import com.dushu1.doubanai.movies.moviesFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.content_tab);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        HomeAdaper homeAdaper = new HomeAdaper(getSupportFragmentManager());
        homeAdaper.addFragment(new booksFragment(),getApplicationContext().getResources().getString(R.string.tab_books));
        homeAdaper.addFragment(new moviesFragment(),getApplicationContext().getResources().getString(R.string.tab_movie));

        viewPager.setAdapter(homeAdaper);

        if (tabLayout != null) {
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.setupWithViewPager(viewPager);
        }


    }

    public class HomeAdaper extends FragmentPagerAdapter {

        final  List<Fragment> fragments = new ArrayList<>();
        final  List<String> titles = new ArrayList<>();

        public void addFragment(Fragment fragment,String title){
                fragments.add(fragment);
                titles.add(title);
        }


        public HomeAdaper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
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
