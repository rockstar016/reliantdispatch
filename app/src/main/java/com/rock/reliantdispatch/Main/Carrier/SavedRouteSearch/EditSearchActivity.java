package com.rock.reliantdispatch.Main.Carrier.SavedRouteSearch;

import android.os.Bundle;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditSearchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_search);
        ButterKnife.bind(this);

    }

}
