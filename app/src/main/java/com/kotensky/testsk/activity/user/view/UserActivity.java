package com.kotensky.testsk.activity.user.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kotensky.testsk.R;
import com.kotensky.testsk.activity.search.view.SearchActivity;
import com.kotensky.testsk.activity.user.presenter.IPresenterUser;
import com.kotensky.testsk.activity.user.presenter.PresenterUser;
import com.kotensky.testsk.rest.data.user.RepoUser;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.kotensky.testsk.R.id.fab;

public class UserActivity extends AppCompatActivity implements IViewUser {


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recyclerViewUser)
    RecyclerView recyclerView;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    private IPresenterUser presenterUser;
    private RecyclerViewAdapterUser adapter;

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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        presenterUser.onLogin();
    }

    @Override
    public void showData(List<RepoUser> repoUserList) {
        adapter.setItemUserList(repoUserList);
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showEmptyList() {
        makeToast("List is empty");
    }

    @Override
    public String getName() {
        return "andrewchopko";
    }

    private void makeToast (String text){
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
