package com.rock.reliantdispatch.CarrierActivities.DispatchOrderToMe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.ActionBottomBaseFragment;
import com.rock.reliantdispatch.R;

public class ActionBottomOrderCompleted extends ActionBottomBaseFragment<OrderDetailActivity> {
    public ActionBottomOrderCompleted() {
        // Required empty public constructor
    }

    public static ActionBottomOrderCompleted newInstance() {
        ActionBottomOrderCompleted fragment = new ActionBottomOrderCompleted();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_action_bottom_completed, container, false);
        return rootView;
    }
}
