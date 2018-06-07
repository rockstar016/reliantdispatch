package com.rock.reliantdispatch.Main.Carrier.TruckSpace;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Interface.DirectDispatchInterface;
import com.rock.reliantdispatch.R;

import butterknife.ButterKnife;

/**
 * Created by RockStar0116 on 4/18/18.
 */

public class TruckSpaceAdapter extends RecyclerView.Adapter<TruckSpaceAdapter.TruckSpaceViewHolder> {
    Activity ParentActivity;
    DirectDispatchInterface listener;
    public TruckSpaceAdapter(Activity parent, DirectDispatchInterface listener) {
        this.listener = listener;
        ParentActivity = parent;
    }

    @NonNull
    @Override
    public TruckSpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(ParentActivity).inflate(R.layout.item_truck_profile, parent, false);
        return new TruckSpaceViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TruckSpaceViewHolder holder, int position) {
        holder.SetDataContent("");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class TruckSpaceViewHolder extends BaseRecyclerViewHolder<String> {
        TruckSpaceViewHolder(View itemView, DirectDispatchInterface handler) {
            super(itemView, handler);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void SetDataContent(String model) {

        }
    }
}
