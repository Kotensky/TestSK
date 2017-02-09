package com.kotensky.testsk.activity.search.view;

import com.kotensky.testsk.activity.search.model.data.ItemSearch;
import com.kotensky.testsk.activity.search.model.data.RepoSearch;

import java.util.List;

/**
 * Created by Stas on 06.02.2017.
 */

public interface IViewSearch {

    void showData(List<ItemSearch> listItemSearch);

    void showError(String error);

    void showEmptyList();

    void endRefresh();

    String getQualifiers();

    int getPage();
}
