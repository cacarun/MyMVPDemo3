package com.android.mvp2.api.service;

import com.android.mvp2.data.model.HttpResult;
import com.android.mvp2.data.model.Repo;
import com.android.mvp2.data.model.Subject;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface HttpService {
    @GET("/users/{user}/repos")
    Observable<ArrayList<Repo>> getRepoData(@Path("user") String user);

    @GET("top250")
    Observable<HttpResult<Subject>> getTopMovie(@Query("start") int start, @Query("count") int count);


    // e.g.
    /**
     http://square.github.io/retrofit/
     */
}