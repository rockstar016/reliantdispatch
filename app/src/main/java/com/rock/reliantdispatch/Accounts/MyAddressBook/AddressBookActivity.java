package com.rock.reliantdispatch.Accounts.MyAddressBook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Interface.AddressRecyclerInterface;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressBookActivity extends BaseActivity implements AddressRecyclerInterface{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    AddressBookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_address_book);

        swipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.primary_dark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                swipeRefreshLayout.setRefreshing(false);
            }, 1500);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressBookAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId)
        {
            case android.R.id.home:
                BackToParent();
                break;
            case R.id.add:
                GoToAddParent();
                break;
            case R.id.menu_search:
                break;
        }
        return true;
    }

    @UiThread
    void BackToParent()
    {
        finish();
    }

    @UiThread
    void GoToAddParent()
    {
        Intent i = new Intent(this, NewAddressActivity.class);
        startActivity(i);
    }

    @Override
    public void OnClickRemoveItem(int position) {
        Toast.makeText(this, "Remove" + (position + 1), Toast.LENGTH_SHORT).show();
     }

    @Override
    public void OnRecyclerItemClick(int position) {
        Intent i = new Intent(this, NewAddressActivity.class);
        startActivity(i);
    }
}
