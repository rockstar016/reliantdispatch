package com.rock.reliantdispatch.ShipperActivities.DraftListing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailDraftItemActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    FragmentDetailVehicleDraftItem fragVehicleDetail;
    @BindView(R.id.scrollContainer)
    ScrollView scrollContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_draft_item);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_detail_draft_item);
        fragVehicleDetail = (FragmentDetailVehicleDraftItem) getSupportFragmentManager().findFragmentById(R.id.fragVehicleDetail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter_only, menu);
        return true;
    }

    void GotoTop() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            scrollContainer.scrollTo(0, 0);
        }, 100);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId) {
            case android.R.id.home:
                finish();
                break;

            case R.id.menu_filter:
                SetBottomActionFragment();
                break;
        }
        return true;
    }

    private void showBottomSheetDialog(@NonNull ActionBottomDraftItemFragement f) {
        f.show(getSupportFragmentManager(), "bottomSheet");
    }

    @UiThread
    public void SetBottomActionFragment() {
        ActionBottomDraftItemFragement f = ActionBottomDraftItemFragement.newInstance();
        showBottomSheetDialog(f);
    }
}
