package com.rock.reliantdispatch.CarrierActivities.SavedRouteSearch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.DraftListing.ActionBottomDraftItemFragement;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailSavedSearchActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_saved_search);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_detail_saved_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == android.R.id.home)
        {
            finish();
        }
        else if(itemId == R.id.menu_filter)
        {
            setBottomActionMenu();
        }
        return true;
    }

    void setBottomActionMenu()
    {
        ActionBottomSavedRouteSearch f = ActionBottomSavedRouteSearch.newInstance();
        showBottomSheetDialog(f);
    }

    private void showBottomSheetDialog(@NonNull ActionBottomSavedRouteSearch f) {
        f.show(getSupportFragmentManager(), "bottomSheet");
    }
}
