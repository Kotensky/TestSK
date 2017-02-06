package com.kotensky.testsk.activity.search.model;


import com.kotensky.testsk.rest.data.search.RepoSearch;
import com.kotensky.testsk.rest.data.user.RepoUser;

import java.util.List;

import rx.Observable;

/**
 * Created by Stas on 06.02.2017.
 */

public interface IModelSearch {

    Observable<RepoSearch> getSearchRepoList (String qualifiers);

}
