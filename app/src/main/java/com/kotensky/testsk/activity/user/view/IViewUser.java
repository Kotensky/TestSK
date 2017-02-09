package com.kotensky.testsk.activity.user.view;

import com.kotensky.testsk.activity.user.model.data.RepoUser;

import java.util.List;

/**
 * Created by Stas on 06.02.2017.
 */

public interface IViewUser {

    void showData(List<RepoUser> repoUserList);

    void showError(String error);

    void showEmptyList();

    String getBasic();

}
