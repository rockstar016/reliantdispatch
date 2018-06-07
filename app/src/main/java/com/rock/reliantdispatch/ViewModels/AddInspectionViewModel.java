package com.rock.reliantdispatch.ViewModels;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.MainThread;

import com.rock.reliantdispatch.Https.VinAuditRepository;
import com.rock.reliantdispatch.Model.Common.VehicleModel;
import com.rock.reliantdispatch.Model.VinAuditResponseModel.VinResponse;

import java.util.ArrayList;

public class AddInspectionViewModel extends ViewModel {

    MutableLiveData<Boolean> isVinApiConsuming;
    MutableLiveData<ArrayList<VehicleModel>> vihicleList;

    public AddInspectionViewModel() {
        isVinApiConsuming = new MutableLiveData<>();
        vihicleList = new MutableLiveData<>();
        isVinApiConsuming.setValue(false);
        vihicleList.setValue(new ArrayList<>());
    }


    public void AddVehicleInformation(VehicleModel model)
    {
        ArrayList<VehicleModel> currentVehicleList = vihicleList.getValue();
        currentVehicleList.add(model);
        vihicleList.setValue(currentVehicleList);
    }

    @SuppressLint("StaticFieldLeak")
    public void LoadVinDataByVinCode(String vinCode)
    {
        isVinApiConsuming.setValue(true);
        new AsyncTask<Void, Void, MutableLiveData<VinResponse>>(){
            @Override
            protected MutableLiveData<VinResponse> doInBackground(Void... voids) {
                MutableLiveData<VinResponse> responseModel = VinAuditRepository.getVinResult(vinCode);
                return responseModel;
            }

            @Override
            protected void onPostExecute(MutableLiveData<VinResponse> aVoid) {
                super.onPostExecute(aVoid);
                SetData(aVoid);
            }

            @MainThread
            void SetData(MutableLiveData<VinResponse> model)
            {
                if(model.getValue() != null && model.getValue().getError().isEmpty())
                {
                    ArrayList<VehicleModel> currentVehicleList = vihicleList.getValue();
                    currentVehicleList.add(new VehicleModel(model.getValue()));
                    vihicleList.setValue(currentVehicleList);
                }
                isVinApiConsuming.setValue(false);
            }
        }.execute();
    }

    public LiveData<Boolean> IsVinApiConsuming()
    {
        if(isVinApiConsuming == null) {
            isVinApiConsuming = new MutableLiveData<>();
            isVinApiConsuming.setValue(false);
        }
        return isVinApiConsuming;
    }

    public LiveData<ArrayList<VehicleModel>> getVihicleArrayList()
    {
        if(vihicleList == null)
        {
            vihicleList = new MutableLiveData<>();
            vihicleList.setValue(new ArrayList<>());
        }
        return vihicleList;
    }
}
