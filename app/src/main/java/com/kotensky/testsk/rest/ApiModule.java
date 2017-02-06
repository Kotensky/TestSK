package com.kotensky.testsk.rest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Stas on 06.02.2017.
 */

public class ApiModule {

    private static final String url = "https://api.github.com";

    public static IRest getApiInterface () {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        IRest iRest = builder.build().create(IRest.class);
        return iRest;
    }
}
