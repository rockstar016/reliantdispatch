package com.rock.reliantdispatch.Accounts.MyDocuments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDocumentActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btUsdotSetView)
    Button btUsdotSetView;
    @BindView(R.id.btUsdotView)
    Button btUsdotView;
    @BindView(R.id.btUsdotUpload)
    Button btUsdotUpload;
    @BindView(R.id.btW9SetView)
    Button btW9SetView;
    @BindView(R.id.btW9View)
    Button btW9View;
    @BindView(R.id.btW9Upload)
    Button btW9Upload;
    @BindView(R.id.btCLISetView)
    Button btCLISetView;
    @BindView(R.id.btCLIView)
    Button btCLIView;
    @BindView(R.id.btCLIUpload)
    Button btCLIUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_document);
        ButterKnife.bind(this);
        InitToolbar(toolbar, R.string.title_activity_my_document);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return true;
    }

    @OnClick({R.id.btCLISetView, R.id.btUsdotSetView, R.id.btW9SetView})
    void OnClickSetView()
    {
        Intent i = new Intent(this, ViewDocumentSetActivity.class);
        startActivity(i);
    }

    @OnClick({R.id.btCLIUpload, R.id.btUsdotUpload, R.id.btW9Upload})
    void OnClickUpload()
    {
        Intent i = new Intent(this, UploadDocumentActivity.class);
        startActivity(i);
    }
}
