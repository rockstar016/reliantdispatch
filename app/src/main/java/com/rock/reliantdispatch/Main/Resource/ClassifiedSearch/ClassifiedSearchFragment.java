package com.rock.reliantdispatch.Main.Resource.ClassifiedSearch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
public class ClassifiedSearchFragment extends BaseFragment {


    @BindView(R.id.spMiles)
    Spinner spMiles;
    @BindView(R.id.spStateName)
    Spinner spStateName;
    @BindView(R.id.spClassiedType)
    Spinner spClassiedType;
    Unbinder unbinder;

    public ClassifiedSearchFragment() {
        // Required empty public constructor
    }

    public static ClassifiedSearchFragment newInstance() {
        ClassifiedSearchFragment fragment = new ClassifiedSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classified_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        InitSpinners();
        return view;
    }

    void InitSpinners()
    {
        SpinnerInitUtils.InitSpinner(this.parent, spMiles, R.array.distance);
        SpinnerInitUtils.InitSpinner(this.parent, spStateName, R.array.state_names);
        SpinnerInitUtils.InitSpinner(this.parent, spClassiedType, R.array.classifiedType);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
