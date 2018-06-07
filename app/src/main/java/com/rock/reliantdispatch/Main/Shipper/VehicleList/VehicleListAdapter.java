package com.rock.reliantdispatch.Main.Shipper.VehicleList;
import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Constants.VehiclesConfig;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.Model.Shipper.VehicleListingModel;
import com.rock.reliantdispatch.R;

import java.util.ArrayList;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehicleListAdapter<String> extends RecyclerView.Adapter<VehicleListAdapter.VehicleViewHolder> {
    private BaseRecyclerInterface parentHandler;
    Activity context;
    ArrayList<VehicleListingModel> dataProvider;
    public VehicleListAdapter(Activity context, BaseRecyclerInterface parentActivity) {
        dataProvider = new ArrayList<>();
        this.context = context;
        this.parentHandler = parentActivity;
    }

    public void setDataProvider(ArrayList<VehicleListingModel> dataProvider)
    {
        this.dataProvider = dataProvider;
        context.runOnUiThread(this::notifyDataSetChanged);
    }

    @NonNull
    @Override
    public VehicleListAdapter.VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicles_listed, parent, false);
        return new VehicleViewHolder(view, parentHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleListAdapter.VehicleViewHolder holder, int position) {
        VehicleListingModel model = new VehicleListingModel();
        model.setType(getItemViewType(position));
        holder.SetDataContent(model);
    }

    @Override
    public int getItemCount() {
        return dataProvider.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataProvider.get(position).getType();
    }

    class VehicleViewHolder extends BaseRecyclerViewHolder<VehicleListingModel>
    {
        @BindView(R.id.viewIndicator) View viewIndicator;
        @BindView(R.id.txtIndicator) TextView txtIndicator;
        @BindView(R.id.txtOrderId) TextView txtOrderId;
        VehicleListingModel model;
        VehicleViewHolder(View itemView, BaseRecyclerInterface handler) {
            super(itemView, handler);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void SetDataContent(VehicleListingModel model) {
            this.model = model;
            viewIndicator.setBackgroundResource(VehiclesConfig.VEHICLE_LISTING_TYPE_COLOR_ARRAY[model.getType()]);
            txtIndicator.setTextColor(ContextCompat.getColor(itemView.getContext(), VehiclesConfig.VEHICLE_LISTING_TYPE_COLOR_ARRAY[model.getType()]));
            txtIndicator.setText(VehiclesConfig.VEHICLE_LISTING_TYPE_STRING_ARRAY[model.getType()]);
        }
    }
}


