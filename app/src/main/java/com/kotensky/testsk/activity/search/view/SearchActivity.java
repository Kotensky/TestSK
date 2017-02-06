package com.kotensky.testsk.activity.search.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kotensky.testsk.R;
import com.kotensky.testsk.activity.search.presenter.IPresenterSearch;
import com.kotensky.testsk.activity.search.presenter.PresenterSearch;
import com.kotensky.testsk.rest.data.search.ItemSearch;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.kotensky.testsk.R.id.fab;

public class SearchActivity extends AppCompatActivity implements IViewSearch {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.editText)
    EditText editText;

    @Bind(R.id.button)
    Button searchButton;

    private RecyclerViewAdapterSearch adapterSearch;

    private IPresenterSearch presenterSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        presenterSearch = new PresenterSearch(this);
        adapterSearch = new RecyclerViewAdapterSearch();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapterSearch);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterSearch.onSearchButtonClick();
            }
        });
    }


    @Override
    public void showData(List<ItemSearch> listItemSearch) {
        adapterSearch.setItemSearchList(listItemSearch);
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
    public String getQualifiers() {
        return editText.getText().toString();
    }

    private void makeToast (String text){
        Snackbar.make(toolbar, text, Snackbar.LENGTH_LONG).show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (presenterSearch != null) {
            presenterSearch.onStop();
        }
    }
}
