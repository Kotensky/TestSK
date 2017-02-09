package com.kotensky.testsk.activity.user.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kotensky.testsk.R;
import com.kotensky.testsk.activity.login.view.LoginActivity;
import com.kotensky.testsk.activity.search.view.SearchActivity;
import com.kotensky.testsk.activity.user.presenter.IPresenterUser;
import com.kotensky.testsk.activity.user.presenter.PresenterUser;
import com.kotensky.testsk.activity.user.view.adapter.RecyclerViewAdapterUser;
import com.kotensky.testsk.activity.user.model.data.OwnerUser;
import com.kotensky.testsk.activity.user.model.data.RepoUser;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.kotensky.testsk.R.id.changeUser;


public class UserActivity extends AppCompatActivity implements IViewUser {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recyclerViewUser)
    RecyclerView recyclerView;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.swipe_refresh_layout_user)
    SwipeRefreshLayout swipeRefreshLayout;

    private IPresenterUser presenterUser;
    private RecyclerViewAdapterUser adapter;

    private SharedPreferences sharedPref;

    private String basic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        presenterUser = new PresenterUser(this);
        adapter = new RecyclerViewAdapterUser();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenterUser.onLogin();
            }
        });
        sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        if (sharedPref.contains(getString(R.string.preference_basic_key))) {
            basic = sharedPref.getString(getString(R.string.preference_basic_key), "Basic");
            presenterUser.onLogin();
        } else {
            Intent intent = new Intent(UserActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void changeUser() {
        sharedPref.edit().remove(getString(R.string.preference_basic_key)).apply();
        Intent intent = new Intent(UserActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == changeUser) {
            changeUser();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showData(List<RepoUser> repoUserList) {
        adapter.setItemUserList(repoUserList);
        OwnerUser ownerUser = repoUserList.get(0).getOwner();
        toolbar.setTitle(ownerUser.getLogin());
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void showError(String error) {
        if (error.contains("401") || error.contains("404"))
            changeUser();
        else
            makeToast(error);
    }

    @Override
    public void showEmptyList() {
        makeToast("List is empty");
    }

    @Override
    public String getBasic() {
        return basic;
    }

    private void makeToast(String text) {
        Snackbar.make(toolbar, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenterUser != null) {
            presenterUser.onStop();
        }
    }
}
