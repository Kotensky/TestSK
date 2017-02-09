package com.kotensky.testsk.rest;

import com.kotensky.testsk.activity.login.model.data.User;
import com.kotensky.testsk.activity.search.model.data.RepoSearch;
import com.kotensky.testsk.activity.user.model.data.RepoUser;


import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Stas on 06.02.2017.
 */

public interface IRest {


    @GET("/search/repositories")
    Observable<RepoSearch> getSearchRepositories (@Query("q") String qualifiers, @Query("page") int page);

    @GET("/user/repos")
    Observable<List<RepoUser>> getUserRepositories();

    @GET("/user")
    Observable<User> getUser();
}
