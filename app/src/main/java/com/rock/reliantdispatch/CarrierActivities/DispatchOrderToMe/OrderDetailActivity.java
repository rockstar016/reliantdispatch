package com.rock.reliantdispatch.CarrierActivities.DispatchOrderToMe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;

import com.rock.reliantdispatch.Base.ActionBottomBaseFragment;
import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.CarrierActivities.Adapters.VehicleRecyclerAdapter;
import com.rock.reliantdispatch.Constants.OrderConfig;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.VehicleListing.ActionBottomDispatchedItemFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailActivity extends BaseActivity implements BaseRecyclerInterface {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerVehicle)
    RecyclerView recyclerVehicle;
    VehicleRecyclerAdapter mVehicleAdapter;
    int OrderKind;
    public static String BUNDLE_ORDER_KIND = "OrderKind";
    @BindView(R.id.btViewGatePass)
    Button btViewGatePass;
    @BindView(R.id.scrollContainer)
    ScrollView scrollContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_order_detail);
        OrderKind = getIntent().getIntExtra(BUNDLE_ORDER_KIND, OrderConfig.ORDER_TYPE_DISPATCHED);

        recyclerVehicle.setHasFixedSize(true);
        mVehicleAdapter = new VehicleRecyclerAdapter(this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerVehicle.setLayoutManager(layoutManager);
        recyclerVehicle.setAdapter(mVehicleAdapter);
        recyclerVehicle.setNestedScrollingEnabled(false);
        recyclerVehicle.setHasFixedSize(true);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_filter:
                showFilterBasedOnOrderKind();
                break;
        }
        return true;
    }

    void showFilterBasedOnOrderKind() {
        ActionBottomBaseFragment f = null;
        switch (OrderKind) {
            case OrderConfig.ORDER_TYPE_OFFER:
                f = ActionBottomOrderOffer.newInstance();
                break;
            case OrderConfig.ORDER_TYPE_DISPATCHED:
                f = ActionBottomOrderDispatched.newInstance();
                break;
            case OrderConfig.ORDER_TYPE_ACCEPTED:
                f = ActionBottomOrderAccepted.newInstance();
                break;
            case OrderConfig.ORDER_TYPE_INTRANSIT:
                f = ActionBottomOrderInTransit.newInstance();
                break;
            case OrderConfig.ORDER_TYPE_DELIVERED:
                f = ActionBottomOrderDelivered.newInstance();
                break;
            case OrderConfig.ORDER_TYPE_COMPLETED:
                f = ActionBottomOrderCompleted.newInstance();
                break;
            case OrderConfig.ORDER_TYPE_CANCELLED:
                f = ActionBottomOrderCancelled.newInstance();
                break;
            case OrderConfig.ORDER_TYPE_ARCHIVED:
                f = ActionBottomOrderArchived.newInstance();
                break;
        }
        attachBottomSheetDialog(f);
    }

    void attachBottomSheetDialog(ActionBottomBaseFragment f)
    {
        if(f != null)
        {
            f.show(getSupportFragmentManager(), "bottomSheetDialog");
        }
    }

    @Override
    public void OnRecyclerItemClick(int position) {

    }
}
