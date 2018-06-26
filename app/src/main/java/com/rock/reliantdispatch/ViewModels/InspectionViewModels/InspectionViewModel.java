package com.rock.reliantdispatch.ViewModels.InspectionViewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rock.model.Common.InspectModel;

import java.util.ArrayList;
import java.util.Objects;

public class InspectionViewModel extends ViewModel {
    private MutableLiveData<Boolean> isEdit;
    MutableLiveData<ArrayList<InspectModel>> inspectProvider;
    MutableLiveData<Integer> currentSelectionItemIndex;
    public InspectionViewModel() {
        isEdit = new MutableLiveData<>();
        isEdit.setValue(false);
        currentSelectionItemIndex = new MutableLiveData<>();
        currentSelectionItemIndex.setValue(-1);
        inspectProvider = new MutableLiveData<>();
        inspectProvider.setValue(new ArrayList<>());

    }

    public MutableLiveData<Integer> getCurrentSelectionItemIndex() {
        return currentSelectionItemIndex;
    }

    public void setCurrentSelectionItemIndex(int currentSelectionItemIndex) {
        this.currentSelectionItemIndex.setValue(currentSelectionItemIndex);
    }

    public MutableLiveData<ArrayList<InspectModel>> getInspectProvider() {
        return inspectProvider;
    }

    public void addNewInspectionItem(String imgUri){
        InspectModel model = new InspectModel();
        model.setFilePath(imgUri);
        model.setDamageList(new ArrayList<>());
        Objects.requireNonNull(inspectProvider.getValue()).add(model);
    }

    public void updateInspectionItem(int position, String imgUri){
        Objects.requireNonNull(inspectProvider.getValue()).get(position).setFilePath(imgUri);
    }

    public void updateDamageList(int position, InspectModel model){
        inspectProvider.getValue().set(position, model);
    }

    public MutableLiveData<Boolean> getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit.setValue(isEdit);
    }
}
