package com.rock.reliantdispatch.CarrierActivities.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;

public class VehicleRecyclerAdapter extends RecyclerView.Adapter<VehicleRecyclerAdapter.VehicleDetailView> {

    Activity parentActivity;
    BaseRecyclerInterface mHandler;
    public VehicleRecyclerAdapter(Activity parentActivity, BaseRecyclerInterface mHandler) {
        this.parentActivity = parentActivity;
        this.mHandler = mHandler;
    }

    @NonNull
    @Override
    public VehicleDetailView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = parentActivity.getLayoutInflater().inflate(R.layout.custom_vehicle_info, parent, false);
        return new VehicleDetailView(rootView, mHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleDetailView holder, int position) {
        holder.SetDataContent("");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class VehicleDetailView extends BaseRecyclerViewHolder<String>
    {
        public VehicleDetailView(View itemView, BaseRecyclerInterface handler) {
            super(itemView, handler);
        }

        @Override
        public void SetDataContent(String model) {

        }
    }
}
