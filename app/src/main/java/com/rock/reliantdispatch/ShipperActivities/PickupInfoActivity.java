package com.rock.reliantdispatch.ShipperActivities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.rock.model.Common.PickupDeliveryModel;
import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickupInfoActivity extends BaseActivity {
    PickupDeliveryModel pickupInfoModel;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spState)
    Spinner spState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_info);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.pickup_information);
        SpinnerInitUtils.InitSpinner(this, spState, R.array.state_names);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_save)
        {
            finish();
        }
        else if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return true;
    }
}
