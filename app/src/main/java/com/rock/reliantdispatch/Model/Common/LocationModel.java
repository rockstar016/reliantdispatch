package com.rock.reliantdispatch.Model.Common;

/**
 * Created by rock on 3/24/18.
 */

public class LocationModel {
    private String zipCode;
    private String city;
    private String state;

    public LocationModel()
    {
        zipCode = "";
        city = "";
        state = "";
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
