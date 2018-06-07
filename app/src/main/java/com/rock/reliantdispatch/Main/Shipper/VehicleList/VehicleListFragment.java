package com.rock.reliantdispatch.Main.Shipper.VehicleList;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rock.reliantdispatch.Base.BaseFragment;
import com.rock.reliantdispatch.Constants.VehiclesConfig;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.Model.Shipper.VehicleListingModel;
import com.rock.reliantdispatch.ShipperActivities.CreateListing.CreateQuickListingActivity;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.VehicleListing.DetailVehicleListItemActivity;
import com.rock.reliantdispatch.ViewModels.VehicleListViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleListFragment extends BaseFragment implements BaseRecyclerInterface{

    VehicleListAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView activeRecycler;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    VehicleListViewModel mListViewModel;

    public VehicleListFragment() {
        // Required empty public constructor
    }

    public static VehicleListFragment newInstance() {
        VehicleListFragment fragment = new VehicleListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_swipe_recycler, container, false);
        unbinder = ButterKnife.bind(this, view);
        mListViewModel = ViewModelProviders.of(this).get(VehicleListViewModel.class);
        mListViewModel.LoadVehicleList(VehiclesConfig.VEHICLE_LISTING_TYPE_LISTED);
        adapter = new VehicleListAdapter(parent, this);
        activeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        activeRecycler.setAdapter(adapter);
        setHasOptionsMenu(true);
        parent.SetToolbarTitle(getString(R.string.vehicles));
        swipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.primary_dark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mListViewModel.refreshDispArray(false);
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_search_filter, menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        mListViewModel.refreshDispArray(true).observe(this, vehicleListingModels -> {
              adapter.setDataProvider(vehicleListingModels);
        });
        mListViewModel.getIsRefreshingSwipe().observe(this, isRefreshing->{
            if(!swipeRefreshLayout.isRefreshing() && isRefreshing)
                swipeRefreshLayout.setRefreshing(true);
            else if(swipeRefreshLayout.isRefreshing() && !isRefreshing)
                swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(parent, CreateQuickListingActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_filter:
                ShowFilterBottomSheetDialog();
                break;
            case R.id.menu_search:
                Toast.makeText(parent, "search", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @UiThread
    void ShowFilterBottomSheetDialog()
    {
        CarrierVehicleFilterListDialogFragment bottomSheetDialog = CarrierVehicleFilterListDialogFragment.newInstance();
        bottomSheetDialog.setmViewModel(mListViewModel);
        bottomSheetDialog.show(getChildFragmentManager(), "filterBottomSheet");
    }

    @Override
    public void OnRecyclerItemClick(int position) {
        Intent intent = new Intent(parent, DetailVehicleListItemActivity.class);
        intent.putExtra(DetailVehicleListItemActivity.VEHICLE_ITEM_TYPE, position);
        startActivity(intent);
    }
}
