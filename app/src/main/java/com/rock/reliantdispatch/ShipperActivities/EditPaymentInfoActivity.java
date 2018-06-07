package com.rock.reliantdispatch.ShipperActivities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditPaymentInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.paymentToCarrier) TextInputLayout paymentToCarrier;
    @BindView(R.id.onDeliveryToCarrier) TextInputLayout onDeliveryToCarrier;
    @BindView(R.id.spPayMethod) Spinner spPayMethod;
    @BindView(R.id.spOnPay) Spinner spOnPay;
    @BindView(R.id.spInPay) Spinner spInPay;
    @BindView(R.id.spUpOnPay) Spinner spUpOnPay;
    @BindView(R.id.spCheckType) Spinner spCheckType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment_info);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.payment_information);
        InitSpinners();
    }

    void InitSpinners()
    {
        SpinnerInitUtils.InitSpinner(this, spPayMethod, R.array.paymethod);
        SpinnerInitUtils.InitSpinner(this, spOnPay, R.array.onpay);
        SpinnerInitUtils.InitSpinner(this, spInPay, R.array.inpay);
        SpinnerInitUtils.InitSpinner(this, spUpOnPay, R.array.uponpay);
        SpinnerInitUtils.InitSpinner(this, spCheckType, R.array.checktype);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            finish();
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
