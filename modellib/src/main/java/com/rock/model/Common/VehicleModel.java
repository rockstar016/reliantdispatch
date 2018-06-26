package com.rock.model.Common;


import com.rock.model.VinAuditResponseModel.VinResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rock on 3/26/18.
 */

public class VehicleModel implements Serializable{
    private String VinCode;
    private String Year, Make, Model;
    private String Trim,VehicleType, DriveType;
    private String Length, Width, Height, CurbWeight;
    private String Running;
    private int Qty;
    private String Color;
    private String LicensePlate;
    private String Lot;
    private String State;
    private String AdditionalInformation;
    private String carPicture;
    private List<InspectModel> inspect;

    public VehicleModel()
    {
        initValues();

    }

    private void initValues()
    {
        this.VinCode = "";
        this.Year = "";
        this.Make = "";
        this.Model = "";
        this.Trim = "";
        this.VehicleType = "";
        this.DriveType = "";
        this.Length = "";
        this.Width = "";
        this.Height = "";
        this.CurbWeight = "";
        this.Running = "Yes";
        this.Qty = 0;
        this.Color = "";
        this.LicensePlate = "";
        this.Lot = "";
        this.State = "";
        this.AdditionalInformation = "";
        this.carPicture = "";
        inspect = new ArrayList<>();
    }

    public VehicleModel(VinResponse responseModel)
    {
        if(!responseModel.getSuccess()) {
            initValues();
        }
        else {
            this.VinCode = responseModel.getAttributes().getVIN();
            this.Year = responseModel.getAttributes().getYear();
            this.Make = responseModel.getAttributes().getMake();
            this.Model = responseModel.getAttributes().getModel();
            this.Trim = responseModel.getAttributes().getTrim();
            this.VehicleType = responseModel.getAttributes().getVehicleType();
            this.DriveType = responseModel.getAttributes().getDrivenWheels();
            this.Length = responseModel.getAttributes().getOverallLength();
            this.Width = responseModel.getAttributes().getOverallWidth();
            this.Height = responseModel.getAttributes().getOverallHeight();
            this.CurbWeight = responseModel.getAttributes().getCurbWeight();
            this.Running = "Yes";
            this.Qty = 0;
            this.Color = "";
            this.LicensePlate = "";
            this.Lot = "";
            this.State = "";
            this.AdditionalInformation = "";
            this.carPicture = "";
            inspect = new ArrayList<>();
        }
    }

    public List<InspectModel> getInspect() {
        return inspect;
    }

    public void setInspect(List<InspectModel> inspect) {
        this.inspect = inspect;
    }

    public String getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(String carPicture) {
        this.carPicture = carPicture;
    }

    public void setVinCode(String vinCode) {
        VinCode = vinCode;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setMake(String make) {
        Make = make;
    }

    public void setModel(String model) {
        Model = model;
    }

    public void setTrim(String trim) {
        Trim = trim;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public void setDriveType(String driveType) {
        DriveType = driveType;
    }

    public void setLength(String length) {
        Length = length;
    }

    public void setWidth(String width) {
        Width = width;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public void setCurbWeight(String curbWeight) {
        CurbWeight = curbWeight;
    }

    public void setRunning(String running) {
        Running = running;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public void setColor(String color) {
        Color = color;
    }

    public void setLicensePlate(String licensePlate) {
        LicensePlate = licensePlate;
    }

    public void setLot(String lot) {
        Lot = lot;
    }

    public void setState(String state) {
        State = state;
    }

    public void setAdditionalInformation(String additionalInformation) {
        AdditionalInformation = additionalInformation;
    }

    public String getMake() {
        return Make;
    }

    public String getVinCode() {
        return VinCode;
    }

    public String getYear() {
        return Year;
    }

    public String getModel() {
        return Model;
    }

    public String getTrim() {
        return Trim;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public String getDriveType() {
        return DriveType;
    }

    public String getLength() {
        return Length;
    }

    public String getWidth() {
        return Width;
    }

    public String getHeight() {
        return Height;
    }

    public String getCurbWeight() {
        return CurbWeight;
    }

    public String getRunning() {
        return Running;
    }

    public int getQty() {
        return Qty;
    }

    public String getColor() {
        return Color;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }

    public String getLot() {
        return Lot;
    }

    public String getState() {
        return State;
    }

    public String getAdditionalInformation() {
        return AdditionalInformation;
    }
}
