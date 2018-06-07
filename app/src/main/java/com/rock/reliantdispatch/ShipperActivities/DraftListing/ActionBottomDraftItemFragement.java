package com.rock.reliantdispatch.ShipperActivities.DraftListing;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.ActionBottomBaseFragment;
import com.rock.reliantdispatch.R;

public class ActionBottomDraftItemFragement extends BottomSheetDialogFragment {
    DetailDraftItemActivity parentActivity;
    public ActionBottomDraftItemFragement() {
        // Required empty public constructor
    }

    public static ActionBottomDraftItemFragement newInstance() {
        ActionBottomDraftItemFragement fragment = new ActionBottomDraftItemFragement();
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

        View rootView = inflater.inflate(R.layout.fragment_action_draft_item, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof DetailDraftItemActivity)
        {
            parentActivity = (DetailDraftItemActivity)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parentActivity = null;
    }
}
