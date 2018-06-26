package com.rock.reliantdispatch.CommonActivity;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.otaliastudios.zoom.ZoomLayout;
import com.rock.model.Common.DamageModel;
import com.rock.model.Common.InspectModel;
import com.rock.reliantdispatch.CommonActivity.Damage.AddDamageActivity;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.CustomView.DamageButton;
import com.rock.reliantdispatch.CustomView.MZoomImageView;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.ViewModels.InspectionViewModels.InspectionViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InspectionActivity extends AppCompatActivity implements BaseRecyclerInterface {
    @BindView(R.id.preview_view)
    MZoomImageView previewView;
    @BindView(R.id.recyclerPhotos)
    RecyclerView recyclerPhotos;
    InspectionViewModel mViewModel;
    InspectionRecyclerAdapter mAdapter;
    @BindView(R.id.btClose)
    Button btClose;
    @BindView(R.id.btAddPhoto)
    Button btAddPhoto;
    @BindView(R.id.txtMainText)
    TextView txtMainText;
    @BindView(R.id.txtSecondaryText)
    TextView txtSecondaryText;
    @BindView(R.id.toolbarArea)
    FrameLayout toolbarArea;
    @BindView(R.id.btAddDamage)
    Button btAddDamage;
    @BindView(R.id.btRetake)
    Button btRetake;
    @BindView(R.id.zoomLayoutContainer)
    FrameLayout zoomLayoutContainer;
    @BindView(R.id.zoomLayout)
    ZoomLayout zoomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(InspectionViewModel.class);
        boolean isCreateOrEdit = getIntent().getBooleanExtra(BundleParamConfig.BUNDLE_CREATE_EDIT_BOOL, false);
        mViewModel.setIsEdit(isCreateOrEdit);
        recyclerPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new InspectionRecyclerAdapter(this, this);
        recyclerPhotos.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.getIsEdit().observe(this, this::InitEditScreen);
        mViewModel.getInspectProvider().observe(this, mAdapter::updateDataProvider);
        mViewModel.getCurrentSelectionItemIndex().observe(this, this::OnChooseItemSetPart);
    }

    void InitEditScreen(boolean isEdit) {
        int visibility = isEdit ? View.VISIBLE : View.INVISIBLE;
        btAddPhoto.setVisibility(visibility);
        btAddDamage.setVisibility(visibility);
        btRetake.setVisibility(visibility);
    }

    @Override
    public void OnRecyclerItemClick(int position) {
        mViewModel.setCurrentSelectionItemIndex(position);
    }

    @UiThread
    void OnChooseItemSetPart(int position) {
        //todo display image to imageview regarding to position
        if (position >= 0) {
            Picasso.get().load(mViewModel.getInspectProvider().getValue().get(position).getFilePath()).into(previewView);
            refreshButtons(mViewModel.getInspectProvider().getValue().get(position).getDamageList());
            txtMainText.setText("Pickup Condition");
            txtSecondaryText.setText("");
            mAdapter.setChoosePosition(position);
        }
    }

    @UiThread
    void refreshButtons(List<DamageModel> observer)
    {
        for(int i = 0 ; i < observer.size() ; i++){
            DamageButton newDamage = new DamageButton(this);
            newDamage.setDataProvider(observer.get(i), previewView.getWidth(), previewView.getHeight());
            newDamage.setParentHandler(null);
            zoomLayoutContainer.addView(newDamage);
        }
    }

    @OnClick(R.id.btClose)
    public void onBtCloseClicked() {
        finish();
    }

    @OnClick(R.id.btAddPhoto)
    public void onBtAddPhotoClicked() {
        //check permission camera, write external storage
        Permissions.check(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                "Reliant Dispatch need to use camera, storage in order to save photo.", new Permissions.Options()
                        .setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        Intent i = new Intent(InspectionActivity.this, InspectionCaptureActivity.class);
                        startActivityForResult(i, BundleParamConfig.ACTIVITY_REQUEST_ADD_INSPECT_PICTURE);
                    }
                });
    }

    @OnClick(R.id.btAddDamage)
    public void onBtAddDamageClicked() {
        //go to add damage activity
        if (mAdapter.getChoosePosition() > -1) {
            Intent i = new Intent(this, AddDamageActivity.class);
            Gson gson = new Gson();
            String jsonValue = gson.toJson(Objects.requireNonNull(mViewModel.getInspectProvider().getValue()).get(mAdapter.getChoosePosition()));
            i.putExtra(BundleParamConfig.BUNDLE_INSPECTION_MODEL, jsonValue);
            startActivityForResult(i, BundleParamConfig.ACTIVITY_REQUEST_ADD_DAMAGE);
        }
    }

    @OnClick(R.id.btRetake)
    public void onBtRetakeClicked() {
        //go to take photo with existing item information
        Permissions.check(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                "Reliant Dispatch need to use camera, storage in order to save photo.", new Permissions.Options()
                        .setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        //do your task
                        if (mAdapter.getChoosePosition() > -1) {
                            Intent i = new Intent(InspectionActivity.this, InspectionCaptureActivity.class);
                            i.putExtra(BundleParamConfig.BUNDLE_VEHICLE_UPDATE_POSITION, mAdapter.getChoosePosition());
                            startActivityForResult(i, BundleParamConfig.ACTIVITY_REQUEST_RETAKE_INSPECT_PICTURE);
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BundleParamConfig.ACTIVITY_REQUEST_ADD_INSPECT_PICTURE && resultCode == Activity.RESULT_OK) {
            String fileUri = data.getStringExtra(BundleParamConfig.BUNDLE_STRING_PARAM);
            mViewModel.addNewInspectionItem(fileUri);
            return;
        }

        if (requestCode == BundleParamConfig.ACTIVITY_REQUEST_RETAKE_INSPECT_PICTURE && resultCode == Activity.RESULT_OK) {
            String fileUri = data.getStringExtra(BundleParamConfig.BUNDLE_STRING_PARAM);
            int updatePosition = data.getIntExtra(BundleParamConfig.BUNDLE_VEHICLE_UPDATE_POSITION, -1);
            if (updatePosition > -1) {
                mViewModel.updateInspectionItem(updatePosition, fileUri);
                return;
            }
        }

        if (requestCode == BundleParamConfig.ACTIVITY_REQUEST_ADD_DAMAGE && resultCode == Activity.RESULT_OK) {
            String strInspect = data.getStringExtra(BundleParamConfig.BUNDLE_INSPECTION_MODEL);
            Gson gson = new Gson();
            InspectModel model = gson.fromJson(strInspect, InspectModel.class);
            mViewModel.updateDamageList(mViewModel.getCurrentSelectionItemIndex().getValue(), model);
            return;
        }
    }
}
