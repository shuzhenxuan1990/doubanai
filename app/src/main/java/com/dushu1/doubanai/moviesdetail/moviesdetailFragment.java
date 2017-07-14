package com.dushu1.doubanai.moviesdetail;

import android.content.Intent;
import android.os.Build;
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
 * Created by Administrator on 2017/6/20.
 */

public class moviesdetailFragment extends Fragment implements View.OnClickListener{


    private String mUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_detail,container,false);
        TextView tv_info = (TextView) view.findViewById(R.id.tvInfo);
        tv_info.setText(getArguments().getString(AppConstant.INTENT_EXTRA_FRAGMENT_INFO));

        if (AppConstant.TYPE_MOVIE_WEBSITE == getArguments().getInt(AppConstant.INTENT_EXTRA_FRAGMENT_TYPE)) {
             tv_info.setOnClickListener(this);
             mUrl = tv_info.getText().toString();
        }
        
        return view;
    }

    public static Fragment getInstance(String info,int type){

        moviesdetailFragment movie_fragment = new moviesdetailFragment();
        Bundle bs = new Bundle();
        bs.putString("info",info);
        bs.putInt("type",type);
        movie_fragment.setArguments(bs);
        return movie_fragment;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvInfo:
                Intent intent = new Intent(getContext(),WebMovieDetailAcitivity.class);
                intent.putExtra("url",mUrl);
                startActivity(intent);
            break;

            default:

            break;

        }
    }
}
