package com.dushu1.doubanai.api;

import com.dushu1.doubanai.bean.HotMovieInfo;
import com.dushu1.doubanai.bean.bookInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/6/14.
 */

public interface IdoubanService {

    String BASE_URL = "https://api.douban.com/v2/";

    @GET("movie/in_theaters")
    Call<HotMovieInfo> seacheHotMovie(@Query("start") int start);

    @GET("book/search")
    Call<bookInfo> searchBooks(@Query("q") String name,@Query("start") int index);

}
