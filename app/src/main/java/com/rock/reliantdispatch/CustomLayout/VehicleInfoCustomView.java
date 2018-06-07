package com.rock.reliantdispatch.CustomLayout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by michalejackson on 3/21/18.
 */

public class VehicleInfoCustomView extends LinearLayout {
    Context context;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.make)
    TextView make;
    @BindView(R.id.model)
    TextView model;
    @BindView(R.id.damageCount)
    TextView damageCount;
    @BindView(R.id.carDetail)
    ImageView carDetail;
    @BindView(R.id.lotTxt)
    TextView lotTxt;
    @BindView(R.id.vinTxt)
    TextView vinTxt;
    @BindView(R.id.detailBtn)
    Button detailBtn;
    @BindView(R.id.inspectionBtn)
    Button inspectionBtn;

    public VehicleInfoCustomView(Context context) {
        super(context);
        this.context = context;
        InitView();
    }

    public VehicleInfoCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VehicleInfoCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VehicleInfoCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void InitView() {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_vehicle_info, this);
        ButterKnife.bind(this, view);
    }
}
