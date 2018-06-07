package com.rock.reliantdispatch.ShipperActivities.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;
public class DetailVehicleRecyclerAdapter extends RecyclerView.Adapter<DetailVehicleRecyclerAdapter.VehicleItemView> {

    Activity parentActivity;
    BaseRecyclerInterface mHandler;
    public DetailVehicleRecyclerAdapter(Activity parentActivity, BaseRecyclerInterface mHandler) {
        this.parentActivity = parentActivity;
        this.mHandler = mHandler;
    }

    @NonNull
    @Override
    public VehicleItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = parentActivity.getLayoutInflater().inflate(R.layout.custom_vehicle_info, parent, false);
        return new VehicleItemView(rootView, mHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleItemView holder, int position) {
        holder.SetDataContent("");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class VehicleItemView extends BaseRecyclerViewHolder<String>
    {
        public VehicleItemView(View itemView, BaseRecyclerInterface handler) {
            super(itemView, handler);
        }

        @Override
        public void SetDataContent(String model) {

        }
    }
}
