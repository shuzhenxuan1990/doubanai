package com.dushu1.doubanai.books;

import com.dushu1.doubanai.api.IdoubanService;
import com.dushu1.doubanai.bean.Book;
import com.dushu1.doubanai.bean.bookInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/22.
 */

public class booksPresentor implements BooksContract.BooksPresentor {

    private IdoubanService idoubanService;
    private BooksContract.BooksView mfragment;
    private boolean fristload = true;
    private int mTotal;

    public booksPresentor(IdoubanService idoubanService, BooksContract.BooksView mfragment) {
        this.idoubanService = idoubanService;
        this.mfragment = mfragment;
        mfragment.setPresenter(this);
    }

    @Override
    public void start() {
        loadReflashBooks(false);
    }

    @Override
    public void loadReflashBooks(boolean forceload) {
        loadingBooks(forceload|fristload,true);
        fristload = false;
    }

    @Override
    public void loadMoreBooks(int startIndex) {



        if (startIndex >= mTotal) {
            mfragment.showNoMoreBooks();
            return;
        }

        idoubanService.searchBooks("黑客与画家",startIndex).enqueue(new Callback<bookInfo>() {
            @Override
            public void onResponse(Call<bookInfo> call, Response<bookInfo> response) {



                List<Book> booklist = response.body().getBooks();

                processLoadMoreBook(response.body().getBooks());


            }

            @Override
            public void onFailure(Call<bookInfo> call, Throwable t) {

                mfragment.showNoMoreBooks();
            }
        });

    }

    private void loadingBooks(boolean forceload,final boolean showIndicate) {



        if (showIndicate) {
            mfragment.showReflashIndicator(true);
        }


        if (forceload) {
            idoubanService.searchBooks("黑客与画家",0).enqueue(new Callback<bookInfo>() {
                @Override
                public void onResponse(Call<bookInfo> call, Response<bookInfo> response) {

                      List<Book> booklist = response.body().getBooks();
                      mTotal = response.body().getTotal();

                      if (showIndicate){

                          mfragment.showReflashIndicator(false);

                      }

                      processLoadBook(response.body().getBooks());
                }

                @Override
                public void onFailure(Call<bookInfo> call, Throwable t) {

                    if (showIndicate) {
                        mfragment.showReflashIndicator(false);
                    }

                     mfragment.showNoBooks();

                }
            });


        }


    }

    private void processLoadBook(List<Book> books) {

        if (books.isEmpty()) mfragment.showNoBooks();

        mfragment.showBooks(books);
    }

    private void processLoadMoreBook(List<Book> books) {

        if(books.isEmpty()) mfragment.showNoMoreBooks();

        mfragment.showMoreBooks(books);

    }
}
