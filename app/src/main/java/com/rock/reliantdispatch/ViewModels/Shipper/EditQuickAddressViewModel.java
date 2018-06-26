package com.rock.reliantdispatch.ViewModels.Shipper;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.rock.model.Common.LocationModel;
import com.rock.model.GooglePlaceDetail.GooglePlaceDetailModel;
import com.rock.reliantdispatch.DataStoreLayer.DataStoreManager;


public class EditQuickAddressViewModel extends ViewModel {
    private MutableLiveData<String> zipCode, city, state;
    private MutableLiveData<Boolean> isBusy;
    public EditQuickAddressViewModel() {
        zipCode = new MutableLiveData<>(); zipCode.setValue("");
        city = new MutableLiveData<>(); city.setValue("");
        state = new MutableLiveData<>(); state.setValue("");
        isBusy = new MutableLiveData<>(); isBusy.setValue(false);
    }

    public MutableLiveData<Boolean> getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(Boolean isBusy) {
        this.isBusy.setValue(isBusy);
    }

    public MutableLiveData<String> getCity() {
        return city;
    }

    public void setCity(String value) {
        this.city.setValue(value);
    }

    public MutableLiveData<String> getZipCode() {
        return zipCode;
    }

    public void setZipCode(String value) {
        this.zipCode.setValue(value);
    }

    public MutableLiveData<String> getState() {
        return state;
    }

    public void setState(String value){
        this.state.setValue(value);
    }

    public void initValues(LocationModel model) {
        if(model == null){
            this.setCity("");
            this.setState("");
            this.setZipCode("");
        }
        else{
            this.setCity(model.getCity());
            this.setZipCode(model.getZipCode());
            this.setState(model.getState());
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void getAddressFromGoogle(String placeId){
        new AsyncTask<Void, Void, GooglePlaceDetailModel>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                setIsBusy(true);
            }

            @Override
            protected GooglePlaceDetailModel doInBackground(Void... strings) {
                return DataStoreManager.getInstance().getLocationDataStoreLayer().loadPlaceDetailFromGoogle(placeId);
            }

            @Override
            protected void onPostExecute(GooglePlaceDetailModel value) {
                super.onPostExecute(value);
//                try {
//                    if (value.getStatus().equalsIgnoreCase(GooglePlaceDetailModel.RESULT_OK)) {
//                        for (int i = 0; i < value.getResult().getAddressComponents().size(); i++) {
//                            AddressComponent addrComps = value.getResult().getAddressComponents().get(i);
//                            if (addrComps.getTypes().get(0).equalsIgnoreCase(POSTAL_CODE)) {
//                                setZipCode(addrComps.getLongName());
//                            } else if (addrComps.getTypes().get(0).equalsIgnoreCase(CITY_CODE)) {
//                                setCity(addrComps.getLongName());
//                            } else if (addrComps.getTypes().get(0).equalsIgnoreCase(STATE_CODE)) {
//                                setState(addrComps.getLongName());
//                            }
//                        }
//                    } else {
//                        setGoogleApiError(true);
//                    }
//                }
//                catch (Exception e)
//                {
//
//                }
                setIsBusy(false);
            }
        }.execute();
    }

}
