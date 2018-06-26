package com.rock.reliantdispatch.CustomView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.rock.model.Common.ShipDateInformationModel;
import com.rock.reliantdispatch.R;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by michalejackson on 3/21/18.
 */

public class ShipDateView extends LinearLayout {
    Context context;
    @BindView(R.id.chkShipDateToday)
    CheckBox chkShipDateToday;
    @BindView(R.id.txtShippingDateInformation)
    EditText txtShippingDateInformation;

    ShipDateInformationModel model;

    @OnCheckedChanged(R.id.chkShipDateToday)
    void OnCheckedChange() {
        model.setToday(chkShipDateToday.isChecked());
    }

    @OnClick(R.id.txtShippingDateInformation)
    void OnClickShippingDateInformation(){
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (view, year, monthOfYear, dayOfMonth) -> {
                    txtShippingDateInformation.setText(String.format(Locale.US, "%d/%d/%d", monthOfYear,dayOfMonth, year));
                    model.setDateChoose(String.format(Locale.US, "%d/%d/%d", monthOfYear,dayOfMonth, year));
                },mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public ShipDateView(Context context) {
        super(context);
        InitView(context);
    }

    public ShipDateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        InitView(context);
    }

    public ShipDateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShipDateView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        InitView(context);
    }

    private void InitView(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.custom_list_shipping_information, this);
        ButterKnife.bind(this, view);
    }

    public void setModel(ShipDateInformationModel model) {
        this.model = model;
        chkShipDateToday.setChecked(model.isToday());
        txtShippingDateInformation.setText(model.getDateChoose());
    }

    public ShipDateInformationModel getModel() {
        return model;
    }
}
