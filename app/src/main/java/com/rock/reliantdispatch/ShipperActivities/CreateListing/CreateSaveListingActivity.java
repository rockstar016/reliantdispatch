package com.rock.reliantdispatch.ShipperActivities.CreateListing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.EditOrderIdActivity;
import com.rock.reliantdispatch.ShipperActivities.EditPaymentInfoActivity;
import com.rock.reliantdispatch.ShipperActivities.PickupInfoActivity;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateSaveListingActivity extends BaseActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.spTrailerType) Spinner spTrailerType;
    @BindView(R.id.spOptionalDelayDays) Spinner spOptionalDelayDays;
    @BindView(R.id.layoutPickupLocation) LinearLayout layoutPickupLocation;
    @BindView(R.id.layoutDeliveryLocation) LinearLayout layoutDeliveryLocation;
    @BindView(R.id.paymentLayout) LinearLayout layoutPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_save_list);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.createSavedListing);
        InitSpinners();
    }

    void InitSpinners() {
        SpinnerInitUtils.InitSpinner(this, spTrailerType, R.array.trailerTypeInCreate);
        SpinnerInitUtils.InitSpinner(this, spOptionalDelayDays, R.array.delayDays);
    }

    @OnClick(R.id.btPickOrigin)
    public void OnClickPickOrigin()
    {
        layoutPickupLocation.setVisibility(View.VISIBLE);
        Intent i = new Intent(this, PickupInfoActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btDeliveryDestination)
    public void OnClickDeliveryDestination()
    {
        layoutDeliveryLocation.setVisibility(View.VISIBLE);
        Intent i = new Intent(this, PickupInfoActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btAddPaymentInformation)
    public void OnClickAddPaymentInformation()
    {
        layoutPayment.setVisibility(View.VISIBLE);
        Intent i = new Intent(this, EditPaymentInfoActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btPosting)
    public void OnClickBtPosting()
    {
        finish();
    }

    @OnClick(R.id.btSaveListing)
    public void OnClickSaveListing()
    {
        finish();
    }

    @OnClick(R.id.btOrderId)
    public void OnClickOrderId()
    {
        Intent i = new Intent(this, EditOrderIdActivity.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reset_only, menu);
        return true;
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
