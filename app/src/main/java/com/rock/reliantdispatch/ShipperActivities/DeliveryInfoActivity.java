package com.rock.reliantdispatch.ShipperActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeliveryInfoActivity extends BaseActivity {



    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.companyName)
    TextInputLayout companyName;
    @BindView(R.id.contactName)
    TextInputLayout contactName;
    @BindView(R.id.address_1)
    TextInputLayout address1;
    @BindView(R.id.address_2)
    TextInputLayout address2;
    @BindView(R.id.zipCode)
    TextInputLayout zipCode;
    @BindView(R.id.city)
    TextInputLayout city;
    @BindView(R.id.phone_1)
    TextInputLayout phone1;
    @BindView(R.id.phone_2)
    TextInputLayout phone2;
    @BindView(R.id.cellphone)
    TextInputLayout cellphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_info);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return true;
    }
}
