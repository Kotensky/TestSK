package com.kotensky.testsk.activity.search.presenter;

import com.kotensky.testsk.activity.search.model.IModelSearch;
import com.kotensky.testsk.activity.search.model.ModelSearchImpl;
import com.kotensky.testsk.activity.search.view.IViewSearch;
import com.kotensky.testsk.rest.data.search.RepoSearch;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Stas on 06.02.2017.
 */

public class PresenterSearch implements IPresenterSearch {

    private IModelSearch modelSearch = new ModelSearchImpl();

    private IViewSearch viewSearch;
    private Subscription subscription = Subscriptions.empty();

    public PresenterSearch (IViewSearch iViewSearch){
        viewSearch = iViewSearch;
    }

    @Override
    public void onSearchButtonClick() {
        if (!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        subscription = modelSearch.getSearchRepoList(viewSearch.getQualifiers())
                .subscribe(new Observer<RepoSearch>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        viewSearch.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(RepoSearch repoSearch) {
                        if (repoSearch.getItems() != null && !repoSearch.getItems().isEmpty()){
                            viewSearch.showData(repoSearch.getItems());
                        }else {
                            viewSearch.showEmptyList();
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
