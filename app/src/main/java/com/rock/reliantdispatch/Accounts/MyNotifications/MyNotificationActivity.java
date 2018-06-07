package com.rock.reliantdispatch.Accounts.MyNotifications;

import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.SpinnerInitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNotificationActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spNewPostCarrier1)
    Spinner spNewPostCarrier1;
    @BindView(R.id.spNewPostCarrier2)
    Spinner spNewPostCarrier2;
    @BindView(R.id.spNewPostCarrier3)
    Spinner spNewPostCarrier3;
    @BindView(R.id.spNewPostCarrier4)
    Spinner spNewPostCarrier4;
    @BindView(R.id.spOfferCarrier1)
    Spinner spOfferCarrier1;
    @BindView(R.id.spOfferCarrier2)
    Spinner spOfferCarrier2;
    @BindView(R.id.spOfferCarrier3)
    Spinner spOfferCarrier3;
    @BindView(R.id.spOfferCarrier4)
    Spinner spOfferCarrier4;
    @BindView(R.id.spDispatchCarrier1)
    Spinner spDispatchCarrier1;
    @BindView(R.id.spDispatchCarrier2)
    Spinner spDispatchCarrier2;
    @BindView(R.id.spDispatchCarrier3)
    Spinner spDispatchCarrier3;
    @BindView(R.id.spDispatchCarrier4)
    Spinner spDispatchCarrier4;
    @BindView(R.id.spFeedbackCarrier1)
    Spinner spFeedbackCarrier1;
    @BindView(R.id.spFeedbackCarrier2)
    Spinner spFeedbackCarrier2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notification);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_my_notification);
        InitSpinners();
    }

    void InitSpinners()
    {
        SpinnerInitUtils.InitSpinner(this, spNewPostCarrier1, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spNewPostCarrier2, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spNewPostCarrier3, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spNewPostCarrier4, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spDispatchCarrier1, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spDispatchCarrier2, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spDispatchCarrier3, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spDispatchCarrier4, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spOfferCarrier1, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spOfferCarrier2, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spOfferCarrier3, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spOfferCarrier4, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spFeedbackCarrier1, R.array.carrier_names);
        SpinnerInitUtils.InitSpinner(this, spFeedbackCarrier2, R.array.carrier_names);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId)
        {
            case android.R.id.home:
                BackToParent();
                break;
            case R.id.menu_save:
                SaveNotificationSettings();
                break;
        }
        return true;
    }

    @UiThread
    void BackToParent()
    {
        finish();
    }

    @UiThread
    void SaveNotificationSettings()
    {
        finish();
    }
}
