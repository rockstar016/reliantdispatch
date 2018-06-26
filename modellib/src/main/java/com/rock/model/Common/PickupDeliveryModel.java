package com.rock.model.Common;

/**
 * Created by michalejackson on 3/21/18.
 */

public class PickupDeliveryModel {
    LocationModel location;
    String phone1, phone2;

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
}
