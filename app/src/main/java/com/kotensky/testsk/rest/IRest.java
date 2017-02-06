package com.kotensky.testsk.rest;

import com.kotensky.testsk.rest.data.search.RepoSearch;
import com.kotensky.testsk.rest.data.user.RepoUser;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Stas on 06.02.2017.
 */

public interface IRest {


    @GET("/search/repositories")
    Observable<RepoSearch> getSearchRepositories (@Query("q") String qualifiers);

    @GET("/users/{user}/repos")
    Observable<List<RepoUser>> getUserRepositories(@Path("user") String user);

}
