package com.rock.reliantdispatch.CarrierActivities.DispatchOrderToMe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.ActionBottomBaseFragment;
import com.rock.reliantdispatch.R;

public class ActionBottomOrderDelivered extends ActionBottomBaseFragment<OrderDetailActivity> {
    public ActionBottomOrderDelivered() {
        // Required empty public constructor
    }

    public static ActionBottomOrderDelivered newInstance() {
        ActionBottomOrderDelivered fragment = new ActionBottomOrderDelivered();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_action_bottom_delivered, container, false);
        return rootView;
    }

}
