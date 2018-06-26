package com.rock.reliantdispatch.CommonActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Flash;
import com.rock.reliantdispatch.Constants.BundleParamConfig;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.DialogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InspectionCaptureActivity extends AppCompatActivity{
    @BindView(R.id.btShutter) Button btShutter;
    @BindView(R.id.btBack) ImageButton btBack;
    @BindView(R.id.txtTitle) TextView txtTitle;
    @BindView(R.id.camPreview) CameraView camPreview;
    boolean mCapturingPicture;

    public int mPositionValue = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_inspection_capture);
        ButterKnife.bind(this);
        mPositionValue = getIntent().getIntExtra(BundleParamConfig.BUNDLE_VEHICLE_UPDATE_POSITION, -1);
        camPreview.setFlash(Flash.AUTO);
        camPreview.addCameraListener(new CameraListener() {
            public void onCameraOpened(CameraOptions options) { onOpened(); }
            public void onPictureTaken(byte[] jpeg) { onPicture(jpeg); }
            @Override
            public void onVideoTaken(File video) {
                super.onVideoTaken(video);
            }
        });

    }

    void onOpened(){
    }

    void onPicture(byte[] jpeg) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null){
            Log.d("d", "Error creating media file, check storage permissions: ");
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(jpeg);
            fos.close();
            String uri = Uri.fromFile(pictureFile).toString();
            finishAsSuccess(uri);
            Log.d("d", "d");
        } catch (Exception e) {
            DialogUtils.showOkDialog(this, "Warning", "Failed to save photo file.");
        }
    }

    @OnClick(R.id.btBack)
    void OnClickBack(){
        Intent i = new Intent();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @OnClick(R.id.btShutter)
    void OnClickShutter(){
        if (mCapturingPicture) return;
        mCapturingPicture = true;
        Toast.makeText(this, "Capturing image now", Toast.LENGTH_SHORT).show();
        camPreview.capturePicture();
    }

    @Override
    protected void onResume() {
        super.onResume();
        camPreview.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        camPreview.destroy();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camPreview.destroy();
    }

    void finishAsSuccess(String imgUri){
        Intent i = new Intent();
        i.putExtra(BundleParamConfig.BUNDLE_STRING_PARAM, imgUri);
        i.putExtra(BundleParamConfig.BUNDLE_VEHICLE_UPDATE_POSITION, mPositionValue);
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    private File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        //Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "reliant");
        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        return mediaFile;
    }
}
