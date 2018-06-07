package com.rock.reliantdispatch.Main.Carrier.TruckSpace;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import com.rock.reliantdispatch.Base.BaseFragment;
import com.rock.reliantdispatch.CarrierActivities.TruckSpace.DetailTruckProfileActivity;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.Interface.DirectDispatchInterface;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TruckSpaceFragment extends BaseFragment implements DirectDispatchInterface{


    private static final int ADDTRUCK = 201;
    private static final int ADDDEPARTURE = 202;

    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    TruckSpaceAdapter adapter;
    int count = 0;
    int selectedPosition = 0;

    public TruckSpaceFragment() {
        // Required empty public constructor
    }

    public static TruckSpaceFragment newInstance() {
        TruckSpaceFragment fragment = new TruckSpaceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe_recycler, container, false);
        unbinder = ButterKnife.bind(this, view);

        swipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.primary_dark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                swipeRefreshLayout.setRefreshing(false);
            }, 1500);
        });

        adapter = new TruckSpaceAdapter(parent, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(parent));
        recyclerView.setAdapter(adapter);
        parent.SetToolbarTitle(getString(R.string.my_listed_truck_space));
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_only, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add)
        {
            Intent i = new Intent(this.parent, AddTruckActivity.class);
            i.putExtra(BundleParamConfig.BUNDLE_CREATE_EDIT_BOOL, true);
            startActivity(i);
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void OnRecyclerItemClick(int position) {
        Intent i = new Intent(this.parent, DetailTruckProfileActivity.class);
        startActivity(i);
    }

    @Override
    public void OnRecyclerItemMoreClick(int position, View anchorView) {
    }
}
