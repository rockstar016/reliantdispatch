package com.rock.reliantdispatch.CarrierActivities.TruckSpace;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;

import butterknife.ButterKnife;

public class DetailTruckSpaceAdapter extends RecyclerView.Adapter<DetailTruckSpaceAdapter.DepartureViewHolder> {
    Activity activity;
    BaseRecyclerInterface parentHandler;

    public DetailTruckSpaceAdapter(Activity activity, BaseRecyclerInterface parentHandler) {
        this.activity = activity;
        this.parentHandler = parentHandler;
    }

    @NonNull
    @Override
    public DepartureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.item_truck_departure, parent, false);
        return new DepartureViewHolder(itemView, parentHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartureViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class DepartureViewHolder extends BaseRecyclerViewHolder<String> {
        DepartureViewHolder(View itemView, BaseRecyclerInterface handler) {
            super(itemView, handler);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void SetDataContent(String model) {

        }
    }
}
