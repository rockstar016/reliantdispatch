package com.rock.reliantdispatch.Base;

import android.content.Context;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;

import com.rock.reliantdispatch.ShipperActivities.VehicleListing.DetailVehicleListItemActivity;

public class ActionBottomBaseFragment<T> extends BottomSheetDialogFragment {
    protected T parentActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (T)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parentActivity = null;
    }
}
