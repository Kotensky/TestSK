package com.kotensky.testsk.activity.user.model;

import com.kotensky.testsk.rest.data.user.RepoUser;

import java.util.List;

import rx.Observable;

/**
 * Created by Stas on 06.02.2017.
 */

public interface IModelUser {

    Observable<List<RepoUser>> getUserRepoList(String qualifiers);
}
