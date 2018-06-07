package com.rock.reliantdispatch.ShipperActivities.CreateListing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.EditOrderIdActivity;
import com.rock.reliantdispatch.ShipperActivities.EditPaymentInfoActivity;
import com.rock.reliantdispatch.ShipperActivities.EditQuickAddressActivity;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateQuickListingActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btPickUpLocation) Button btPickUpLocation;
    @BindView(R.id.btDeliveryLocation) Button btDeliveryLocation;
    @BindView(R.id.spTrailerType) Spinner spTrailerType;
    @BindView(R.id.spOptionalDelayDays) Spinner spOptionalDelayDays;
    @BindView(R.id.btOrderId) Button btOrderId;
    @BindView(R.id.btAddPaymentInformation) Button btAddPaymentInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_quick_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.quick);
        InitSpinners();
    }

    void InitSpinners()
    {
        SpinnerInitUtils.InitSpinner(this, spTrailerType, R.array.trailerTypeInCreate);
        SpinnerInitUtils.InitSpinner(this, spOptionalDelayDays, R.array.delayDays);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reset_only, menu);
        return true;
    }

    @OnClick(R.id.btPickUpLocation)
    public void OnClickPickupLocation()
    {
        Intent i = new Intent(this, EditQuickAddressActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btDeliveryLocation)
    public void OnClickDeliveryLocation()
    {
        Intent i = new Intent(this, EditQuickAddressActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btOrderId)
    public void OnClickOrderId()
    {
        Intent i = new Intent(this, EditOrderIdActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btAddPaymentInformation)
    public void OnClickPaymentInformation()
    {
        Intent i = new Intent(this, EditPaymentInfoActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btPostList)
    public void OnClickPostList()
    {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_reset:
                break;
        }
        return true;
    }
}
