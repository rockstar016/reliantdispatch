package com.rock.reliantdispatch.Main.Carrier.DirectDispatchedToMe;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
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
import com.rock.reliantdispatch.CarrierActivities.DispatchOrderToMe.OrderDetailActivity;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.Main.Shipper.VehicleList.CarrierVehicleFilterListDialogFragment;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DirectDispatchedToMeFragment extends BaseFragment implements BaseRecyclerInterface, OrdersFilterFragment.FilterDialogListener{
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    DirectDispatchToMeAdapter adapter;
    public DirectDispatchedToMeFragment() {
        // Required empty public constructor
    }

    public static DirectDispatchedToMeFragment newInstance() {
        DirectDispatchedToMeFragment fragment = new DirectDispatchedToMeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swipe_recycler, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        swipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.primary_dark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                swipeRefreshLayout.setRefreshing(false);
            }, 1500);
        });
        adapter = new DirectDispatchToMeAdapter(this, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        parent.SetToolbarTitle(R.string.dispatch_orders_to_me);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search_filter, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if(menuId == R.id.menu_search)
        {

        }
        else if(menuId == R.id.menu_filter)
        {
            showFilterDialog();
        }
        return true;
    }

    void showFilterDialog()
    {
        OrdersFilterFragment bottomSheetDialog = OrdersFilterFragment.newInstance();
        bottomSheetDialog.setParentHandler(this);
        bottomSheetDialog.show(getChildFragmentManager(), "filterBottomSheet");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnRecyclerItemClick(int position) {
        Intent i = new Intent(this.getActivity(), OrderDetailActivity.class);
        i.putExtra(OrderDetailActivity.BUNDLE_ORDER_KIND, position);
        startActivity(i);
    }

    @Override
    public void OnClickFilterItem(int position) {

    }
}
