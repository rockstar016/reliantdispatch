package com.rock.reliantdispatch.ShipperActivities;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.rock.model.Common.LocationModel;
import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;
import com.rock.reliantdispatch.ViewModels.Shipper.EditQuickAddressViewModel;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditQuickAddressActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spState)
    Spinner spState;
    @BindView(R.id.txtZip)
    TextInputLayout txtZip;
    @BindView(R.id.txtCity)
    TextInputLayout txtCity;

    EditQuickAddressViewModel mViewModel;
    boolean isPickUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address_quicklist);
        ButterKnife.bind(this);
        InitToolbar(toolbar, "Pickup Location");
        InitSpinner();
        mViewModel = ViewModelProviders.of(this).get(EditQuickAddressViewModel.class);
        LocationModel mInit = (LocationModel) getIntent().getSerializableExtra(BundleParamConfig.BUNDLE_QUICK_LOCATION_MODEL);
        isPickUp = getIntent().getBooleanExtra(BundleParamConfig.BUNDLE_CREATE_EDIT_BOOL, false);
        mViewModel.initValues(mInit);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SetToolbarTitle(isPickUp?"Pickup Location":"Delivery Location");
        mViewModel.getCity().observe(this, Objects.requireNonNull(txtCity.getEditText())::setText);
        mViewModel.getState().observe(this, this::setStateValue);
        mViewModel.getZipCode().observe(this, Objects.requireNonNull(txtZip.getEditText())::setText);
        mViewModel.getIsBusy().observe(this, isBusy->{
            if(isBusy){
                ShowLoadingDialog("Loading");
            }
            else{
                HideLoadingDialog();
            }
        });
//        mViewModel.getGoogleApiError().observe(this, isError->{
//            if(isError) {
//                DialogUtils.showOkDialog(EditQuickAddressActivity.this, "Warning", "Failed to parse address from Google. Try again.");
//                mViewModel.setGoogleApiError(false);
//            }
//        });
    }

    @UiThread
    void setStateValue(String value) {
        if (value == null || value.isEmpty()) {
            spState.setSelection(0);
        } else {
            for (int i = 0; i < spState.getAdapter().getCount(); i++) {
                if (spState.getItemAtPosition(i).equals(value)) {
                    spState.setSelection(i);
                    break;
                }
            }
        }
    }

    void InitSpinner() {
        SpinnerInitUtils.InitSpinner(this, spState, R.array.state_names);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            FinishAsSuccess();
        } else if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent();
            setResult(Activity.RESULT_CANCELED, i);
            finish();
        }
        return true;
    }

    void FinishAsSuccess() {
        LocationModel model = new LocationModel();
        model.setZipCode(Objects.requireNonNull(txtZip.getEditText()).getText().toString());
        model.setCity(Objects.requireNonNull(txtCity.getEditText()).getText().toString());
        model.setState(spState.getSelectedItem().toString());
        model.setMetroArea(String.format(Locale.US, "%s - %s - %s", model.getState(), model.getCity(), model.getZipCode()));
        Intent i = new Intent();
        i.putExtra(BundleParamConfig.BUNDLE_QUICK_LOCATION_MODEL, model);
        setResult(Activity.RESULT_OK, i);
        finish();
    }


}
