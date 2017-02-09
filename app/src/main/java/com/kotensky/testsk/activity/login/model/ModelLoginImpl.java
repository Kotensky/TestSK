package com.kotensky.testsk.activity.login.model;

import com.kotensky.testsk.activity.login.model.data.User;
import com.kotensky.testsk.rest.ApiModule;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Stas on 09.02.2017.
 */

public class ModelLoginImpl implements IModelLogin {

    @Override
    public Observable<User> getUser(String basic) {
        return ApiModule.getApiInterface(basic).getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
