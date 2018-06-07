package com.rock.reliantdispatch.Main.Carrier.TruckSpace;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTruckActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spTrailerType)
    Spinner spTrailerType;
    @BindView(R.id.spRunning)
    Spinner spRunning;
    @BindView(R.id.btCancel)
    Button btCancel;

    boolean isCreate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_truck);
        ButterKnife.bind(this);
        InitToolbar(toolbar, "Add Truck");
        InitSpinner();
        isCreate = getIntent().getBooleanExtra(BundleParamConfig.BUNDLE_CREATE_EDIT_BOOL, false);
        btCancel.setText(!isCreate?"Cancel Edit Truck Profile":"Cancel Create Truck Profile");
        setToolbarTitle();
    }

    @UiThread
    void setToolbarTitle()
    {
        getSupportActionBar().setTitle(isCreate?"Add Truck":"Edit Truck");
    }

    void InitSpinner() {
        SpinnerInitUtils.InitSpinner(this, spTrailerType, R.array.trailerType);
        SpinnerInitUtils.InitSpinner(this, spRunning, R.array.running);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_save) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else if (itemId == android.R.id.home) {
            finish();
        }
        return true;
    }

    @OnClick(R.id.btCancel)
    public void onViewClicked() {
        finish();
    }
}
