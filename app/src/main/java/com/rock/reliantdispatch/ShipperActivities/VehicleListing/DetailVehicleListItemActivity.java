package com.rock.reliantdispatch.ShipperActivities.VehicleListing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.rock.reliantdispatch.Base.ActionBottomBaseFragment;
import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Constants.VehiclesConfig;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailVehicleListItemActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.scrollContainer)
    ScrollView scrollContainer;

    public int VehicleListItemType;
    public static String VEHICLE_ITEM_TYPE = "ListItemType";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vehicle_list);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_detail_vehicle_list_item);
        VehicleListItemType = getIntent().getIntExtra(VEHICLE_ITEM_TYPE, VehiclesConfig.VEHICLE_LISTING_TYPE_LISTED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter_only, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        GotoTop();
    }

    void GotoTop()
    {
        new Handler(Looper.getMainLooper()).postDelayed(() ->{
            scrollContainer.scrollTo(0,0);
        }, 100);
    }

    @UiThread
    public void SetBottomActionFragment()
    {
        ActionBottomBaseFragment f;
        switch (VehicleListItemType)
        {
            case VehiclesConfig.VEHICLE_LISTING_TYPE_LISTED:
                f = ActionBottomListedItemFragment.newInstance();
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_DISPATCHED:
                f = ActionBottomDispatchedItemFragment.newInstance();
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_INTRANSIT:
                f = ActionBottomInTransitItemFragment.newInstance();
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_ACCEPTED:
                f = ActionBottomAcceptedItemFragment.newInstance();
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_DELIVERED:
                f = ActionBottomAcceptedItemFragment.newInstance();
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_COMPLETED:
                f = ActionBottomCompletedItemFragment.newInstance();
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_CANCELLED:
                f = ActionBottomCancelledItemFragment.newInstance();
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_ARCHIVED:
                f = ActionBottomArchivedItemFragment.newInstance();
                break;
            default:
                f = ActionBottomListedItemFragment.newInstance();
        }
        showBottomSheetDialog(f);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId)
        {
            case android.R.id.home:
                finish();
                break;

            case R.id.menu_filter:
                SetBottomActionFragment();
                break;
        }
        return true;
    }

    private void showBottomSheetDialog(@NonNull ActionBottomBaseFragment f) {
        f.show(getSupportFragmentManager(), "bottomSheet");
    }
}
