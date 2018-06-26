package com.rock.reliantdispatch.ShipperActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.StringCheckUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditOrderIdActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.orderId)
    TextInputLayout orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order_id);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.my_order_id);
        String pOrderId = getIntent().getStringExtra(BundleParamConfig.BUNDLE_ORDER_ID);
        orderId.getEditText().setText(pOrderId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_save)
        {
            finishAsSuccess();
        }
        else if(item.getItemId() == android.R.id.home)
        {
            Intent i = new Intent();
            setResult(Activity.RESULT_CANCELED, i);
            finish();
        }
        return true;
    }

    void finishAsSuccess() {
        if(StringCheckUtil.isEmpty(this,orderId)) return;
        Intent i = new Intent();
        i.putExtra(BundleParamConfig.BUNDLE_ORDER_ID, orderId.getEditText().getText().toString());
        setResult(Activity.RESULT_OK, i);
        finish();
    }

}
