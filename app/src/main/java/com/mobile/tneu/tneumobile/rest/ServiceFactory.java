package com.mobile.tneu.tneumobile.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by stepanv on 28.09.16.
 */

public class ServiceFactory {

  public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    httpClient.addInterceptor(logging);

    final Retrofit restAdapter = new Retrofit.Builder()
        .baseUrl(NewsApiService.SERVICE_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(httpClient.build())
        .build();

    return restAdapter.create(clazz);
  }
}
