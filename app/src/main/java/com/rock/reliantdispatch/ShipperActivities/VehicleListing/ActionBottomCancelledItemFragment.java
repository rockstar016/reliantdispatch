package com.rock.reliantdispatch.ShipperActivities.VehicleListing;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.ActionBottomBaseFragment;
import com.rock.reliantdispatch.R;


public class ActionBottomCancelledItemFragment extends ActionBottomBaseFragment<DetailVehicleListItemActivity> {
    public ActionBottomCancelledItemFragment() {
        // Required empty public constructor
    }

    public static ActionBottomCancelledItemFragment newInstance() {
        ActionBottomCancelledItemFragment fragment = new ActionBottomCancelledItemFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_action_vehicle_cancelled_item, container, false);
        return rootView;
    }

}
