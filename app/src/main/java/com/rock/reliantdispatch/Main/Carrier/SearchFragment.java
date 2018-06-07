package com.rock.reliantdispatch.Main.Carrier;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.rock.reliantdispatch.Base.BaseFragment;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment implements BottomNavigationView.OnNavigationItemSelectedListener{


    @BindView(R.id.child_content)
    FrameLayout childContent;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;
    Unbinder unbinder;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrier_bottom_nav, container, false);
        unbinder = ButterKnife.bind(this, view);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        if(savedInstanceState == null) addChildFragment(FRAGMENTS.NewSearchFragment);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        MoveToSelectedChildFragment(item);
        return true;
    }
}
