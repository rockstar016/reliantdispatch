package com.rock.reliantdispatch.DataStoreLayer;

import android.annotation.SuppressLint;
import android.content.Context;

public class DataStoreManager {
    Context context;
    static DataStoreManager mInstance;
    ShipperVehicleListingStoreLayer VehicleListingStoreLayer;
    public static DataStoreManager getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new DataStoreManager();
        }
        return mInstance;
    }

    public DataStoreManager()
    {
        VehicleListingStoreLayer = new ShipperVehicleListingStoreLayer();
    }

    public ShipperVehicleListingStoreLayer getVehicleListingStoreLayer() {
        return VehicleListingStoreLayer;
    }
}
