package com.rock.reliantdispatch.ShipperActivities.CreateListing;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.rock.model.Common.LocationModel;
import com.rock.model.Common.VehicleModel;
import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.CommonActivity.InspectionActivity;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.CustomView.ShipDateView;
import com.rock.reliantdispatch.Interface.VehicleDetailItemViewInterface;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.Adapters.DetailVehicleRecyclerAdapter;
import com.rock.reliantdispatch.ShipperActivities.EditOrderIdActivity;
import com.rock.reliantdispatch.ShipperActivities.EditPaymentInfoActivity;
import com.rock.reliantdispatch.ShipperActivities.EditQuickAddressActivity;
import com.rock.reliantdispatch.ShipperActivities.VehicleInfoActivity;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;
import com.rock.reliantdispatch.ViewModels.VehicleListingViewModel;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class CreateQuickListingActivity extends BaseActivity implements VehicleDetailItemViewInterface{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.btPickUpLocation) Button btPickUpLocation;
    @BindView(R.id.btDeliveryLocation) Button btDeliveryLocation;
    @BindView(R.id.spTrailerType) Spinner spTrailerType;
    @BindView(R.id.spOptionalDelayDays) Spinner spOptionalDelayDays;
    @BindView(R.id.btOrderId) Button btOrderId;
    @BindView(R.id.btAddPaymentInformation) Button btAddPaymentInformation;
    @BindView(R.id.btScanVinCode) Button btScanVinCode;
    @BindView(R.id.btScanVinLetter) Button btScanVinLetter;
    @BindView(R.id.btVehicleManual) Button btVehicleManual;
    @BindView(R.id.btPostList) Button btPostList;
    @BindView(R.id.vehicleRecycler) RecyclerView vehicleRecyclerView;
    @BindView(R.id.txtQuickPickup) TextView txtQuickPickup;
    @BindView(R.id.txtQuickPickupMetro) TextView txtQuickPickupMetro;
    @BindView(R.id.txtQuickDelivery) TextView txtQuickDelivery;
    @BindView(R.id.txtQuickDeliveryMetro) TextView txtQuickDeliveryMetro;
    @BindView(R.id.txtOrderId) TextView txtOrderId;
    @BindView(R.id.shipDateView) ShipDateView shipDateView;

    @BindArray(R.array.trailerTypeInCreate) String[] arrTrailerType;

    VehicleListingViewModel mViewModel;
    DetailVehicleRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_quick_list);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(VehicleListingViewModel.class);
        InitToolbar(toolbar, R.string.quick);
        setSupportActionBar(toolbar);
        InitSpinners();

        vehicleRecyclerView.setHasFixedSize(true);
        vehicleRecyclerView.setNestedScrollingEnabled(false);
        /** Init recyclerView **/
        vehicleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailVehicleRecyclerAdapter(this, this);
        vehicleRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.getVehicleModelArrayList().observe(this, adapter::setDataProvider);
        mViewModel.getPickUpLocation().observe(this, pickUp->{
            setAddressTextView(pickUp, txtQuickPickup, txtQuickPickupMetro);
        });
        mViewModel.getDeliveryLocation().observe(this, deliver->{
            setAddressTextView(deliver, txtQuickDelivery, txtQuickDeliveryMetro);
        });
        mViewModel.getOrderId().observe(this, txtOrderId::setText);
        mViewModel.getTrailerType().observe(this, trailerType->{
            if(Objects.requireNonNull(trailerType).equalsIgnoreCase("")) {
                spTrailerType.setSelection(0);
                return;
            }
            for(int i = 0 ; i < arrTrailerType.length ; i++){
                if(arrTrailerType[i].equalsIgnoreCase(trailerType)) {
                    spTrailerType.setSelection(i);
                    return;
                }
            }
        });
        mViewModel.getShipDateInformation().observe(this, shipDateInformation-> {
            shipDateView.setModel(shipDateInformation);
        });
    }

    void setAddressTextView(LocationModel model, TextView txtAddrView, TextView txtMetro){
        String dispPickup = String.format(Locale.US, "%s-%s-%s", Objects.requireNonNull(model).getState(), model.getCity(), model.getZipCode());
        txtAddrView.setText(dispPickup);
        txtMetro.setText(model.getMetroArea());
    }

    void InitSpinners() {
        SpinnerInitUtils.InitSpinner(this, spTrailerType, R.array.trailerTypeInCreate);
        SpinnerInitUtils.InitSpinner(this, spOptionalDelayDays, R.array.delayDays);
    }

    @OnItemSelected(R.id.spTrailerType)
    void OnClickItemTrailerType() {
        mViewModel.setTrailerType(spTrailerType.getSelectedItem().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reset_only, menu);
        return true;
    }

    @OnClick(R.id.btPickUpLocation)
    public void OnClickPickupLocation() {
        Intent i = new Intent(this, EditQuickAddressActivity.class);
        i.putExtra(BundleParamConfig.BUNDLE_QUICK_LOCATION_MODEL, mViewModel.getPickUpLocation().getValue());
        startActivityForResult(i, BundleParamConfig.ACTIVITY_REQUEST_PICKUP_LOCATION);
    }

    @OnClick(R.id.btDeliveryLocation)
    public void OnClickDeliveryLocation() {
        Intent i = new Intent(this, EditQuickAddressActivity.class);
        i.putExtra(BundleParamConfig.BUNDLE_QUICK_LOCATION_MODEL, mViewModel.getDeliveryLocation().getValue());
        startActivityForResult(i, BundleParamConfig.ACTIVITY_REQUEST_DELIVERY_LOCATION);
    }

    @OnClick(R.id.btOrderId)
    public void OnClickOrderId() {
        Intent i = new Intent(this, EditOrderIdActivity.class);
        i.putExtra(BundleParamConfig.BUNDLE_ORDER_ID, mViewModel.getOrderId().getValue());
        startActivityForResult(i, BundleParamConfig.ACTIVITY_REQUEST_ORDER_ID);
    }

    @OnClick(R.id.btAddPaymentInformation)
    public void OnClickPaymentInformation() {
        Intent i = new Intent(this, EditPaymentInfoActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btPostList)
    public void OnClickPostList() {
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

    @OnClick(R.id.btScanVinCode)
    public void onBtScanVinCodeClicked() {
    }

    @OnClick(R.id.btScanVinLetter)
    public void onBtScanVinLetterClicked() {
    }

    @OnClick(R.id.btVehicleManual)
    public void onBtVehicleManualClicked() {
        Intent  i = new Intent(this, VehicleInfoActivity.class);
        VehicleModel model = new VehicleModel();
        i.putExtra(BundleParamConfig.BUNDLE_VEHICLE_MODEL, model);
        startActivityForResult(i, BundleParamConfig.ACTIVITY_REQUEST_MANUAL_ADD_VEHICLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == BundleParamConfig.ACTIVITY_REQUEST_MANUAL_ADD_VEHICLE && resultCode == Activity.RESULT_OK) {
            VehicleModel model = (VehicleModel) data.getSerializableExtra(BundleParamConfig.BUNDLE_VEHICLE_MODEL);
            mViewModel.addItemToVehidelArrayList(model);
        }
        else if(requestCode == BundleParamConfig.ACTIVITY_REQUEST_PICKUP_LOCATION && resultCode == RESULT_OK) {
            LocationModel model = (LocationModel) data.getSerializableExtra(BundleParamConfig.BUNDLE_QUICK_LOCATION_MODEL);
            mViewModel.setPickUpLocation(model);
        }
        else if(requestCode == BundleParamConfig.ACTIVITY_REQUEST_DELIVERY_LOCATION && resultCode == RESULT_OK){
            LocationModel model = (LocationModel) data.getSerializableExtra(BundleParamConfig.BUNDLE_QUICK_LOCATION_MODEL);
            mViewModel.setDeliveryLocation(model);
        }
        else if(requestCode == BundleParamConfig.ACTIVITY_REQUEST_ORDER_ID && resultCode == RESULT_OK){
            String pOrderId = data.getStringExtra(BundleParamConfig.BUNDLE_ORDER_ID);
            mViewModel.setOrderId(pOrderId);
        }
        else if(requestCode == BundleParamConfig.ACTIVITY_REQUEST_EDIT_VEHICLE && resultCode == RESULT_OK){
            VehicleModel model = (VehicleModel)data.getSerializableExtra(BundleParamConfig.BUNDLE_VEHICLE_MODEL);
            int pos = data.getIntExtra(BundleParamConfig.BUNDLE_VEHICLE_UPDATE_POSITION, 0);
            mViewModel.updateItemInVehicleArrayList(model, pos);
        }
    }

    @Override
    public void OnClickDetail(int position) {
        Intent i = new Intent(this, VehicleInfoActivity.class);
        i.putExtra(BundleParamConfig.BUNDLE_VEHICLE_MODEL, Objects.requireNonNull(mViewModel.getVehicleModelArrayList().getValue()).get(position));
        i.putExtra(BundleParamConfig.BUNDLE_VEHICLE_UPDATE_POSITION, position);
        startActivityForResult(i, BundleParamConfig.ACTIVITY_REQUEST_EDIT_VEHICLE);
    }

    @Override
    public void OnClickInspection(int postion) {
        Intent i = new Intent(this, InspectionActivity.class);
        i.putExtra(BundleParamConfig.BUNDLE_CREATE_EDIT_BOOL, true);
        startActivity(i);
    }

    @Override
    public void OnClickRemove(int position) {
        if(Objects.requireNonNull(mViewModel.getVehicleModelArrayList().getValue()).size() > position) {
            mViewModel.getVehicleModelArrayList().getValue().remove(position);
            updateRecyclerView(position);
        }
    }

    @UiThread
    void updateRecyclerView(int position )
    {
        adapter.notifyItemRemoved(position);
        vehicleRecyclerView.requestLayout();
    }
}
