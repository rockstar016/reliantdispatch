package com.rock.reliantdispatch.ShipperActivities.CarrierTrailer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.Adapters.DetailVehicleRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarrierTrailerDetailActivity extends BaseActivity implements BaseRecyclerInterface {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerVehicle)
    RecyclerView recyclerView;
    DetailVehicleRecyclerAdapter adapter;
    @BindView(R.id.scrollContainer)
    ScrollView scrollContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_detail_carriertrailer_item);
//        adapter = new DetailVehicleRecyclerAdapter(this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GotoTop();
    }

    void GotoTop()
    {
        new Handler(Looper.getMainLooper()).postDelayed(()->{scrollContainer.scrollTo(0,0);}, 100);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void OnRecyclerItemClick(int position) {

    }

}
