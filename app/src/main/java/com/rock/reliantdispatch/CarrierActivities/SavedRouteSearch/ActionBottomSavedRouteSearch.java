package com.rock.reliantdispatch.CarrierActivities.SavedRouteSearch;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.R;

public class ActionBottomSavedRouteSearch extends BottomSheetDialogFragment {

    public ActionBottomSavedRouteSearch() {
        // Required empty public constructor
    }

    public static ActionBottomSavedRouteSearch newInstance() {
        ActionBottomSavedRouteSearch fragment = new ActionBottomSavedRouteSearch();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_action_saved_search, container, false);
        return rootView;
    }

}
