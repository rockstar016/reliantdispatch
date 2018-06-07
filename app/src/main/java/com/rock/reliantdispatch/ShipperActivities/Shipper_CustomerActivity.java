package com.rock.reliantdispatch.ShipperActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Shipper_CustomerActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.name)
    TextInputLayout name;
    @BindView(R.id.contactName)
    TextInputLayout contactName;
    @BindView(R.id.address)
    TextInputLayout address;
    @BindView(R.id.btShowMap)
    ImageButton btShowMap;
    @BindView(R.id.city)
    TextInputLayout city;
    @BindView(R.id.state)
    TextInputLayout state;
    @BindView(R.id.zipCode)
    TextInputLayout zipCode;
    @BindView(R.id.phoneNumber)
    TextInputLayout phoneNumber;
    @BindView(R.id.email)
    TextInputLayout email;
    @BindView(R.id.fax)
    TextInputLayout fax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper__customer);
        ButterKnife.bind(this);
    }

//    @OnClick(R.id.back)
//    public void onBackClicked(View view) {
//        finish();
//    }
//    @OnClick(R.id.save)
//    public void onSaveClicked(View view) {
//        Intent returnIntent = new Intent();
//        setResult(Activity.RESULT_OK, returnIntent);
//        finish();
//    }
}
