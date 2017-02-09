package com.kotensky.testsk.activity.search.model;


import com.kotensky.testsk.activity.search.model.data.RepoSearch;
import com.kotensky.testsk.activity.user.model.data.RepoUser;

import rx.Observable;

/**
 * Created by Stas on 06.02.2017.
 */

public interface IModelSearch {

    Observable<RepoSearch> getSearchRepoList (String qualifiers, int page);

}
