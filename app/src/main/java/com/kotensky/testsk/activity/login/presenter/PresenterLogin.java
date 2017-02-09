package com.kotensky.testsk.activity.login.presenter;

import com.kotensky.testsk.activity.login.model.IModelLogin;
import com.kotensky.testsk.activity.login.model.ModelLoginImpl;
import com.kotensky.testsk.activity.login.model.data.User;
import com.kotensky.testsk.activity.login.view.IViewLogin;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Stas on 09.02.2017.
 */

public class PresenterLogin implements IPresenterLogin {

    IModelLogin modelLogin = new ModelLoginImpl();
    IViewLogin viewLogin;

    private Subscription subscription = Subscriptions.empty();

    public PresenterLogin(IViewLogin viewLogin) {
        this.viewLogin = viewLogin;
    }

    @Override
    public void onLoginClick() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = modelLogin.getUser(viewLogin.getBasic())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        viewLogin.showError();
                    }

                    @Override
                    public void onNext(User user) {
                        if (user != null)
                            viewLogin.authOk();
                        else
                            viewLogin.showError();
                    }
                });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
