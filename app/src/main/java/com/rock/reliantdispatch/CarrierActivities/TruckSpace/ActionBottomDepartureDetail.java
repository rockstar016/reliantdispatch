package com.rock.reliantdispatch.CarrierActivities.TruckSpace;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rock.reliantdispatch.Base.ActionBottomBaseFragment;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ActionBottomDepartureDetail extends ActionBottomBaseFragment<TruckDepartureDetailActivity> {
    @BindView(R.id.btEdit)
    Button btEdit;
    Unbinder unbinder;

    public ActionBottomDepartureDetail() {
        // Required empty public constructor
    }

    public static ActionBottomDepartureDetail newInstance() {
        ActionBottomDepartureDetail fragment = new ActionBottomDepartureDetail();
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
        View rootView = inflater.inflate(R.layout.fragment_action_departure_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btEdit)
    public void onEditClicked() {
        if(parentActivity != null)
        {
            parentActivity.GotoEditDeparture();
        }
    }
}
