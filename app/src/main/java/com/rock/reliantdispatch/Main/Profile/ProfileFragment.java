package com.rock.reliantdispatch.Main.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Accounts.MyAddressBook.AddressBookActivity;
import com.rock.reliantdispatch.Accounts.MyDocuments.MyDocumentActivity;
import com.rock.reliantdispatch.Accounts.MyNotifications.MyNotificationActivity;
import com.rock.reliantdispatch.Accounts.MyProfile.MyProfileActivity;
import com.rock.reliantdispatch.Accounts.ReliantSetting.ReliantSettingActivity;
import com.rock.reliantdispatch.Base.BaseFragment;
import com.rock.reliantdispatch.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {
    Unbinder unbinder;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        parent.SetToolbarTitle(R.string.my_account);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btMyProfile)
    public void onBtMyProfileClicked() {
        Intent i = new Intent(this.parent, MyProfileActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btNotification)
    public void onBtNotificationClicked() {
        Intent i = new Intent(this. parent, MyNotificationActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btAddressBook)
    public void onBtAddressBookClicked() {
        Intent i = new Intent(this.parent, AddressBookActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btSetting)
    public void onBtSettingClicked() {
        Intent i = new Intent(this.parent, ReliantSettingActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btDocument)
    public void onBtDocumentClicked() {
        Intent i = new Intent(this.parent, MyDocumentActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btFeedback)
    public void onBtFeedbackClicked() {
    }

    @OnClick(R.id.btBilling)
    public void onBtBillingClicked() {
    }

    @OnClick(R.id.btFavorite)
    public void onBtFavoriteClicked() {
    }

    @OnClick(R.id.btLogOut)
    public void onBtLogOutClicked() {
    }
}
