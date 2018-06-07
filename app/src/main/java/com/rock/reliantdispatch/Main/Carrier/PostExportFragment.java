package com.rock.reliantdispatch.Main.Carrier;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

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
public class PostExportFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.pickupCityGroup) MultiSpinner pickupCityGroup;
    @BindView(R.id.deliveryCityGroup) MultiSpinner deliveryCityGroup;
    @BindView(R.id.viewExportListing) Button viewExportListing;

    @BindView(R.id.spListing) Spinner spListing;
    @BindView(R.id.spReadyToShip) Spinner spReadyToShip;
    @BindView(R.id.spSortBy) Spinner spSortBy;
    @BindView(R.id.spThenSortBy) Spinner spThenSortBy;

    public PostExportFragment() {
        // Required empty public constructor
    }

    public static PostExportFragment newInstance() {
        PostExportFragment fragment = new PostExportFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_export, container, false);
        unbinder = ButterKnife.bind(this, view);
        Setup();
        parent.SetToolbarTitle(getString(R.string.export));
        return view;
    }

    private void Setup() {
        SpinnerInitUtils.InitMultipleSpinner(this.parent, pickupCityGroup, R.array.cityGroup, selected ->{});
        SpinnerInitUtils.InitMultipleSpinner(this.parent, deliveryCityGroup, R.array.cityGroup, selected->{});

        SpinnerInitUtils.InitSpinner(this.parent, spListing, R.array.listings);
        SpinnerInitUtils.InitSpinner(this.parent, spReadyToShip, R.array.readyToShip);
        SpinnerInitUtils.InitSpinner(this.parent, spSortBy, R.array.sortBy);
        SpinnerInitUtils.InitSpinner(this.parent, spThenSortBy, R.array.sortBy);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
