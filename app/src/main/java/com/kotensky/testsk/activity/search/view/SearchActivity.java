package com.kotensky.testsk.activity.search.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kotensky.testsk.R;
import com.kotensky.testsk.activity.search.presenter.IPresenterSearch;
import com.kotensky.testsk.activity.search.presenter.PresenterSearch;
import com.kotensky.testsk.activity.search.view.adapter.RecyclerViewAdapterSearch;
import com.kotensky.testsk.activity.search.view.listener.EndlessRecyclerOnScrollListener;
import com.kotensky.testsk.activity.search.model.data.ItemSearch;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SearchActivity extends AppCompatActivity implements IViewSearch, SearchView.OnQueryTextListener{


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.swipe_refresh_layout_search)
    SwipeRefreshLayout swipeRefreshLayout;

    private String qualifiers;
    private int page = 1;
    SearchView searchView;

    private RecyclerViewAdapterSearch adapterSearch;
    private IPresenterSearch presenterSearch;
    private EndlessRecyclerOnScrollListener scrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        presenterSearch = new PresenterSearch(this);
        adapterSearch = new RecyclerViewAdapterSearch();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapterSearch);

        scrollListener = new EndlessRecyclerOnScrollListener(llm) {
            @Override
            public void onLoadMore(int current_page) {
                page = current_page;
                presenterSearch.loadData();
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenterSearch.loadData();
            }
        });
    }


    @Override
    public void showData(List<ItemSearch> listItemSearch) {
        if (!swipeRefreshLayout.isEnabled())
            swipeRefreshLayout.setEnabled(true);
        if (page > 1) {
            adapterSearch.addItemSearchList(listItemSearch);
        } else {
            adapterSearch.setItemSearchList(listItemSearch);
            scrollListener.setPage(1);
            scrollListener.setLoading(false);
            toolbar.setTitle(qualifiers);
        }
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showEmptyList() {
        makeToast("No results for the query '" + qualifiers + "'");
    }

    @Override
    public void endRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public String getQualifiers() {
        return qualifiers;
    }

    @Override
    public int getPage() {
        return page;
    }

    private void makeToast(String text) {
        Snackbar.make(toolbar, text, Snackbar.LENGTH_LONG).show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (presenterSearch != null) {
            presenterSearch.onStop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem itemSearch = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        qualifiers = query;
        page = 1;
        presenterSearch.loadData();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
