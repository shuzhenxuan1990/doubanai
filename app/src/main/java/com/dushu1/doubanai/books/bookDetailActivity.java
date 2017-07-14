package com.dushu1.doubanai.books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.dushu1.doubanai.R;
import com.dushu1.doubanai.base.BaseActivity;
import com.dushu1.doubanai.base.BaseHomeAdapter;
import com.dushu1.doubanai.bean.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */

public class bookDetailActivity extends BaseActivity {


    private CollapsingToolbarLayout toolbar_layout;
    private ViewPager view_pager;
    private Book books;

    @Override
    protected void initView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_book_detail);
        initVersible();
        initToolbar();
        initPicasso();
        view_pager = (ViewPager) findViewById(R.id.book_viewpager);
        setUpPager(view_pager);
        initTabLayout();


    }

    @Override
    protected void initVersible() {
        books = (Book) getIntent().getSerializableExtra("book1");
    }

    private void initPicasso() {

       ImageView book_image = (ImageView) findViewById(R.id.book_image);
        Picasso.with(this)
                .load(books.getImages().getLarge())
                .into(book_image);
    }

    private void initToolbar() {
        toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.book_collapsing_toolbar);
        toolbar_layout.setTitle(books.getTitle());
    }


    private void initTabLayout() {

      TabLayout tab_layout = (TabLayout) findViewById(R.id.book_sliding_tabs);
        if (tab_layout != null) {
            tab_layout.addTab(tab_layout.newTab());
            tab_layout.addTab(tab_layout.newTab());
            tab_layout.addTab(tab_layout.newTab());
            tab_layout.setupWithViewPager(view_pager);
        }
    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    private void setUpPager(ViewPager view_pager) {

        booksAdapter adapter = new booksAdapter(getSupportFragmentManager());
        adapter.addFragment(bookDetailFragment.getInstance(books.getSummary()),"内容简介");
        adapter.addFragment(bookDetailFragment.getInstance(books.getAuthor_intro()),"作者简介");
        adapter.addFragment(bookDetailFragment.getInstance(books.getCatalog()),"目录");
        view_pager.setAdapter(adapter);


    }

    class  booksAdapter extends BaseHomeAdapter {


        public booksAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void addFragment(Fragment fragment, String title) {
            mfragments.add(fragment);
            mtitles.add(title);
        }
    }
}
