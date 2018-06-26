package com.rock.reliantdispatch.ViewModels;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.net.Uri;
import android.os.AsyncTask;

import com.rock.model.Common.VehicleModel;
import com.rock.model.VinAuditResponseModel.VinResponse;
import com.rock.reliantdispatch.DataStoreLayer.DataStoreManager;

public class LoadVehicleInfoViewModel extends ViewModel {
    MutableLiveData<Boolean> isBusy;
    MutableLiveData<VehicleModel> retVal;
    MutableLiveData<Uri> newCarImage;
    int updatePosition;
    LoadVehicleInfoViewModel() {
        isBusy = new MutableLiveData<>();
        isBusy.setValue(false);
        this.retVal = new MutableLiveData<>();
        this.retVal.setValue(new VehicleModel());
        this.newCarImage = new MutableLiveData<>();
        this.newCarImage.setValue(null);
    }

    public int getUpdatePosition() {
        return updatePosition;
    }

    public void setUpdatePosition(int updatePosition) {
        this.updatePosition = updatePosition;
    }

    public MutableLiveData<Uri> getNewCarImage() {
        return newCarImage;
    }

    public void setNewCarImage(Uri newCarImage) {
        this.newCarImage.setValue(newCarImage);
    }

    public MutableLiveData<VehicleModel> getVehicleModel() {
        return retVal;
    }

    public void setRetVal(VehicleModel retVal) {
        this.retVal.setValue(retVal);
    }

    private void setIsBusy(Boolean value) {
        this.isBusy.setValue(value);
    }

    public MutableLiveData<Boolean> getIsBusy() {
        return isBusy;
    }

    @SuppressLint("StaticFieldLeak")
    public void loadNewVehicleInfo(String vinCode) {
        new AsyncTask<Void, Void, VinResponse>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                setIsBusy(true);
            }

            @Override
            protected VinResponse doInBackground(Void... strings) {
                return DataStoreManager.getInstance().getVinInfoStoreLayer().loadVinData(vinCode);
            }

            @Override
            protected void onPostExecute(VinResponse vinResponse) {
                super.onPostExecute(vinResponse);
                setIsBusy(false);
                VehicleModel mModel = new VehicleModel(vinResponse);
                setRetVal(mModel);
            }
        }.execute();
    }
}
