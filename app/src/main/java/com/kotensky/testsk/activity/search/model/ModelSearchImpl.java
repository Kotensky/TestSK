package com.kotensky.testsk.activity.search.model;

import com.kotensky.testsk.rest.ApiModule;
import com.kotensky.testsk.rest.IRest;
import com.kotensky.testsk.activity.search.model.data.RepoSearch;
import com.kotensky.testsk.activity.user.model.data.RepoUser;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Stas on 06.02.2017.
 */

public class ModelSearchImpl implements IModelSearch {

    IRest iRest = ApiModule.getApiInterface();

    @Override
    public Observable<RepoSearch> getSearchRepoList(String qualifiers, int page) {
        return iRest.getSearchRepositories(qualifiers, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
