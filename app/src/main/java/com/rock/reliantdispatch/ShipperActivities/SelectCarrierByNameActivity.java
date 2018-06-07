package com.rock.reliantdispatch.ShipperActivities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.Adapters.SearchCarrierAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCarrierByNameActivity extends BaseActivity implements BaseRecyclerInterface{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.paymentToCarrier) TextInputLayout paymentToCarrier;
    @BindView(R.id.btSearch) Button btSearch;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    SearchCarrierAdapter adapter;
    ArrayList<String> provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_select_carrier);
        ButterKnife.bind(this);
        InitToolbar(toolbar, "");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        provider = new ArrayList<String>();
        adapter = new SearchCarrierAdapter(this, provider, this);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.btSearch)
    public void onSearchButtonClicked() {
        provider.add(new String("123"));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnRecyclerItemClick(int position) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return true;
    }
}
