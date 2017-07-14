package com.dushu1.doubanai.books;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dushu1.doubanai.R;
import com.dushu1.doubanai.base.BaseFragment;
import com.dushu1.doubanai.base.BaseRecyclerAdapter;
import com.dushu1.doubanai.base.BaseViewHolder;
import com.dushu1.doubanai.bean.Book;
import com.dushu1.doubanai.utils.OnEndlessRecyclerViewScrollListener;
import com.dushu1.doubanai.utils.ScrollChildSwipeRefreshLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class booksFragment extends BaseFragment<Book> implements BooksContract.BooksView{

    private BooksContract.BooksPresentor presentor;
    private LinearLayout ll_error;
    private BooksAdapter adapter;
    private ScrollChildSwipeRefreshLayout refreshLayout;


    public booksFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initEndlessScrollListener() {

        OnEndlessRecyclerViewScrollListener scrollListener = new OnEndlessRecyclerViewScrollListener(mlayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                 presentor.loadMoreBooks(totalItemsCount);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    @Override
    protected void initSwipeRefreshLayout() {

        refreshLayout = (ScrollChildSwipeRefreshLayout) mView.findViewById(R.id.refresh_layout);
        //设置样式
        refreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        refreshLayout.setScrollUpChild(recyclerView);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presentor.loadReflashBooks(true);
            }
        });

    }

    @Override
    protected void initRecycleView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_books, container, false);

        recyclerView = (RecyclerView) mView.findViewById(R.id.books_frg_recy);
        ll_error = (LinearLayout) mView.findViewById(R.id.ll_error);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            mlayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(mlayoutManager);
            //adapter = new BooksAdapter(getContext(),booklist);
            recyclerView.setAdapter(baseRecyclerAdapter);
        }

    }

    @Override
    protected void initRecyleViewAdapter() {
       baseRecyclerAdapter =  new BaseRecyclerAdapter(

           new BaseViewHolder.BuilderItemViewHolder() {
           @Override
           public Object buildView(View itemView) {
               return new BooksHolder(itemView);
           }
       },new ArrayList<>(0),R.layout.recyle_book_view);
    }

    @Override
    protected void initData() {
       mAdapterData = new ArrayList<>();
    }

    @Override
    protected void startPresenter() {
        if (presentor != null) {
            presentor.start();
        }
    }




    @Override
    public void setPresenter(BooksContract.BooksPresentor presenter) {
         this.presentor = presenter;
    }


    @Override
    public void showBooks(List<Book> list) {

        //如果获取的list的数据的第一条数据的ID跟已存在的booklist的id相等的话，就不执行刷新数据
        if ( mAdapterData.size()!=0 && list.get(0).getId() == mAdapterData.get(0).getId() ) {
            return;
        }

        mAdapterData.addAll(list);
        baseRecyclerAdapter.setData(mAdapterData);
        ll_error.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMoreBooks(List<Book> list) {
        mAdapterData.addAll(list);
       baseRecyclerAdapter.setData(mAdapterData);
    }

    @Override
    public void showNoMoreBooks() {
        Toast.makeText(getActivity(),"没有更多咯",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNoBooks() {
      ll_error.setVisibility(View.VISIBLE);
      recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showReflashIndicator(final boolean flag) {
        if (getView() == null) {
            return;
        }

    // final SwipeRefreshLayout  swipe_layout = (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        refreshLayout.post(new Runnable() {
         @Override
         public void run() {
            refreshLayout.setRefreshing(flag);
         }
     });

    }


    public static class BooksHolder extends BaseViewHolder<Book> implements View.OnClickListener {
        CardView cardView;
        ImageView bookImage;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookSubTitle;
        TextView bookPubDate;
        TextView bookPages;
        TextView bookPrice;


        public BooksHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            bookImage = (ImageView) itemView.findViewById(R.id.book_cover);
            bookTitle = (TextView) itemView.findViewById(R.id.txt_title);
            bookAuthor = (TextView) itemView.findViewById(R.id.txt_author);
            bookSubTitle = (TextView) itemView.findViewById(R.id.txt_subTitle);
            bookPubDate = (TextView) itemView.findViewById(R.id.txt_pubDate);
            bookPages = (TextView) itemView.findViewById(R.id.txt_pages);
            bookPrice = (TextView) itemView.findViewById(R.id.txt_prices);
            itemView.setOnClickListener(this);
        }

        @Override
        protected void bindView(Book book) {
            Context context = itemView.getContext();
            if (context == null || book == null) return;
            String prefixSubTitle = context.getString(R.string.prefix_subtitle);
            String prefixAuthor = context.getString(R.string.prefix_author);
            String prefixPubDate = context.getString(R.string.prefix_pubdata);
            String prefixPages = context.getString(R.string.prefix_pages);
            String prefixPrice = context.getString(R.string.prefix_price);

            Picasso.with(context)
                    .load(book.getImages().getLarge())
                    .into(bookImage);

            bookTitle.setText(book.getTitle());

            bookAuthor.setText(String.format(prefixAuthor,book.getAuthor()));
            bookSubTitle.setText(String.format(prefixSubTitle,book.getSubtitle()));
            bookPubDate.setText(String.format(prefixPubDate,book.getPubdate()));
            bookPages.setText(String.format(prefixPages,book.getPages()));
            bookPrice.setText(String.format(prefixPrice,book.getPrice()));
        }

        @Override
        public void onClick(View v) {
         /* if (itemView == null || itemContent == null) {
              return;
          }*/
            Context context = itemView.getContext();
            if (context == null) return;
            Intent intent = new Intent(context,bookDetailActivity.class);
            intent.putExtra("book1",itemContent);
            if (context instanceof Activity){

                Activity activity = (Activity) context;
                //定义转换动画
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,bookImage,"cover").toBundle();

                ActivityCompat.startActivity(activity,intent,bundle);
            }

        }

    }


}
