package com.rock.reliantdispatch.ShipperActivities.DraftListing;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ShipperActivities.Adapters.DetailVehicleRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentDetailVehicleDraftItem extends Fragment implements BaseRecyclerInterface {
    @BindView(R.id.recyclerVehicle)
    RecyclerView recyclerVehicle;
    @BindView(R.id.btCarrierInfo)
    Button btCarrierInfo;
    @BindView(R.id.btViewGatePass)
    Button btViewGatePass;
    Unbinder unbinder;
    DetailVehicleRecyclerAdapter mVehicleAdapter;
    DetailDraftItemActivity parentActivity;
    public FragmentDetailVehicleDraftItem() {
        // Required empty public constructor
    }

    public static FragmentDetailVehicleDraftItem newInstance() {
        FragmentDetailVehicleDraftItem fragment = new FragmentDetailVehicleDraftItem();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail_vehicle_item, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        btViewGatePass.setVisibility(View.INVISIBLE);
        btCarrierInfo.setText("(Carrier Not Assigned)");
//        mVehicleAdapter = new DetailVehicleRecyclerAdapter(parentActivity, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.parentActivity);
        recyclerVehicle.setLayoutManager(layoutManager);
        recyclerVehicle.setAdapter(mVehicleAdapter);
        recyclerVehicle.setNestedScrollingEnabled(false);
        recyclerVehicle.setHasFixedSize(true);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetailDraftItemActivity) {
            parentActivity = (DetailDraftItemActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parentActivity = null;
    }

    @Override
    public void OnRecyclerItemClick(int position) {
        // car detail information will be displayed here
    }
}
