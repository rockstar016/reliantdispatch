package com.rock.reliantdispatch.Main.Carrier;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.androidbuts.multispinnerfilter.MultiSpinner;
import com.rock.reliantdispatch.Base.BaseFragment;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewSearchFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.pickup_cityCheck)
    CheckBox pickupCityCheck;
    @BindView(R.id.pickupCityGroup) MultiSpinner pickupCityGroup;
    @BindView(R.id.pickup_regionSearch) LinearLayout pickupRegionSearch;
    @BindView(R.id.zipCode) TextInputLayout zipCode;
    @BindView(R.id.city) TextInputLayout city;
    @BindView(R.id.pickup_addressSearch) LinearLayout pickupAddressSearch;
    @BindView(R.id.deliveryCheck) CheckBox deliveryCheck;
    @BindView(R.id.deliveryCityGroup) MultiSpinner deliveryCityGroup;
    @BindView(R.id.delivery_regionSearch) LinearLayout deliveryRegionSearch;
    @BindView(R.id.deliveryZipCode) TextInputLayout deliveryZipCode;
    @BindView(R.id.deliveryCity) TextInputLayout deliveryCity;
    @BindView(R.id.delivery_addressSearch) LinearLayout deliveryAddressSearch;
    @BindView(R.id.vehicleType) MultiSpinner vehicleType;
    @BindView(R.id.paymentType) MultiSpinner paymentType;
    @BindView(R.id.pickup_spDistance) Spinner pickup_spDistance;
    @BindView(R.id.pickup_spStates) Spinner pickup_spStates;
    @BindView(R.id.deliver_spDistance) Spinner deliver_spDistance;
    @BindView(R.id.deliver_spStates) Spinner deliver_spStates;
    @BindView(R.id.vehicleSpTrailerType) Spinner vehicleSpTrailerType;
    @BindView(R.id.vehicleSpRunning) Spinner vehicleSpRunning;
    @BindView(R.id.vehicleSpMinVehicle) Spinner vehicleSpMinVehicle;
    @BindView(R.id.vehicleSpMaxVehicle) Spinner vehicleSpMaxVehicle;
    @BindView(R.id.datepriceSpShipWithIn) Spinner datepriceSpShipWithIn;
    @BindView(R.id.searchSwitchNewListingNotification) Switch searchSwitchNewListingNotification;
    @BindView(R.id.searchSpHightLight) Spinner searchSpHightLight;
    @BindView(R.id.searchChkPlaceHight) CheckBox searchChkPlaceHight;
    @BindView(R.id.searchSpShowCount) Spinner searchSpShowCount;
    @BindView(R.id.searchTxtCompanyTitle) TextView searchTxtCompanyTitle;
    @BindView(R.id.searchBtRemove) ImageButton searchBtRemove;
    @BindView(R.id.searchBtCompanySearch) ImageButton searchBtCompanySearch;
    @BindView(R.id.searchSpSortBy) Spinner searchSpSortBy;
    @BindView(R.id.searchSpSortByDirection) Spinner searchSpSortByDirection;
    @BindView(R.id.searchSpThenSortBy) Spinner searchSpThenSortBy;
    @BindView(R.id.searchSpThenSortByDirection) Spinner searchSpThenSortByDirection;
    @BindView(R.id.searchSpRefreshNewListing) Spinner searchSpRefreshNewListing;
    @BindView(R.id.searchSwitchSound) Switch searchSwitchSound;

    public NewSearchFragment() {
        // Required empty public constructor
    }

    public static NewSearchFragment newInstance() {
        NewSearchFragment fragment = new NewSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrier_new_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        parent.SetToolbarTitle(getString(R.string.new_search));
        Setup();
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_save_only, menu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void Setup() {
        deliveryCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            deliveryRegionSearch.setVisibility(isChecked?View.GONE:View.VISIBLE);
            deliveryAddressSearch.setVisibility(isChecked?View.VISIBLE:View.GONE);
        });
        pickupCityCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            pickupRegionSearch.setVisibility(isChecked?View.GONE:View.VISIBLE);
            pickupAddressSearch.setVisibility(isChecked?View.VISIBLE:View.GONE);
        });

        SpinnerInitUtils.InitMultipleSpinner(this.parent, pickupCityGroup, R.array.cityGroup, selected -> {});
        SpinnerInitUtils.InitMultipleSpinner(this.parent, deliveryCityGroup, R.array.cityGroup, selected -> {});
        SpinnerInitUtils.InitMultipleSpinner(this.parent, paymentType, R.array.paymenttype, selected -> {});
        SpinnerInitUtils.InitMultipleSpinner(this.parent, vehicleType, R.array.CarTypeList, selected -> {});
        SpinnerInitUtils.InitMultipleSpinner(this.parent, paymentType, R.array.paymenttype, selected -> {});

        SpinnerInitUtils.InitSpinner(this.parent, pickup_spDistance, R.array.distance);
        SpinnerInitUtils.InitSpinner(this.parent, deliver_spDistance, R.array.distance);
        SpinnerInitUtils.InitSpinner(this.parent, pickup_spStates, R.array.state_names);
        SpinnerInitUtils.InitSpinner(this.parent, deliver_spStates, R.array.state_names);
        SpinnerInitUtils.InitSpinner(this.parent, vehicleSpTrailerType, R.array.trailerType);
        SpinnerInitUtils.InitSpinner(this.parent, vehicleSpRunning, R.array.running);
        SpinnerInitUtils.InitSpinner(this.parent, vehicleSpMinVehicle, R.array.minVehicles);
        SpinnerInitUtils.InitSpinner(this.parent, vehicleSpMaxVehicle, R.array.maxVehicles);
        SpinnerInitUtils.InitSpinner(this.parent, datepriceSpShipWithIn, R.array.readyToShip);
        SpinnerInitUtils.InitSpinner(this.parent, searchSpShowCount, R.array.showCountArray);
        SpinnerInitUtils.InitSpinner(this.parent, searchSpSortBy, R.array.sortByArray);
        SpinnerInitUtils.InitSpinner(this.parent, searchSpSortByDirection, R.array.sortDirection);
        SpinnerInitUtils.InitSpinner(this.parent, searchSpThenSortBy, R.array.sortByArray);
        SpinnerInitUtils.InitSpinner(this.parent, searchSpThenSortByDirection, R.array.sortDirection);
        SpinnerInitUtils.InitSpinner(this.parent, searchSpRefreshNewListing, R.array.refreshListingTimeArray);
        SpinnerInitUtils.InitSpinner(this.parent, searchSpHightLight, R.array.highLightNewListings);
    }




}
