package com.rock.reliantdispatch.Main.Resource.CarrierService;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.rock.reliantdispatch.Base.BaseFragment;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarrierServiceFragment extends BaseFragment {


    @BindView(R.id.spStateName)
    Spinner spStateName;
    Unbinder unbinder;

    public CarrierServiceFragment() {
        // Required empty public constructor
    }

    public static CarrierServiceFragment newInstance() {
        CarrierServiceFragment fragment = new CarrierServiceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrier, container, false);
        unbinder = ButterKnife.bind(this, view);
        parent.SetToolbarTitle(getString(R.string.carrier_services));
        setHasOptionsMenu(true);
        InitSpinners();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void InitSpinners()
    {
        SpinnerInitUtils.InitSpinner(this. parent, spStateName, R.array.state_names);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
