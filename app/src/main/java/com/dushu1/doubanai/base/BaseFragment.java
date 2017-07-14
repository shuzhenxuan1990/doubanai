package com.dushu1.doubanai.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dushu1.doubanai.bean.Book;
import com.dushu1.doubanai.books.BooksContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public abstract class BaseFragment<T> extends Fragment {

    public BaseFragment() {

    }

    protected boolean mIsLoading = false;
    protected View mView;
    protected RecyclerView recyclerView;
    protected List<T> mAdapterData;
    protected LinearLayout ll_error;
    protected BaseRecyclerAdapter baseRecyclerAdapter;
    protected LinearLayoutManager mlayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initRecycleView(inflater, container, savedInstanceState);

        initSwipeRefreshLayout();

        initEndlessScrollListener();

        return mView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initRecyleViewAdapter();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startPresenter();
    }

    protected abstract void initEndlessScrollListener();

    protected abstract void initSwipeRefreshLayout();

    protected abstract void initRecycleView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void initRecyleViewAdapter();

    protected abstract void initData();

    protected abstract void startPresenter();
}
