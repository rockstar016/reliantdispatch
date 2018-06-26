package com.rock.reliantdispatch.CommonActivity.Damage;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.otaliastudios.zoom.ZoomLayout;
import com.rock.model.Common.DamageModel;
import com.rock.model.Common.InspectModel;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.Constants.DamageConfig;
import com.rock.reliantdispatch.CustomView.DamageButton;
import com.rock.reliantdispatch.CustomView.MZoomImageView;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ViewModels.InspectionViewModels.AddDamageViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDamageActivity extends AppCompatActivity implements MZoomImageView.IClickZoomImageView, DamageButton.IDamageButtonListener {
    @BindView(R.id.preview_view)
    MZoomImageView previewView;
    @BindView(R.id.btDone)
    Button btDone;
    @BindView(R.id.btClearAll)
    Button btClearAll;
    @BindView(R.id.toolbarArea)
    FrameLayout toolbarArea;

    @BindView(R.id.damageParentLayout)
    FrameLayout damageParentLayout;
    @BindView(R.id.zoomLayout)
    ZoomLayout zoomLayout;
    @BindView(R.id.zoomLayoutContainer)
    FrameLayout zoomLayoutContainer;
    AddDamageViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_damage);
        mViewModel = ViewModelProviders.of(this).get(AddDamageViewModel.class);
        ButterKnife.bind(this);
        previewView.setClickZoomImageView(this);
        String param = getIntent().getStringExtra(BundleParamConfig.BUNDLE_INSPECTION_MODEL);
        makeInspectionModel(param);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.getDamageList().observe(this, observer -> {
            assert observer != null;
            btClearAll.setVisibility(observer.size() == 0?View.INVISIBLE:View.VISIBLE);
            removeAllButtons();
            for(int i = 0 ; i < observer.size() ; i++){
                addDamageButtonOnUI(observer.get(i));
            }
        });

        mViewModel.getImgPath().observe(this, (String imgPath) -> {
            assert imgPath != null;
            if(imgPath.isEmpty())
                Picasso.get().load(R.drawable.car).into(previewView);
            else
                Picasso.get().load(imgPath).error(R.drawable.car).into(previewView);
        });
    }

    void removeAllButtons(){
        List<View> mChildList = new ArrayList<View>();
        for(int i = 0 ; i < zoomLayoutContainer.getChildCount() ; i++){
            mChildList.add(zoomLayoutContainer.getChildAt(i));
        }
        for(int i = 0 ; i < mChildList.size(); i++){
            if(mChildList.get(i) instanceof DamageButton)
                zoomLayoutContainer.removeView(mChildList.get(i));
        }
    }

    private void makeInspectionModel(String param){
        InspectModel model;
        try {
            Gson gson = new Gson();
            model = gson.fromJson(param, InspectModel.class);
        }
        catch (Exception e) {
            model = new InspectModel();
        }
        if(model == null) model = new InspectModel();
        mViewModel.initDataProvider(model);
    }

    @OnClick(R.id.btDone)
    public void onBtDoneClicked() {
        InspectModel retModel = new InspectModel();
        retModel.setFilePath(mViewModel.getImgPath().getValue());
        retModel.setDamageList(mViewModel.getDamageList().getValue());
        Gson gson = new Gson();
        String jsValue = gson.toJson(retModel);

        Intent retIntent = new Intent();
        retIntent.putExtra(BundleParamConfig.BUNDLE_INSPECTION_MODEL, jsValue);
        setResult(Activity.RESULT_OK, retIntent);
        finish();
    }

    @OnClick(R.id.btClearAll)
    public void onBtClearAllClicked() {
        new AlertDialog.Builder(this)
                .setTitle("Info")
                .setMessage("Do you want to remove all damages?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    mViewModel.clearDamageList();
                    dialog.dismiss();
                })
                .setNegativeButton("No", (dialog, which) -> { dialog.dismiss();})
                .show();
    }

    @Override
    public void OnAddDamage(float posScrX, float posScrY) {
        DamageListFragment fragment = DamageListFragment.newInstance();
        fragment.setParentHandler((pos) -> {
            DamageModel tmp = new DamageModel();
            tmp.setyPosition(posScrY);
            tmp.setxPosition(posScrX);
            tmp.setOriginImageWidth(previewView.getWidth());
            tmp.setOriginImageHeight(previewView.getHeight());
            tmp.setDamageKind(pos);
            mViewModel.addDamageItem(tmp);
        });
        fragment.show(getSupportFragmentManager(), "damageList");
    }

    @UiThread
    void addDamageButtonOnUI(DamageModel model){
        DamageButton newDamage = new DamageButton(this);
        newDamage.setDataProvider(model, previewView.getWidth(), previewView.getHeight());
        newDamage.setParentHandler(this);
        zoomLayoutContainer.addView(newDamage);
    }

    @Override
    public void removeDamageButton(DamageButton v) {
        zoomLayoutContainer.removeView(v);
        mViewModel.removeDamageItem(v.getDataProvider());
    }
}
