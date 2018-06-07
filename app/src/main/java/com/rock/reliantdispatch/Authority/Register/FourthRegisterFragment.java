package com.rock.reliantdispatch.Authority.Register;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourthRegisterFragment extends BaseRegisterFragment {
    Unbinder unbinder;

    public FourthRegisterFragment() {
        // Required empty public constructor
    }

    public static FourthRegisterFragment newInstance() {
        FourthRegisterFragment fourthRegisterFragment = new FourthRegisterFragment();
        Bundle args = new Bundle();
        fourthRegisterFragment.setArguments(args);
        return fourthRegisterFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fourth_register, container, false);
        unbinder = ButterKnife.bind(this, view);
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
            Parent.viewPager.setCurrentItem(Parent.viewPager.getCurrentItem() + 1);
        }
        else if(item.getItemId() == R.id.menu_clear)
        {

        }

        return true;
    }
}
