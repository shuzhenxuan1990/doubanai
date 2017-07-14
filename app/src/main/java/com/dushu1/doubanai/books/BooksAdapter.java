package com.dushu1.doubanai.books;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dushu1.doubanai.R;
import com.dushu1.doubanai.base.BaseViewHolder;
import com.dushu1.doubanai.bean.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public  class BooksAdapter  {

/*    private List<Book> booklist ;
    private Context context;*/

    public BooksAdapter() {
        /*this.context = context;
        this.booklist = booklist;*/
    }

   /* @Override
    public BooksAdapter.BooksHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyle_book_view,parent,false);

        return new BooksHolder(view);
    }

    @Override
    public void onBindViewHolder(BooksHolder holder, int position) {

        holder.updateBooksInfo(booklist.get(position));

    }



    @Override
    public int getItemCount() {
        return booklist.size();
    }

    public void setData(List<Book> list) {
        this.booklist = list;
        notifyDataSetChanged();
    }*/


}

