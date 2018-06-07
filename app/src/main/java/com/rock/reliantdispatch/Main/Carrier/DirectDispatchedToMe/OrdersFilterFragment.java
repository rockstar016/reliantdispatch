package com.rock.reliantdispatch.Main.Carrier.DirectDispatchedToMe;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.rock.reliantdispatch.Main.Shipper.VehicleList.CarrierVehicleFilterListDialogFragment;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrdersFilterFragment extends BottomSheetDialogFragment {
    @BindView(R.id.tgFilterOffer)
    ToggleButton tgFilterOffer;
    @BindView(R.id.tgFilterDispatched)
    ToggleButton tgFilterDispatched;
    @BindView(R.id.tgFilterAccepted)
    ToggleButton tgFilterAccepted;
    @BindView(R.id.tgFilterTransit)
    ToggleButton tgFilterTransit;
    @BindView(R.id.tgFilterDelivered)
    ToggleButton tgFilterDelivered;
    @BindView(R.id.tgFilterCompleted)
    ToggleButton tgFilterCompleted;
    @BindView(R.id.tgFilterCancelled)
    ToggleButton tgFilterCancelled;
    @BindView(R.id.tgFilterArchive)
    ToggleButton tgFilterArchive;

    Unbinder unbinder;
    FilterDialogListener parentHandler;

    public void setParentHandler(FilterDialogListener handler)
    {
        parentHandler = handler;
    }
    public OrdersFilterFragment() {
        // Required empty public constructor
    }

    public static OrdersFilterFragment newInstance() {
        OrdersFilterFragment fragment = new OrdersFilterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_action_order_filter, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        InitToggleButtonTitle();
        SetToggleButtonsToUnChecked();
        return rootView;
    }
    void InitToggleButtonTitle() {
        tgFilterOffer.setTextOn(getString(R.string.offers_counter_offers_index, 5, 6));
        tgFilterOffer.setTextOff(getString(R.string.offers_counter_offers_index, 5, 6));
        SetToggleTitle(tgFilterAccepted, R.string.vehicle_list_kind_accepted_index, 2);
        SetToggleTitle(tgFilterArchive, R.string.vehicle_list_kind_archieved_index, 3);
        SetToggleTitle(tgFilterCancelled, R.string.vehicle_list_kind_canceled_index, 1);
        SetToggleTitle(tgFilterCompleted, R.string.vehicle_list_kind_completed_index, 1);
        SetToggleTitle(tgFilterDelivered, R.string.vehicle_list_kind_delivered_index, 6);
        SetToggleTitle(tgFilterTransit, R.string.vehicle_list_kind_in_transit_index, 5);
        SetToggleTitle(tgFilterDispatched, R.string.vehicle_list_kind_dispatched_index, 1);
    }

    void SetToggleTitle(ToggleButton tgButton, int resource, int Count) {
        tgButton.setTextOn(getContext().getString(resource, Count));
        tgButton.setTextOff(getContext().getString(resource, Count));
    }

    void SetToggleButtonsToUnChecked() {
        tgFilterAccepted.setChecked(false);
        tgFilterOffer.setChecked(false);
        tgFilterArchive.setChecked(false);
        tgFilterCancelled.setChecked(false);
        tgFilterCompleted.setChecked(false);
        tgFilterDelivered.setChecked(false);
        tgFilterTransit.setChecked(false);
        tgFilterDispatched.setChecked(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tgFilterOffer)
    public void onTgFilterOfferClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterOffer.setChecked(true);
    }

    @OnClick(R.id.tgFilterDispatched)
    public void onTgFilterDispatchedClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterDispatched.setChecked(true);
    }

    @OnClick(R.id.tgFilterAccepted)
    public void onTgFilterAcceptedClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterAccepted.setChecked(true);
    }

    @OnClick(R.id.tgFilterTransit)
    public void onTgFilterTransitClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterTransit.setChecked(true);
    }

    @OnClick(R.id.tgFilterDelivered)
    public void onTgFilterDeliveredClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterDelivered.setChecked(true);
    }

    @OnClick(R.id.tgFilterCompleted)
    public void onTgFilterCompletedClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterCompleted.setChecked(true);
    }

    @OnClick(R.id.tgFilterCancelled)
    public void onTgFilterCancelledClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterCancelled.setChecked(true);
    }

    @OnClick(R.id.tgFilterArchive)
    public void onTgFilterArchiveClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterArchive.setChecked(true);
    }
    public interface FilterDialogListener
    {
        void OnClickFilterItem(int position);
    }
}
