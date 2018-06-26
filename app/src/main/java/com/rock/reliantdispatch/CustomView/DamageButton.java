package com.rock.reliantdispatch.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.rock.model.Common.DamageModel;
import com.rock.reliantdispatch.Constants.DamageConfig;
import com.rock.reliantdispatch.R;

import java.util.Objects;

import butterknife.OnClick;

public class DamageButton extends AppCompatButton implements View.OnClickListener{
    private DamageModel dataProvider;
    private IDamageButtonListener parentHandler;

    public DamageButton(Context context) {
        super(context);
        initView();
    }

    public DamageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DamageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    void initView() {
       setBackgroundResource(R.drawable.circle_red_damage_background);
       setTextColor(Color.WHITE);
       setPadding(0,0,0,0);
       setOnClickListener(this);
    }

    public void setParentHandler(IDamageButtonListener parentHandler) {
        this.parentHandler = parentHandler;
    }

    public void setDataProvider(DamageModel model, int parentWidth, int parentHeight){
        int rateX = parentWidth / model.getOriginImageWidth();
        int rateY = parentHeight / model.getOriginImageHeight();
        this.dataProvider = model;
        setText(DamageConfig.damageStringProvider[model.getDamageKind()]);
        setX(model.getxPosition() * rateX - 10);
        setY(model.getyPosition() * rateY - 10);
        setLayoutParams(new FrameLayout.LayoutParams(parentWidth / 18,parentWidth / 18));
        setTextSize(parentWidth / 140);
    }

    public DamageModel getDataProvider() {
        return dataProvider;
    }

    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(getContext()).setTitle("Info")
                .setMessage("Are you sure to remove damage mark?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    Objects.requireNonNull(this.parentHandler).removeDamageButton(this);
                })
                .setNegativeButton("Cancel", ((dialog, which) -> {
                    dialog.dismiss();
                })).show();
    }


    public interface IDamageButtonListener{
        void removeDamageButton(DamageButton v);
    }
}
