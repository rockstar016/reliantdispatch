package com.rock.reliantdispatch.Main.Shipper.DraftList;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
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
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.CreateListing.CreateQuickListingActivity;
import com.rock.reliantdispatch.ShipperActivities.CreateListing.CreateSaveListingActivity;
import com.rock.reliantdispatch.ShipperActivities.DraftListing.DetailDraftItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DraftListFragment extends BaseFragment implements BaseRecyclerInterface {
    DraftListAdapter adapter;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView deliveredRecycler;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    public DraftListFragment() {
        // Required empty public constructor
    }

    public static DraftListFragment newInstance() {
        DraftListFragment fragment = new DraftListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_swipe_recycler, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new DraftListAdapter(parent, this);
        deliveredRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        deliveredRecycler.setAdapter(adapter);
        setHasOptionsMenu(true);
        parent.SetToolbarTitle(getString(R.string.drafts));

        swipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.primary_dark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                swipeRefreshLayout.setRefreshing(false);
            }, 1500);
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.draft_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                gotoCreateSaveListing();
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_delete_items:
                break;
            case R.id.menu_list_items:
                break;
            case R.id.menu_reset_expire:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void gotoCreateSaveListing() {
        Intent mIntent = new Intent(this.parent, CreateSaveListingActivity.class);
        startActivity(mIntent);
    }

    @Override
    public void OnRecyclerItemClick(int position) {
        Intent intent = new Intent(parent, DetailDraftItemActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
