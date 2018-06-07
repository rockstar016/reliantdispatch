package com.rock.reliantdispatch.ShipperActivities.VehicleListing;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.ActionBottomBaseFragment;
import com.rock.reliantdispatch.R;
public class ActionBottomAcceptedItemFragment extends ActionBottomBaseFragment<DetailVehicleListItemActivity> {



    public ActionBottomAcceptedItemFragment() {
        // Required empty public constructor
    }

    public static ActionBottomAcceptedItemFragment newInstance() {
        ActionBottomAcceptedItemFragment fragment = new ActionBottomAcceptedItemFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_action_vehicle_accepted_item, container, false);
        return rootView;
    }

}
