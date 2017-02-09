package com.kotensky.testsk.activity.login.model;

import com.kotensky.testsk.activity.login.model.data.User;

import rx.Observable;

/**
 * Created by Stas on 09.02.2017.
 */

public interface IModelLogin {

    Observable<User> getUser(String basic);
}
