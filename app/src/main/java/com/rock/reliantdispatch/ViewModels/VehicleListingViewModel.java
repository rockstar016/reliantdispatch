package com.rock.reliantdispatch.ViewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rock.model.Common.LocationModel;
import com.rock.model.Common.ShipDateInformationModel;
import com.rock.model.Common.VehicleModel;

import java.util.ArrayList;
import java.util.Objects;

public class VehicleListingViewModel extends ViewModel {
    private MutableLiveData<ArrayList<VehicleModel>> vehicleModelArrayList;
    private MutableLiveData<LocationModel> pickUpLocation, deliveryLocation;
    private MutableLiveData<String> orderId, trailerType;
    private MutableLiveData<ShipDateInformationModel> shipDateInformation;
    public VehicleListingViewModel() {
        vehicleModelArrayList = new MutableLiveData<>();
        vehicleModelArrayList.setValue(new ArrayList<>());
        pickUpLocation = new MutableLiveData<>();
        pickUpLocation.setValue(new LocationModel());
        deliveryLocation = new MutableLiveData<>();
        deliveryLocation.setValue(new LocationModel());
        orderId  = new MutableLiveData<>();
        orderId.setValue("");
        trailerType = new MutableLiveData<>();
        trailerType.setValue("");
        shipDateInformation = new MutableLiveData<>();
        shipDateInformation.setValue(new ShipDateInformationModel());
    }

    public MutableLiveData<ShipDateInformationModel> getShipDateInformation() {
        return shipDateInformation;
    }

    public void setShipDateInformation(ShipDateInformationModel shipDateInformation) {
        this.shipDateInformation.setValue(shipDateInformation);
    }

    public MutableLiveData<String> getTrailerType() {
        return trailerType;
    }

    public void setTrailerType(String trailerType) {
        this.trailerType.setValue(trailerType);
    }

    public MutableLiveData<String> getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId.setValue(orderId);
    }

    public void setPickUpLocation(LocationModel pickUpLocation) {
        this.pickUpLocation.setValue(pickUpLocation);
    }

    public void setDeliveryLocation(LocationModel deliveryLocation) {
        this.deliveryLocation.setValue(deliveryLocation);
    }

    public MutableLiveData<LocationModel> getPickUpLocation() {
        return pickUpLocation;
    }

    public MutableLiveData<LocationModel> getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setVehicleModelArrayList(MutableLiveData<ArrayList<VehicleModel>> vehicleModelArrayList) {
        this.vehicleModelArrayList = vehicleModelArrayList;
    }

    public MutableLiveData<ArrayList<VehicleModel>> getVehicleModelArrayList() {
        return vehicleModelArrayList;
    }

    public void addItemToVehidelArrayList(@NonNull VehicleModel model) {
        Objects.requireNonNull(vehicleModelArrayList.getValue()).add(model);
    }

    public void updateItemInVehicleArrayList(@NonNull VehicleModel model, int position){
        Objects.requireNonNull(vehicleModelArrayList.getValue()).remove(position);
        vehicleModelArrayList.getValue().add(position, model);
    }

}
