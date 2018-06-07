package com.rock.reliantdispatch.DataStoreLayer;

import com.rock.reliantdispatch.Model.Shipper.VehicleListingModel;
import com.rock.reliantdispatch.ViewModels.VehicleListViewModel;

import java.util.ArrayList;

public class ShipperVehicleListingStoreLayer {
    ArrayList<VehicleListingModel> vehicleListing;
    public ArrayList<VehicleListingModel> getVehicleListing(boolean isForceRefresh)
    {
        if(vehicleListing == null)
        {
            vehicleListing = new ArrayList<>();
            fillOutVehicleList();
        }
        if(isForceRefresh)
        {
            vehicleListing.clear();
            fillOutVehicleList();
        }
        return vehicleListing;
    }

    void fillOutVehicleList()
    {
        for(int i = 0 ; i < 30; i++)
        {
            VehicleListingModel model = new VehicleListingModel();
            model.setType(i % 8);
            vehicleListing.add(model);
        }
    }
}
