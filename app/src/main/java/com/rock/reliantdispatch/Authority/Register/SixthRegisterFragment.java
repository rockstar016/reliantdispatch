package com.rock.reliantdispatch.Authority.Register;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SixthRegisterFragment extends BaseRegisterFragment {
    Unbinder unbinder;
    @BindView(R.id.spMemberShip)
    Spinner spMemberShip;
    @BindView(R.id.spState)
    Spinner spState;
    @BindView(R.id.spExpirationMonth)
    Spinner spExpirationMonth;
    @BindView(R.id.spExpirationYear)
    Spinner spExpirationYear;

    public SixthRegisterFragment() {
        // Required empty public constructor
    }

    public static SixthRegisterFragment newInstance() {
        SixthRegisterFragment sixthRegisterFragment = new SixthRegisterFragment();
        Bundle args = new Bundle();
        sixthRegisterFragment.setArguments(args);
        return sixthRegisterFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sixth_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        SpinnerInitUtils.InitSpinner(this.getContext(), spMemberShip, R.array.membershipLevel);
        SpinnerInitUtils.InitSpinner(this.getContext(), spState, R.array.state_names);
        SpinnerInitUtils.InitSpinner(this.getContext(), spExpirationMonth, R.array.expirationmonth);
        SpinnerInitUtils.InitSpinner(this.getContext(), spExpirationYear, R.array.expirationyear);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_next_reset, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            Parent.viewPager.setCurrentItem(Parent.viewPager.getCurrentItem() - 1);
        }
        else if(item.getItemId() == R.id.menu_next)
        {
            Parent.finish();
        }
        else if(item.getItemId() == R.id.menu_clear)
        {

        }
        return true;
    }
}
