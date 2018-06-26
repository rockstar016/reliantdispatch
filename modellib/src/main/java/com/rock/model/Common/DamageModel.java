package com.rock.model.Common;

import java.io.Serializable;

public class DamageModel implements Serializable{
    private int damageKind;
    /**
     * It is the ration of width:pointX, height:pointY
     */
    private float xPosition, yPosition;
    private int originImageWidth, originImageHeight;

    public int getOriginImageWidth() {
        return originImageWidth;
    }

    public void setOriginImageWidth(int originImageWidth) {
        this.originImageWidth = originImageWidth;
    }

    public int getOriginImageHeight() {
        return originImageHeight;
    }

    public void setOriginImageHeight(int originImageHeight) {
        this.originImageHeight = originImageHeight;
    }

    public DamageModel()
    {
        damageKind = -1;
        xPosition = -1;
        yPosition  = -1;
    }
    public int getDamageKind() {
        return damageKind;
    }

    public void setDamageKind(int damageKind) {
        this.damageKind = damageKind;
    }

    public float getxPosition() {
        return xPosition;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }
}
