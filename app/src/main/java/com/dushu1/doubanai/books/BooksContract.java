package com.dushu1.doubanai.books;

import com.dushu1.doubanai.bean.Book;
import com.dushu1.doubanai.movies.BasePretenser;
import com.dushu1.doubanai.movies.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public interface BooksContract {

     interface BooksView extends BaseView<BooksPresentor>{

         void showBooks(List<Book> list);
         void showMoreBooks(List<Book> list);
         void showNoMoreBooks();
         void showNoBooks();
         void showReflashIndicator(boolean flag);


    }

     interface BooksPresentor extends BasePretenser{

         void loadReflashBooks(boolean flag);
         void loadMoreBooks(int startIndex);
     }

}
