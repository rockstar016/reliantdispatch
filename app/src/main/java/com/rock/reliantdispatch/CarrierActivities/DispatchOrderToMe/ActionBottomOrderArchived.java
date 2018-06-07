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
public class ActionBottomOrderArchived extends ActionBottomBaseFragment<OrderDetailActivity> {
    public ActionBottomOrderArchived() {
        // Required empty public constructor
    }

    public static ActionBottomOrderArchived newInstance() {
        ActionBottomOrderArchived fragment = new ActionBottomOrderArchived();
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
        View rootView = inflater.inflate(R.layout.fragment_action_bottom_archived, container, false);
        return rootView;
    }
}
