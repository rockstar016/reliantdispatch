package com.rock.reliantdispatch.Model.Common;

/**
 * Created by rock on 3/24/18.
 */

public class InspectModel {
    String loadId;
    LocationModel fromLocation, toLocation;

    public String getLoadId() {
        return loadId;
    }

    public void setLoadId(String loadId) {
        this.loadId = loadId;
    }

    public LocationModel getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(LocationModel fromLocation) {
        this.fromLocation = fromLocation;
    }

    public LocationModel getToLocation() {
        return toLocation;
    }

    public void setToLocation(LocationModel toLocation) {
        this.toLocation = toLocation;
    }
}
