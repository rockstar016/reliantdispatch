package com.rock.reliantdispatch.Base;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rock.reliantdispatch.Utils.LoadingDialog;

/**
 * Created by michalejackson on 3/20/18.
 */

public class BaseActivity extends AppCompatActivity{
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void InitToolbar(Toolbar toolbar, int stringResource)
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(stringResource);
    }

    protected void InitToolbar(Toolbar toolbar, String title)
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
    }


    public void ShowLoadingDialog(String title)
    {
        loadingDialog = LoadingDialog.getInstance(title);
        loadingDialog.show(this.getSupportFragmentManager(), "dialog");
    }

    public void HideLoadingDialog()
    {
        if(loadingDialog != null)
        {
            loadingDialog.dismiss();
        }
        loadingDialog = null;
    }
}
