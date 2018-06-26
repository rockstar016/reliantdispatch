package com.rock.reliantdispatch.Base;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.androidstudy.networkmanager.Tovuti;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.rock.reliantdispatch.Utils.LoadingDialog;

import java.util.Objects;

/**
 * Created by michalejackson on 3/20/18.
 */

public class BaseActivity extends AppCompatActivity{
    LoadingDialog loadingDialog;
    protected boolean isConnected;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Permissions.check(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE},
                "Reliant Dispatch need to use network connection.", new Permissions.Options()
                        .setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        //do your task
                        CheckNetworkConnection();
                   }
                });
    }

    void CheckNetworkConnection()
    {
        Tovuti.from(this).monitor((connectionType, isConnected, isFast) -> {
            this.isConnected = isConnected;
        });
    }

    @Override
    protected void onStop() {
        Tovuti.from(this).stop();
        super.onStop();
    }

    protected void InitToolbar(Toolbar toolbar, int stringResource) {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(stringResource);
    }

    protected void InitToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    public void SetToolbarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    public void SetToolbarSecondaryTitle(String title){
        getSupportActionBar().setSubtitle(title);
    }

    public void SetToolbarTitle(int stringResource) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(stringResource);
    }

    @UiThread
    public void ShowLoadingDialog(String title)
    {
        if(loadingDialog != null)
            return;
        loadingDialog = LoadingDialog.getInstance(title);
        loadingDialog.show(this.getSupportFragmentManager(), "dialog");
    }

    @UiThread
    public void HideLoadingDialog()
    {
        if(loadingDialog != null)
        {
            loadingDialog.dismiss();
        }
        loadingDialog = null;
    }
}
