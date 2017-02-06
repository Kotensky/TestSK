package com.kotensky.testsk.activity.user.presenter;

import com.kotensky.testsk.activity.search.presenter.IPresenterSearch;
import com.kotensky.testsk.activity.user.model.IModelUser;
import com.kotensky.testsk.activity.user.model.ModelUserImpl;
import com.kotensky.testsk.activity.user.view.IViewUser;
import com.kotensky.testsk.rest.data.user.RepoUser;

import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Stas on 06.02.2017.
 */

public class PresenterUser implements IPresenterUser {

    private IModelUser modelUser = new ModelUserImpl();
    private IViewUser viewUser;

    private Subscription subscription = Subscriptions.empty();

    public PresenterUser (IViewUser view){
        viewUser = view;
    }

    @Override
    public void onLogin() {
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        subscription = modelUser.getUserRepoList(viewUser.getName())
                .subscribe(new Observer<List<RepoUser>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        viewUser.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<RepoUser> repoUserList) {
                        if (repoUserList!=null && !repoUserList.isEmpty()){
                            viewUser.showData(repoUserList);
                        }else {
                            viewUser.showEmptyList();
                        }
                    }
                });
    }

    @Override
    public void onStop() {
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
