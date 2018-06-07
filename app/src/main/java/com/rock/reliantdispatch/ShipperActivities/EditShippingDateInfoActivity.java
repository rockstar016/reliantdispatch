package com.rock.reliantdispatch.ShipperActivities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditShippingDateInfoActivity extends BaseActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.txtDispatchDate) TextView txtDispatchDate;
    @BindView(R.id.firstAvailableDate) EditText firstAvailableDate;
    @BindView(R.id.pickupSpinner) Spinner pickupSpinner;
    @BindView(R.id.pickupDate) EditText pickupDate;
    @BindView(R.id.deliverSpinner) Spinner deliverSpinner;
    @BindView(R.id.deliverDate) EditText deliverDate;
//    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_date_info);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.shipping_date_information);
        InitSpinner();
    }

    void InitSpinner()
    {
        SpinnerInitUtils.InitSpinner(this, deliverSpinner, R.array.dateset);
        SpinnerInitUtils.InitSpinner(this, pickupSpinner, R.array.dateset);
    }

    @OnClick({R.id.deliverDate, R.id.pickupDate, R.id.firstAvailableDate})
    public void OpenCalendar(View selectedView) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    switch (selectedView.getId()) {
                        case R.id.firstAvailableDate:
                            firstAvailableDate.setText(String.format(Locale.US, "%d/%d/%d", monthOfYear,dayOfMonth, year));
                            break;
                        case R.id.pickupDate:
                            pickupDate.setText(String.format(Locale.US, "%d/%d/%d", monthOfYear,dayOfMonth, year));
                            break;
                        case R.id.deliverDate:
                            deliverDate.setText(String.format(Locale.US, "%d/%d/%d", monthOfYear,dayOfMonth, year));
                            break;
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_only, menu);
        return super.onCreateOptionsMenu(menu);
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
