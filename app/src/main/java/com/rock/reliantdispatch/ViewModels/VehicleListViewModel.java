package com.rock.reliantdispatch.ViewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rock.model.Shipper.VehicleListingModel;
import com.rock.reliantdispatch.Constants.VehiclesConfig;
import com.rock.reliantdispatch.DataStoreLayer.DataStoreManager;
import com.rock.reliantdispatch.DataStoreLayer.ShipperVehicleListingStoreLayer;

import java.util.ArrayList;
public class VehicleListViewModel extends ViewModel {
    MutableLiveData<ArrayList<VehicleListingModel>> arrayDispListing;
    MutableLiveData<Integer> currentSelectedFilter;
    MutableLiveData<Boolean> isRefreshingSwipe;
    ShipperVehicleListingStoreLayer DataStoreLayer;

    public VehicleListViewModel()
    {
        arrayDispListing = new MutableLiveData<>();
        arrayDispListing.setValue(new ArrayList<>());

        isRefreshingSwipe = new MutableLiveData<>();
        isRefreshingSwipe.setValue(false);

        currentSelectedFilter = new MutableLiveData<>();
        currentSelectedFilter.setValue(VehiclesConfig.VEHICLE_LISTING_TYPE_LISTED);

        DataStoreLayer = DataStoreManager.getInstance().getVehicleListingStoreLayer();
        FillOutVehicleListing();
    }

    public MutableLiveData<Boolean> getIsRefreshingSwipe() {
        return isRefreshingSwipe;
    }

    public MutableLiveData<Integer> getCurrentSelectedFilter() {
        return currentSelectedFilter;
    }

    private void FillOutVehicleListing()
    {
        for(int i = 0 ; i < 30 ; i++)
        {
            VehicleListingModel item = new VehicleListingModel();
            item.setType(i % 8);
        }
    }

    public void LoadVehicleList(int ListType)
    {
        currentSelectedFilter.setValue(ListType);
        refreshDispArray(false);
    }

    public MutableLiveData<ArrayList<VehicleListingModel>> refreshDispArray(boolean isForceRefresh) {
        isRefreshingSwipe.setValue(true);
        arrayDispListing.setValue(new ArrayList<>());
        ArrayList<VehicleListingModel> totalArray = DataStoreLayer.getVehicleListing(isForceRefresh);
        ArrayList<VehicleListingModel> dispArray = new ArrayList<>();
        for(int i = 0 ; i < totalArray.size() ; i++)
        {
            if(totalArray.get(i).getType() == currentSelectedFilter.getValue())
            {
                dispArray.add(totalArray.get(i));
            }
        }
        arrayDispListing.setValue(dispArray);
        isRefreshingSwipe.setValue(false);
        return arrayDispListing;
    }

    public int getStatusCount(int status)
    {
        int count = 0;
        ArrayList<VehicleListingModel> totalArray = DataStoreLayer.getVehicleListing(false);
        for(int i = 0 ; i < totalArray.size() ; i++)
        {
            if(totalArray.get(i).getType() == status)
            {
                count++;
            }
        }
        return count;
    }
}
