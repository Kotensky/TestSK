package com.kotensky.testsk.activity.user.model;

import com.kotensky.testsk.rest.ApiModule;
import com.kotensky.testsk.rest.IRest;
import com.kotensky.testsk.rest.data.user.RepoUser;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Stas on 06.02.2017.
 */

public class ModelUserImpl implements IModelUser {

    IRest iRest = ApiModule.getApiInterface();

    @Override
    public Observable<List<RepoUser>> getUserRepoList(String name) {
        return iRest.getUserRepositories(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
