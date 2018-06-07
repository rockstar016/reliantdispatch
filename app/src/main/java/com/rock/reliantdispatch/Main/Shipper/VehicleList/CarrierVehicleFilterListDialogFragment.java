package com.rock.reliantdispatch.Main.Shipper.VehicleList;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.rock.reliantdispatch.Constants.VehiclesConfig;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ViewModels.VehicleListViewModel;

import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CarrierVehicleFilterListDialogFragment extends BottomSheetDialogFragment {


    @BindView(R.id.tgFilterListed)
    ToggleButton tgFilterListed;
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
    VehicleListViewModel mViewModel;
    public void setmViewModel(VehicleListViewModel model)
    {
        mViewModel = model;
    }

    public static CarrierVehicleFilterListDialogFragment newInstance() {
        final CarrierVehicleFilterListDialogFragment fragment = new CarrierVehicleFilterListDialogFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_action_vehicle_filter, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.getCurrentSelectedFilter().observe(this, this::InitFirstToggleButton);
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    void InitFirstToggleButton(int currentSelectionCategory)
    {
        InitToggleButtonTitle();
        SetToggleButtonsToUnChecked();
        if(currentSelectionCategory < 0) currentSelectionCategory = VehiclesConfig.VEHICLE_LISTING_TYPE_LISTED;
        switch (currentSelectionCategory)
        {
            case VehiclesConfig.VEHICLE_LISTING_TYPE_LISTED:
                tgFilterListed.setChecked(true);
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_DISPATCHED:
                tgFilterDispatched.setChecked(true);
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_ACCEPTED:
                tgFilterAccepted.setChecked(true);
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_INTRANSIT:
                tgFilterTransit.setChecked(true);
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_DELIVERED:
                tgFilterDelivered.setChecked(true);
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_COMPLETED:
                tgFilterCompleted.setChecked(true);
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_CANCELLED:
                tgFilterCancelled.setChecked(true);
                break;
            case VehiclesConfig.VEHICLE_LISTING_TYPE_ARCHIVED:
                tgFilterArchive.setChecked(true);
                break;
        }
    }

    void InitToggleButtonTitle() {
        int accepted = mViewModel.getStatusCount(VehiclesConfig.VEHICLE_LISTING_TYPE_ACCEPTED);
        int archived = mViewModel.getStatusCount(VehiclesConfig.VEHICLE_LISTING_TYPE_ARCHIVED);
        int cancelled = mViewModel.getStatusCount(VehiclesConfig.VEHICLE_LISTING_TYPE_CANCELLED);
        int completed = mViewModel.getStatusCount(VehiclesConfig.VEHICLE_LISTING_TYPE_COMPLETED);
        int delivered = mViewModel.getStatusCount(VehiclesConfig.VEHICLE_LISTING_TYPE_DELIVERED);
        int transit = mViewModel.getStatusCount(VehiclesConfig.VEHICLE_LISTING_TYPE_INTRANSIT);
        int dispatched = mViewModel.getStatusCount(VehiclesConfig.VEHICLE_LISTING_TYPE_DISPATCHED);
        int listed = mViewModel.getStatusCount(VehiclesConfig.VEHICLE_LISTING_TYPE_LISTED);
        SetToggleTitle(tgFilterAccepted, R.string.vehicle_list_kind_accepted_index, accepted);
        SetToggleTitle(tgFilterArchive, R.string.vehicle_list_kind_archieved_index, archived);
        SetToggleTitle(tgFilterCancelled, R.string.vehicle_list_kind_canceled_index, cancelled);
        SetToggleTitle(tgFilterCompleted, R.string.vehicle_list_kind_completed_index, completed);
        SetToggleTitle(tgFilterDelivered, R.string.vehicle_list_kind_delivered_index, delivered);
        SetToggleTitle(tgFilterTransit, R.string.vehicle_list_kind_in_transit_index, transit);
        SetToggleTitle(tgFilterDispatched, R.string.vehicle_list_kind_dispatched_index, dispatched);
        SetToggleTitle(tgFilterListed, R.string.vehicle_list_kind_listed_index, listed);
    }

    @UiThread
    void SetToggleTitle(ToggleButton tgButton, int resource, int Count) {
        tgButton.setTextOn(getContext().getString(resource, Count));
        tgButton.setTextOff(getContext().getString(resource, Count));
    }

    void SetToggleButtonsToUnChecked() {
        tgFilterAccepted.setChecked(false);
        tgFilterArchive.setChecked(false);
        tgFilterCancelled.setChecked(false);
        tgFilterCompleted.setChecked(false);
        tgFilterDelivered.setChecked(false);
        tgFilterTransit.setChecked(false);
        tgFilterDispatched.setChecked(false);
        tgFilterListed.setChecked(false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tgFilterListed)
    public void onTgFilterListedClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterListed.setChecked(true);
        mViewModel.LoadVehicleList(VehiclesConfig.VEHICLE_LISTING_TYPE_LISTED);
        this.dismiss();
    }

    @OnClick(R.id.tgFilterDispatched)
    public void onTgFilterDispatchedClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterDispatched.setChecked(true);
        mViewModel.LoadVehicleList(VehiclesConfig.VEHICLE_LISTING_TYPE_DISPATCHED);
        this.dismiss();
    }

    @OnClick(R.id.tgFilterAccepted)
    public void onTgFilterAcceptedClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterAccepted.setChecked(true);
        mViewModel.LoadVehicleList(VehiclesConfig.VEHICLE_LISTING_TYPE_ACCEPTED);
        this.dismiss();
    }

    @OnClick(R.id.tgFilterTransit)
    public void onTgFilterTransitClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterTransit.setChecked(true);
        mViewModel.LoadVehicleList(VehiclesConfig.VEHICLE_LISTING_TYPE_INTRANSIT);
        this.dismiss();
    }

    @OnClick(R.id.tgFilterDelivered)
    public void onTgFilterDeliveredClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterDelivered.setChecked(true);
        mViewModel.LoadVehicleList(VehiclesConfig.VEHICLE_LISTING_TYPE_DELIVERED);
        this.dismiss();
    }

    @OnClick(R.id.tgFilterCompleted)
    public void onTgFilterCompletedClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterCompleted.setChecked(true);
        mViewModel.LoadVehicleList(VehiclesConfig.VEHICLE_LISTING_TYPE_COMPLETED);
        this.dismiss();
    }

    @OnClick(R.id.tgFilterCancelled)
    public void onTgFilterCancelledClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterCancelled.setChecked(true);
        mViewModel.LoadVehicleList(VehiclesConfig.VEHICLE_LISTING_TYPE_CANCELLED);
        this.dismiss();
    }

    @OnClick(R.id.tgFilterArchive)
    public void onTgFilterArchiveClicked() {
        SetToggleButtonsToUnChecked();
        tgFilterArchive.setChecked(true);
        mViewModel.LoadVehicleList(VehiclesConfig.VEHICLE_LISTING_TYPE_ARCHIVED);
        this.dismiss();
    }
}
