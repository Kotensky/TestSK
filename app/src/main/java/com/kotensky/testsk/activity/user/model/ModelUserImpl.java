package com.kotensky.testsk.activity.user.model;

import com.kotensky.testsk.rest.ApiModule;
import com.kotensky.testsk.activity.user.model.data.RepoUser;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Stas on 06.02.2017.
 */

public class ModelUserImpl implements IModelUser {


    @Override
    public Observable<List<RepoUser>> getUserRepoList(String basic) {
        return ApiModule.getApiInterface(basic).getUserRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
