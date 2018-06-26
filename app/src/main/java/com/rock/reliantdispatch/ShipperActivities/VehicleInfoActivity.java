package com.rock.reliantdispatch.ShipperActivities;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.myhexaville.smartimagepicker.ImagePicker;
import com.rock.model.Common.VehicleModel;
import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;
import com.rock.reliantdispatch.Utils.StringCheckUtil;
import com.rock.reliantdispatch.ViewModels.LoadVehicleInfoViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindArray;
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
    @BindView(R.id.spVehicleType)
    Spinner spVehicleType;
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
    @BindArray(R.array.CarTypeList)
    String[] resCarTypeList;
    @BindView(R.id.imgCar)
    ImageView imgCar;
    @BindView(R.id.btPhotoChange)
    Button btPhotoChange;

    LoadVehicleInfoViewModel mViewModel;
    ImagePicker carPhotoPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.vehicle_information);
        SpinnerSetup();
        mViewModel = ViewModelProviders.of(this).get(LoadVehicleInfoViewModel.class);
        Objects.requireNonNull(vin.getEditText()).setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_ENTER) {
                DoSearch();
                return true;
            }
            return false;
        });
        VehicleModel vehicleModel = (VehicleModel) getIntent().getSerializableExtra(BundleParamConfig.BUNDLE_VEHICLE_MODEL);
        int pos = getIntent().getIntExtra(BundleParamConfig.BUNDLE_VEHICLE_UPDATE_POSITION, -1);
        mViewModel.setUpdatePosition(pos);
        mViewModel.setRetVal(vehicleModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            Intent i = new Intent();
            setResult(Activity.RESULT_CANCELED, i);
            finish();
        } else if (itemId == R.id.menu_save) {
            FinishAsSuccess();
        }
        return false;
    }

    void FinishAsSuccess() {
        /*** todo check valid information function
         if(CheckValidInformation()) {
         DialogUtils.showOkDialog(this, "Warning", "You have no valid vehicle information");
         return;
         }
         ***/
        makeReturnValue();
        Intent i = new Intent();
        i.putExtra(BundleParamConfig.BUNDLE_VEHICLE_MODEL, mViewModel.getVehicleModel().getValue());
        i.putExtra(BundleParamConfig.BUNDLE_VEHICLE_UPDATE_POSITION, mViewModel.getUpdatePosition());
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    void makeReturnValue() {
        VehicleModel mModel = new VehicleModel();
        mModel.setModel(Objects.requireNonNull(model.getEditText()).getText().toString());
        mModel.setMake(Objects.requireNonNull(make.getEditText()).getText().toString());
        mModel.setYear(Objects.requireNonNull(year.getEditText()).getText().toString());
        mModel.setVehicleType(spVehicleType.getSelectedItem().toString());
        mModel.setTrim(Objects.requireNonNull(trim.getEditText()).getText().toString());
        mModel.setRunning(spVehicleType.getSelectedItem().toString());
        mModel.setLot(Objects.requireNonNull(auctionLot.getEditText()).getText().toString());
        mModel.setVinCode(Objects.requireNonNull(vin.getEditText()).getText().toString());
        mModel.setLength(Objects.requireNonNull(length.getEditText()).getText().toString());
        mModel.setWidth(Objects.requireNonNull(width.getEditText()).getText().toString());
        mModel.setHeight(Objects.requireNonNull(height.getEditText()).getText().toString());
        mModel.setDriveType(Objects.requireNonNull(driveType.getEditText()).getText().toString());
        mModel.setCurbWeight(Objects.requireNonNull(curbWeight.getEditText()).getText().toString());
        mModel.setQty(Integer.parseInt(Objects.requireNonNull(qty.getEditText()).getText().toString()));
        mModel.setState(stateSpinner.getSelectedItem().toString());
        mModel.setAdditionalInformation(Objects.requireNonNull(additionInfo.getEditText()).getText().toString());
        mModel.setColor(Objects.requireNonNull(color.getEditText()).getText().toString());
        mModel.setLicensePlate(Objects.requireNonNull(licensePlate.getEditText()).getText().toString());
        if(mViewModel.getNewCarImage().getValue() == null)
        {
            mModel.setCarPicture(Objects.requireNonNull(mViewModel.getVehicleModel().getValue()).getCarPicture());
        }
        else
        {
            mModel.setCarPicture(mViewModel.getNewCarImage().getValue().toString());
        }
        mViewModel.setRetVal(mModel);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mViewModel.getIsBusy().observe(this, isBusy -> {
            if (Objects.requireNonNull(isBusy)) {
                ShowLoadingDialog("Loading Information");
            } else {
                HideLoadingDialog();
            }
        });

        mViewModel.getVehicleModel().observe(this, this::fillOutUI);
        mViewModel.getNewCarImage().observe(this, imgUri->{
            if(imgUri != null)
            {
                Picasso.get().load(imgUri).into(imgCar);
            }
        });
        carPhotoPicker = new ImagePicker(this, null,
                imageUri -> {
                    mViewModel.setNewCarImage(imageUri);
                }).setWithImageCrop(3, 2 );
    }

    @UiThread
    void fillOutUI(VehicleModel value) {
        Objects.requireNonNull(year.getEditText()).setText(value.getYear());
        Objects.requireNonNull(make.getEditText()).setText(value.getMake());
        Objects.requireNonNull(model.getEditText()).setText(value.getModel());
        Objects.requireNonNull(trim.getEditText()).setText(value.getTrim());
        for (int i = 0; i < resCarTypeList.length; i++) {
            if (value.getVehicleType().equalsIgnoreCase(resCarTypeList[i])) {
                spVehicleType.setSelection(i);
                break;
            }
        }
        Objects.requireNonNull(driveType.getEditText()).setText(value.getDriveType());
        Objects.requireNonNull(length.getEditText()).setText(value.getLength());
        Objects.requireNonNull(width.getEditText()).setText(value.getWidth());
        Objects.requireNonNull(height.getEditText()).setText(value.getHeight());
        Objects.requireNonNull(curbWeight.getEditText()).setText(value.getCurbWeight());
        Objects.requireNonNull(qty.getEditText()).setText(value.getQty() + "");

        if(mViewModel.getNewCarImage().getValue() == null){
            if(!value.getCarPicture().isEmpty()) {
                Picasso picasso = Picasso.get();
                picasso.setLoggingEnabled(true);
                picasso.load(value.getCarPicture()).into(imgCar, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("d","d");
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            else {
                Picasso.get().load(R.drawable.car).into(imgCar);
            }
        }
        else {
            Picasso.get().load(mViewModel.getNewCarImage().getValue()).error(R.drawable.car).into(imgCar);
        }
    }

    void DoSearch() {
        if (!StringCheckUtil.isEmpty(this, vin) && isConnected) {
            mViewModel.loadNewVehicleInfo(Objects.requireNonNull(vin.getEditText()).getText().toString());
        }
    }

    private void SpinnerSetup() {
        SpinnerInitUtils.InitSpinner(this, spVehicleType, R.array.CarTypeList);
    }

    @OnClick(R.id.btPhotoChange)
    public void onPhotoChangeClicked() {
        CharSequence colors[] = new CharSequence[] {"Photo From Gallery", "Take Camera", "Remove Picture"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, (dialog, which) -> {
            switch(which)
            {
                case 0:
                    PickPhotoFromGallery();
                    break;
                case 1:
                    TakePhotoFromCamera();
                    break;
                case 2:
                    RemovePicture();
                    break;
            }
        });
        builder.show();
    }

    @UiThread
    void PickPhotoFromGallery() {
        carPhotoPicker.choosePicture(false);
    }

    @UiThread
    void TakePhotoFromCamera(){
        carPhotoPicker.openCamera();
    }

    @UiThread
    void RemovePicture(){
        Objects.requireNonNull(mViewModel.getVehicleModel().getValue()).setCarPicture("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        carPhotoPicker.handleActivityResult(resultCode, requestCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        carPhotoPicker.handlePermission(requestCode, grantResults);
    }
}
