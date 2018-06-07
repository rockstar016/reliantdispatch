package com.rock.reliantdispatch.CarrierActivities.TruckSpace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TruckDepartureDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_departure_detail);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_truck_departure_detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_filter:
                showBottomAction();
                break;
        }
        return true;
    }

    void showBottomAction()
    {
        ActionBottomDepartureDetail f = ActionBottomDepartureDetail.newInstance();
        f.show(getSupportFragmentManager(), "bottomSheet");
    }

    public void GotoEditDeparture()
    {
        Fragment f = getSupportFragmentManager().findFragmentByTag("bottomSheet");
        if(f != null)
        {
            ((ActionBottomDepartureDetail)f).dismiss();
        }

        Intent i = new Intent(this, AddDepartureActivity.class);
        i.putExtra(BundleParamConfig.BUNDLE_CREATE_EDIT_BOOL, false);
        startActivity(i);
    }
}
