package com.rock.model.Common;

import java.io.Serializable;

/**
 * Created by rock on 3/24/18.
 */

public class LocationModel implements Serializable{
    private String zipCode;
    private String city;
    private String state;
    private String metroArea;
    public LocationModel()
    {
        zipCode = "";
        city = "";
        state = "";
        metroArea = "";
    }

    public String getMetroArea() {
        return metroArea;
    }

    public void setMetroArea(String metroArea) {
        this.metroArea = metroArea;
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
