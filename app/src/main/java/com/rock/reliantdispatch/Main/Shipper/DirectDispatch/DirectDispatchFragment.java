package com.rock.reliantdispatch.Main.Shipper.DirectDispatch;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.rock.reliantdispatch.Base.BaseFragment;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.EditOrderIdActivity;
import com.rock.reliantdispatch.ShipperActivities.EditPaymentInfoActivity;
import com.rock.reliantdispatch.ShipperActivities.EditShippingDateInfoActivity;
import com.rock.reliantdispatch.ShipperActivities.PickupInfoActivity;
import com.rock.reliantdispatch.ShipperActivities.SelectCarrierByNameActivity;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DirectDispatchFragment extends BaseFragment {
    @BindView(R.id.spTrailerType)
    Spinner spTrailerType;
    Unbinder unbinder;
    @BindView(R.id.layoutCarrierInfo)
    LinearLayout layoutCarrierInfo;
    @BindView(R.id.btSearchCarrier)
    Button btSearchCarrier;
    @BindView(R.id.layoutPickupLocation)
    LinearLayout layoutPickupLocation;
    @BindView(R.id.btPickOrigin)
    Button btPickOrigin;
    @BindView(R.id.layoutDeliveryLocation)
    LinearLayout layoutDeliveryLocation;
    @BindView(R.id.btDeliveryDestination)
    Button btDeliveryDestination;
    @BindView(R.id.btOrderId)
    Button btOrderId;
    @BindView(R.id.btShipDate)
    Button btShipDate;
    @BindView(R.id.paymentLayout)
    LinearLayout paymentLayout;
    @BindView(R.id.btPaymentInfo)
    Button btPaymentInfo;
    @BindView(R.id.btDispatch)
    Button btDispatch;

    public DirectDispatchFragment() {
        // Required empty public constructor
    }

    public static DirectDispatchFragment newInstance() {
        DirectDispatchFragment fragment = new DirectDispatchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipper_direct_dispatch, container, false);
        parent.SetToolbarTitle(getString(R.string.direct_dispatch));
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, view);
        InitSpinners();
        return view;
    }

    public void InitSpinners() {
        SpinnerInitUtils.InitSpinner(this.parent, spTrailerType, R.array.trailerTypeInCreate);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_clear_only, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btSearchCarrier)
    public void onBtSearchCarrierClicked() {
        Intent i = new Intent(parent, SelectCarrierByNameActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btPickOrigin)
    public void onBtPickOriginClicked() {
        layoutPickupLocation.setVisibility(View.VISIBLE);
        Intent i = new Intent(this.parent, PickupInfoActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btDeliveryDestination)
    public void onBtDeliveryDestinationClicked() {
        layoutDeliveryLocation.setVisibility(View.VISIBLE);
        Intent i = new Intent(this.parent, PickupInfoActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btOrderId)
    public void onBtOrderIdClicked() {
        Intent i = new Intent(this.parent, EditOrderIdActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btShipDate)
    public void onBtShipDateClicked() {
        Intent i = new Intent(this.parent, EditShippingDateInfoActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btPaymentInfo)
    public void onBtPaymentInfoClicked() {
        Intent i = new Intent(this.parent, EditPaymentInfoActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btDispatch)
    public void onBtDispatchClicked() {
        //todo reset all content here
    }
}
