package com.kotensky.testsk.activity.search.model;

import com.kotensky.testsk.rest.ApiModule;
import com.kotensky.testsk.rest.IRest;
import com.kotensky.testsk.rest.data.search.RepoSearch;
import com.kotensky.testsk.rest.data.user.RepoUser;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Stas on 06.02.2017.
 */

public class ModelSearchImpl implements IModelSearch {

    IRest iRest = ApiModule.getApiInterface();

    @Override
    public Observable<RepoSearch> getSearchRepoList(String qualifiers) {
        return iRest.getSearchRepositories(qualifiers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
