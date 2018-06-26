package com.rock.reliantdispatch.ViewModels.InspectionViewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rock.model.Common.DamageModel;
import com.rock.model.Common.InspectModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddDamageViewModel extends ViewModel {
    MutableLiveData<List<DamageModel>> damageList;
    MutableLiveData<String> imgPath;
    public AddDamageViewModel() {
        imgPath = new MutableLiveData<>();
        imgPath.setValue("");
        damageList = new MutableLiveData<>();
        damageList.setValue(new ArrayList<DamageModel>());
    }

    public void initDataProvider(InspectModel model){
        imgPath.setValue(model.getFilePath());
        addDamageItems(model.getDamageList());
    }

    public MutableLiveData<String> getImgPath() {
        return imgPath;
    }

    public MutableLiveData<List<DamageModel>> getDamageList() {
        return damageList;
    }

    public void clearDamageList(){
        damageList.setValue(new ArrayList<>());
    }

    private void addDamageItems(List<DamageModel> arrayList){
            damageList.setValue(arrayList);
    }

    public void addDamageItem(DamageModel item){
        List<DamageModel> mTmp = damageList.getValue();
        mTmp.add(item);
        damageList.setValue(mTmp);
    }

    public void removeDamageItem(DamageModel item){
        List<DamageModel> mTmp = damageList.getValue();
        for(int i = 0 ; i < mTmp.size() ; i++){
            DamageModel tmpModel = mTmp.get(i);
            if(tmpModel.getDamageKind() == item.getDamageKind() && tmpModel.getyPosition() == item.getyPosition() && tmpModel.getxPosition() == item.getxPosition()){
                mTmp.remove(i);
                break;
            }
        }
        damageList.setValue(mTmp);
    }
}
