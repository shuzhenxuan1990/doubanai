package com.dushu1.doubanai.movies;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.dushu1.doubanai.R;
import com.dushu1.doubanai.activity.MainActivity;
import com.dushu1.doubanai.api.IdoubanService;
import com.dushu1.doubanai.api.doubanManager;
import com.dushu1.doubanai.base.BaseFragment;
import com.dushu1.doubanai.base.BaseRecyclerAdapter;
import com.dushu1.doubanai.base.BaseViewHolder;
import com.dushu1.doubanai.bean.HotMovieInfo;
import com.dushu1.doubanai.bean.Movies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class moviesFragment extends BaseFragment<Movies> implements MovieContract.MovieView {

   // public static final String TAG = "DouBan";
    private MovieContract.Pretenser presenter;
    //private MovieAdapter adapter;
    private SwipeToLoadLayout swipeToLoadLayout;
    private LinearLayout noMovie_view;
    public static final String TAG = moviesFragment.class.getSimpleName();



    @Override
    protected void initEndlessScrollListener() {

    }

    @Override
    protected void initSwipeRefreshLayout() {

        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadMoivesPre(true);
            }
        });
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMoreMoives(baseRecyclerAdapter.getItemCount());
            }
        });

    }

    @Override
    protected void initRecycleView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = (RecyclerView) mView.findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout) mView.findViewById(R.id.swipeToLoadLayout);
        noMovie_view = (LinearLayout) mView.findViewById(R.id.ll_no_movies);

        if (recyclerView != null){
            recyclerView.setHasFixedSize(true);
            mlayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
            //adapter = new MovieAdapter(mAdapterData,getContext());
            recyclerView.setLayoutManager(mlayoutManager);
            recyclerView.setAdapter(baseRecyclerAdapter);
        }
    }

    @Override
    protected void initRecyleViewAdapter() {
       baseRecyclerAdapter = new BaseRecyclerAdapter(
               new BaseViewHolder.BuilderItemViewHolder() {
                   @Override
                   public Object buildView(View itemView) {
                       return new MovieAdapter.MovieHolder(itemView);
                   }
               },
               new ArrayList<>(0),
               R.layout.movies_item);
    }

    @Override
    protected void initData() {
        mAdapterData = new ArrayList<>();
    }

    @Override
    protected void startPresenter() {
        if(presenter != null) {
            presenter.start();
        }
    }


    public moviesFragment() {
        // Required empty public constructor
    }




    @Override
    public void setPresenter(MovieContract.Pretenser presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgressBar(final Boolean isActive) {

        if (getView() == null) return;

        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(isActive);
            }
        });

    }

    @Override
    public void showNoMoives() {
        recyclerView.setVisibility(View.GONE);
        noMovie_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMoivewList(List<Movies> movies) {


        if(mAdapterData.size() != 0 && movies.get(0).getId().equals(mAdapterData.get(0).getId())) {
            Log.e(MainActivity.TAG,"啦啦加载不出来啦");
            Log.e(MainActivity.TAG,"moviesID: "+ movies.get(0).getId() +"....." + mAdapterData.get(0).getId());
            return;
        }
         mAdapterData.addAll(movies);
         baseRecyclerAdapter.setData(mAdapterData);
         recyclerView.setVisibility(View.VISIBLE);
         noMovie_view.setVisibility(View.GONE);
    }

    @Override
    public void showLoadMoreMoives(List<Movies> movies) {

        Log.e(MainActivity.TAG,  TAG + " showLoadedMoreMovies 111 : \n" +
                "mAdapterData.size() =  " + mAdapterData.size()
                + ", LoadMoreList.size = " + movies.size() + ", adapter's movies.size = " + baseRecyclerAdapter.getData().size());

        mAdapterData.addAll(movies);
        baseRecyclerAdapter.setData(mAdapterData);
        swipeToLoadLayout.setLoadingMore(false);

        Log.e(MainActivity.TAG,  TAG + " showLoadedMoreMovies 222 : \n" +
                "mAdapterData.size() =  " + mAdapterData.size()
                + ", LoadMoreList.size = " + movies.size() + ", adapter's movies.size = " + baseRecyclerAdapter.getData().size());

    }

    @Override
    public void showNoLoadedMoreMovies() {
        Toast.makeText(getContext(),"no more",Toast.LENGTH_LONG).show();
        swipeToLoadLayout.setLoadingMore(false);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapterData.clear();
        presenter.cancelRetrofitRequest();
        Log.e(MainActivity.TAG, TAG + "=> onDestroy()!!!");
    }
}
