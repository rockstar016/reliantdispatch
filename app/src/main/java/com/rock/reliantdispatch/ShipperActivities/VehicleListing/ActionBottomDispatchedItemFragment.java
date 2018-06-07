package com.rock.reliantdispatch.ShipperActivities.VehicleListing;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.ActionBottomBaseFragment;
import com.rock.reliantdispatch.R;
public class ActionBottomDispatchedItemFragment extends ActionBottomBaseFragment<DetailVehicleListItemActivity> {
    public ActionBottomDispatchedItemFragment() {
        // Required empty public constructor
    }

    public static ActionBottomDispatchedItemFragment newInstance() {
        ActionBottomDispatchedItemFragment fragment = new ActionBottomDispatchedItemFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_action_vehicle_dispatched_item, container, false);
        return rootView;
    }

}
