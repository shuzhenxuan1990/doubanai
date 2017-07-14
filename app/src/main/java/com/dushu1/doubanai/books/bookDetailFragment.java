package com.dushu1.doubanai.books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dushu1.doubanai.R;
import com.dushu1.doubanai.utils.AppConstant;

/**
 * Created by Administrator on 2017/6/23.
 */

public class bookDetailFragment extends Fragment {
    @Nullable

    public static Fragment getInstance(String info){

        bookDetailFragment book_detail = new bookDetailFragment();

        Bundle bundle = new Bundle();

        bundle.putString(AppConstant.INTENT_EXTRA_FRAGMENT_INFO,info);

        book_detail.setArguments(bundle);

        return book_detail;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_detial,container,false);

        TextView tx_book_detail = (TextView) view.findViewById(R.id.tx_bookInfo);

        tx_book_detail.setText(getArguments().getString(AppConstant.INTENT_EXTRA_FRAGMENT_INFO));

        return view;
    }


}
