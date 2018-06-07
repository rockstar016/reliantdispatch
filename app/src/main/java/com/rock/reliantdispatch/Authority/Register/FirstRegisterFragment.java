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
public class FirstRegisterFragment extends BaseRegisterFragment {

    Unbinder unbinder;
    @BindView(R.id.spBusinessType)
    Spinner spBusinessType;
    @BindView(R.id.spDisplayName)
    Spinner spDisplayName;

    public FirstRegisterFragment() {
        // Required empty public constructor
    }

    public static FirstRegisterFragment newInstance() {
        FirstRegisterFragment firstRegisterFragment = new FirstRegisterFragment();
        Bundle args = new Bundle();
        firstRegisterFragment.setArguments(args);
        return firstRegisterFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        SpinnerInitUtils.InitSpinner(this.getContext(), spBusinessType, R.array.businesstype);
        SpinnerInitUtils.InitSpinner(this.getContext(), spDisplayName, R.array.displayname);
        setHasOptionsMenu(true);
        return view;
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
            Parent.finish();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    @OnClick(R.id.next)
//    public void onViewClicked() {

//    }
}
