package com.rock.reliantdispatch.DataStoreLayer;

public class DataStoreManager {
    private static DataStoreManager mInstance;
    private ShipperVehicleListingStoreLayer VehicleListingStoreLayer;
    private VinInfoStoreLayer vinInfoStoreLayer;
    private LocationDataStoreLayer locationDataStoreLayer;
    public static DataStoreManager getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new DataStoreManager();
        }
        return mInstance;
    }

    private DataStoreManager()
    {
        vinInfoStoreLayer = new VinInfoStoreLayer();
        VehicleListingStoreLayer = new ShipperVehicleListingStoreLayer();
        locationDataStoreLayer = new LocationDataStoreLayer();
    }

    public VinInfoStoreLayer getVinInfoStoreLayer() {
        return vinInfoStoreLayer;
    }

    public ShipperVehicleListingStoreLayer getVehicleListingStoreLayer() {
        return VehicleListingStoreLayer;
    }

    public LocationDataStoreLayer getLocationDataStoreLayer() {
        return locationDataStoreLayer;
    }
}
