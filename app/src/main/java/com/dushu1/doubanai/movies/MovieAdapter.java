package com.dushu1.doubanai.movies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dushu1.doubanai.R;
import com.dushu1.doubanai.base.BaseRecyclerAdapter;
import com.dushu1.doubanai.base.BaseViewHolder;
import com.dushu1.doubanai.bean.Movies;
import com.dushu1.doubanai.moviesdetail.moviesdetailAcitvity;
import com.dushu1.doubanai.utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class MovieAdapter extends BaseRecyclerAdapter {



    public MovieAdapter(BaseViewHolder.BuilderItemViewHolder builderItemViewHolder, List dataList, int layoutRes) {
        super(builderItemViewHolder, dataList, layoutRes);
    }




    /*public List<Movies> getData(){
        return mdata;
    }


    public MovieAdapter(List<Movies> datas, Context context) {
        this.mdata = datas;
        this.context = context;
    }*/

   /*@Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies_item,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        if (holder == null) return;
        holder.updateMsg(mdata.get(position));
    }

    public void setData(List<Movies> data) {
        this.mdata = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }*/

    public static class MovieHolder extends BaseViewHolder<Movies> implements View.OnClickListener{

        TextView mTitle;
        ImageView mIcon;
        RatingBar mRating;
        TextView mScore;


        public MovieHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.movie_title);
            mIcon = (ImageView) itemView.findViewById(R.id.movie_cover);
            mRating = (RatingBar) itemView.findViewById(R.id.rating_star);
            mScore = (TextView) itemView.findViewById(R.id.movie_average);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Context context = itemView.getContext();
            if (context == null) return;
            Intent intent = new Intent(context,moviesdetailAcitvity.class);
            intent.putExtra(AppConstant.INTENT_EXTRA_MOVIE,itemContent);
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                //定义转换动画
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,mIcon,"cover").toBundle();
                ActivityCompat.startActivity(activity,intent,bundle);
            }
        }

       /* public void updateMsg(Movies movies) {
            if (movies == null)
                return;
        }
*/
        @Override
        protected void bindView(Movies movies) {
            Context context = itemView.getContext();
            if(movies == null || context == null) return;
            //this.itemContent = movies;
            Picasso.with(context)
                    .load(movies.getImages().getLarge())
                    .placeholder(context.getResources().getDrawable(R.mipmap.ic_launcher))
                    .into(mIcon);

            mTitle.setText(movies.getTitle());
            final double average = movies.getRating().getAverage();
            if (average == 0.0) {
                mRating.setVisibility(View.GONE);
                mScore.setText("暂无评分");
            }else{
                mRating.setVisibility(View.VISIBLE);
                mScore.setText(String.valueOf(average));
                mRating.setStepSize(0.5f);
                mRating.setRating((float)(movies.getRating().getAverage()/2));
            }
        }
    }

}
