package com.dushu1.doubanai.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/14.
 */
 public class doubanManager {

    private static IdoubanService idoubanService;

    public static IdoubanService getService(){
        if (idoubanService == null) {
            Retrofit retrofit = getRetrofit();
            idoubanService =  retrofit.create(IdoubanService.class);
        }
        return idoubanService;
    }

    public static Retrofit getRetrofit() {
        /**
         * 添加http的过滤器，拦截打印日志确认是否成功获取数据
         */
         OkHttpClient client;
         HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
         logging.setLevel(HttpLoggingInterceptor.Level.BODY);
         client = new OkHttpClient.Builder().addInterceptor(logging).build();

        return  new Retrofit.Builder()
                .baseUrl(IdoubanService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();
    }

}
