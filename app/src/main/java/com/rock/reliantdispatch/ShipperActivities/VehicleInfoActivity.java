package com.rock.reliantdispatch.ShipperActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;
import android.widget.TextView;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VehicleInfoActivity extends BaseActivity {
    public static final String RETURN_VALUE = "CAR_MODEL";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vin)
    TextInputLayout vin;
    @BindView(R.id.year)
    TextInputLayout year;
    @BindView(R.id.make)
    TextInputLayout make;
    @BindView(R.id.model)
    TextInputLayout model;
    @BindView(R.id.trim)
    TextInputLayout trim;
    @BindView(R.id.typeSpinner)
    Spinner typeSpinner;
    @BindView(R.id.driveType)
    TextInputLayout driveType;
    @BindView(R.id.length)
    TextInputLayout length;
    @BindView(R.id.width)
    TextInputLayout width;
    @BindView(R.id.height)
    TextInputLayout height;
    @BindView(R.id.curbWeight)
    TextInputLayout curbWeight;
    @BindView(R.id.typeRunning)
    Spinner typeRunning;
    @BindView(R.id.qty)
    TextInputLayout qty;
    @BindView(R.id.color)
    TextInputLayout color;
    @BindView(R.id.licensePlate)
    TextInputLayout licensePlate;
    @BindView(R.id.auctionLot)
    TextInputLayout auctionLot;
    @BindView(R.id.stateSpinner)
    Spinner stateSpinner;
    @BindView(R.id.additionInfo)
    TextInputLayout additionInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.vehicle_information);
        SpinnerSetup();
    }

    private void SpinnerSetup() {

    }



//    @OnClick(R.id.save)
//    public void onSaveClicked() {
//        if (StringCheckUtil.isEmpty(this, year)) return;
//        if (StringCheckUtil.isEmpty(this, make)) return;
//        if (StringCheckUtil.isEmpty(this, model)) return;
//
//        Intent returnIntent = new Intent();
//        VehicleModel vehicleModel = new VehicleModel();
//        vehicleModel.setAdditionalInformation(additionInfo.getEditText().getText().toString().trim());
//        vehicleModel.setColor(color.getEditText().getText().toString().trim());
//        vehicleModel.setCurbWeight(curbWeight.getEditText().toString().trim());
//        vehicleModel.setDriveType(driveType.getEditText().getText().toString().trim());
//        vehicleModel.setHeight(height.getEditText().getText().toString().trim());
//        vehicleModel.setLength(length.getEditText().getText().toString().trim());
//        vehicleModel.setLot(auctionLot.getEditText().getText().toString().trim());
//        vehicleModel.setLicensePlate(licensePlate.getEditText().getText().toString().trim());
//        vehicleModel.setMake(make.getEditText().getText().toString().trim());
//        vehicleModel.setModel(model.getEditText().getText().toString().trim());
//        vehicleModel.setQty(Integer.parseInt(qty.getEditText().getText().toString().trim()));
//        vehicleModel.setRunning(typeRunning.getSelectedItem().toString());
//        vehicleModel.setState(stateSpinner.getSelectedItem().toString());
//        vehicleModel.setTrim(trim.getEditText().getText().toString().trim());
//        vehicleModel.setVehicleType(typeSpinner.getSelectedItem().toString());
//        vehicleModel.setVinCode(vin.getEditText().getText().toString().trim());
//        vehicleModel.setWidth(width.getEditText().getText().toString().trim());
//        vehicleModel.setYear(year.getEditText().getText().toString().trim());
//        returnIntent.putExtra(RETURN_VALUE, vehicleModel);
//        setResult(Activity.RESULT_OK, returnIntent);
//        finish();
//    }
}
