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


public class ActionBottomDetailTruckProfile extends ActionBottomBaseFragment<DetailTruckProfileActivity> {
    @BindView(R.id.btDelete)
    Button btDelete;
    @BindView(R.id.btEdit)
    Button btEdit;
    @BindView(R.id.btNewDeparture)
    Button btNewDeparture;
    Unbinder unbinder;

    public ActionBottomDetailTruckProfile() {
        // Required empty public constructor
    }

    public static ActionBottomDetailTruckProfile newInstance() {
        ActionBottomDetailTruckProfile fragment = new ActionBottomDetailTruckProfile();
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
        View rootView = inflater.inflate(R.layout.fragment_action_detail_truck_profile, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btNewDeparture)
    public void onNewDepartureClick()
    {
        if(parentActivity != null)
            parentActivity.GoToNewDeparture();
    }

    @OnClick(R.id.btEdit)
    public void onViewClicked() {
        if(parentActivity != null)
            parentActivity.GoToEditTruckProfile();
    }
}
