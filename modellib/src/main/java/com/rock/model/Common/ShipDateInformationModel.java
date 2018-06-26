package com.rock.model.Common;

import java.io.Serializable;

public class ShipDateInformationModel implements Serializable{
    private boolean isToday;
    private String dateChoose = "";

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    public String getDateChoose() {
        return dateChoose;
    }

    public void setDateChoose(String dateChoose) {
        this.dateChoose = dateChoose;
    }
}
