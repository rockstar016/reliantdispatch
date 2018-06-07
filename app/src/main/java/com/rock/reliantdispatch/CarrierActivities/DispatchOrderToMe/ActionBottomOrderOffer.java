package com.rock.reliantdispatch.CarrierActivities.DispatchOrderToMe;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.ActionBottomBaseFragment;
import com.rock.reliantdispatch.R;

public class ActionBottomOrderOffer extends ActionBottomBaseFragment<OrderDetailActivity> {

    public ActionBottomOrderOffer() {
        // Required empty public constructor
    }

    public static ActionBottomOrderOffer newInstance() {
        ActionBottomOrderOffer fragment = new ActionBottomOrderOffer();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_action_order_offer, container, false);
        return rootView;
    }
}
