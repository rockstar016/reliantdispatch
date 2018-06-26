package com.rock.model.Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rock on 3/24/18.
 */

public class InspectModel implements Serializable{
    private String filePath;
    private List<DamageModel> damageList;
    public InspectModel(){
        filePath = "";
        damageList = new ArrayList<>();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<DamageModel> getDamageList() {
        return damageList;
    }

    public void setDamageList(List<DamageModel> damageList) {
        this.damageList = damageList;
    }
}
