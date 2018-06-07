package com.rock.reliantdispatch.Interface;

import android.view.View;

public interface CarrierTruckSpaceInterface extends DirectDispatchInterface
{
    void OnItemEditAction(int mainPosition, int itemPosition, View anchorView);
    void OnItemDeleteAction(int mainPosition, int itemPosition, View anchorView);
}
