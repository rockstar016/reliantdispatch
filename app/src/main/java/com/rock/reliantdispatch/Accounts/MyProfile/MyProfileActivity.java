package com.rock.reliantdispatch.Accounts.MyProfile;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyProfileActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spDisplayName)
    Spinner spDisplayName;
    @BindView(R.id.spState)
    Spinner spState;
    @BindView(R.id.spTimezone)
    Spinner spTimezone;
    @BindView(R.id.spHereAboutUs)
    Spinner spHereAboutUs;
    @BindView(R.id.spContactMethod)
    Spinner spContactMethod;
    @BindView(R.id.spAgentState)
    Spinner spAgentState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_my_profile);
        InitSpinner();
    }

    void InitSpinner()
    {
        SpinnerInitUtils.InitSpinner(this, spDisplayName, R.array.displayname);
        SpinnerInitUtils.InitSpinner(this, spState, R.array.state_names);
        SpinnerInitUtils.InitSpinner(this, spTimezone, R.array.timezone);
        SpinnerInitUtils.InitSpinner(this, spHereAboutUs, R.array.hereaboutus);
        SpinnerInitUtils.InitSpinner(this, spContactMethod, R.array.contactmethod);
        SpinnerInitUtils.InitSpinner(this, spAgentState, R.array.state_names);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return true;
    }
}
