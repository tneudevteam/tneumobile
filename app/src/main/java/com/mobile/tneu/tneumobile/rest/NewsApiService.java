package com.mobile.tneu.tneumobile.rest;

import com.mobile.tneu.tneumobile.model.News;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by stepanv on 28.09.16.
 */

public interface NewsApiService {
  String SERVICE_ENDPOINT = "http://139.59.211.163:9191/";

  @GET("/snippets/{page}")
  Observable<List<News>> getNewsByPage(@Path("page") String pageNumber);

  @GET("/article")
  Observable<News> getNewsByUrl(@Query("link") String newsUrl);
}
