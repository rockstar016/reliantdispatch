package com.rock.reliantdispatch.CarrierActivities.TruckSpace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.Main.Carrier.TruckSpace.AddTruckActivity;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTruckProfileActivity extends BaseActivity implements BaseRecyclerInterface{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerTruckSpace)
    RecyclerView recyclerTruckSpace;
    DetailTruckSpaceAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_truck_profile);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_detail_truck_profile);
        adapter = new DetailTruckSpaceAdapter(this, this);
        recyclerTruckSpace.setAdapter(adapter);
        recyclerTruckSpace.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_filter_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_filter:
                showFilter();
                break;
        }
        return true;
    }

    void showFilter()
    {
        ActionBottomDetailTruckProfile f = ActionBottomDetailTruckProfile.newInstance();
        f.show(getSupportFragmentManager(), "bottomSheet");
    }

    public void GoToEditTruckProfile()
    {
        Intent i = new Intent(this, AddTruckActivity.class);
        i.putExtra(BundleParamConfig.BUNDLE_CREATE_EDIT_BOOL, false);
        startActivity(i);
    }

    public void GoToNewDeparture()
    {
        Fragment f = getSupportFragmentManager().findFragmentByTag("bottomSheet");
        if(f != null)
        {
            ((ActionBottomDetailTruckProfile)f).dismiss();
        }
        Intent i = new Intent(this, AddDepartureActivity.class);
        i.putExtra(BundleParamConfig.BUNDLE_CREATE_EDIT_BOOL, true);
        startActivity(i);
    }

    @Override
    public void OnRecyclerItemClick(int position) {
        Intent i = new Intent(this, TruckDepartureDetailActivity.class);
        startActivity(i);
    }
}
