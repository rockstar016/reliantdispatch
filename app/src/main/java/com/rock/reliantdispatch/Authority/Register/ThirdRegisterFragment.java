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
public class ThirdRegisterFragment extends BaseRegisterFragment {

    Unbinder unbinder;
    @BindView(R.id.spContactMethod)
    Spinner spContactMethod;
    @BindView(R.id.spHereAboutUs)
    Spinner spHereAboutUs;

    public ThirdRegisterFragment() {
        // Required empty public constructor
    }

    public static ThirdRegisterFragment newInstance() {
        ThirdRegisterFragment thirdRegisterFragment = new ThirdRegisterFragment();
        Bundle args = new Bundle();
        thirdRegisterFragment.setArguments(args);
        return thirdRegisterFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        SpinnerInitUtils.InitSpinner(this.getContext(), spContactMethod, R.array.contactmethod);
        SpinnerInitUtils.InitSpinner(this.getContext(), spHereAboutUs , R.array.hereaboutus);
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
//    @OnClick({R.id.goBack, R.id.next})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.goBack:
//                Parent.viewPager.setCurrentItem(Parent.viewPager.getCurrentItem() - 1);
//                break;
//            case R.id.next:
//                Parent.viewPager.setCurrentItem(Parent.viewPager.getCurrentItem() + 1);
//                break;
//        }
//    }
}
