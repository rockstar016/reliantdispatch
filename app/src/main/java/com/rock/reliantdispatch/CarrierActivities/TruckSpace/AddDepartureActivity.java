package com.rock.reliantdispatch.CarrierActivities.TruckSpace;

import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDepartureActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spDepartureTime)
    Spinner spDepartureTime;
    @BindView(R.id.spStateFrom)
    Spinner spStateFrom;
    @BindView(R.id.spStateDestination)
    Spinner spStateDestination;
    @BindView(R.id.spStateHeadingDirection)
    Spinner spStateHeadingDirection;

    boolean isCreate = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_departure);
        ButterKnife.bind(this);
        InitToolbar(toolbar, "Add Departure");
        SpinnerInitUtils.InitSpinner(this, spDepartureTime, R.array.departureTime);
        SpinnerInitUtils.InitSpinner(this, spStateFrom, R.array.state_names);
        SpinnerInitUtils.InitSpinner(this, spStateDestination, R.array.state_names);
        SpinnerInitUtils.InitSpinner(this, spStateHeadingDirection, R.array.state_names);
        isCreate = getIntent().getBooleanExtra(BundleParamConfig.BUNDLE_CREATE_EDIT_BOOL, true);
        setToolbarTitle();
    }

    @UiThread
    void setToolbarTitle()
    {
        getSupportActionBar().setTitle(isCreate?"Add Departure":"Edit Departure");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.menu_save) {
            finish();
        }
        else if(menuId == android.R.id.home)
        {
            finish();
        }
        return true;
    }
}
