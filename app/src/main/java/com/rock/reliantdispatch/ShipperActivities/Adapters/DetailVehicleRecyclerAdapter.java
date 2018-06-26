package com.rock.reliantdispatch.ShipperActivities.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rock.model.Common.VehicleModel;
import com.rock.reliantdispatch.Interface.VehicleDetailItemViewInterface;
import com.rock.reliantdispatch.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This adapter is for Shipper. So shipper can only see the inspection. He can't edit the inspection model.
 */
public class DetailVehicleRecyclerAdapter extends RecyclerView.Adapter<DetailVehicleRecyclerAdapter.VehicleItemViewHolder> {
    private Activity parentActivity;
    private VehicleDetailItemViewInterface mHandler;
    private ArrayList<VehicleModel> dataProvider;
    public DetailVehicleRecyclerAdapter(Activity parentActivity, VehicleDetailItemViewInterface mHandler) {
        this.parentActivity = parentActivity;
        this.mHandler = mHandler;
        dataProvider = new ArrayList<>();
    }

    public void insertData(@NonNull VehicleModel item)
    {
        dataProvider.add(item);
        parentActivity.runOnUiThread( () -> notifyItemInserted(dataProvider.size() - 1));
    }

    public void setDataProvider(@NonNull ArrayList<VehicleModel> newList)
    {
        dataProvider = newList;
        parentActivity.runOnUiThread(this::notifyDataSetChanged);
    }

    @NonNull
    @Override
    public VehicleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = parentActivity.getLayoutInflater().inflate(R.layout.custom_vehicle_info, parent, false);
        return new VehicleItemViewHolder(rootView, mHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleItemViewHolder holder, int position) {
        holder.SetDataContent(dataProvider.get(position));
    }

    @Override
    public int getItemCount() {
        return dataProvider.size();
    }

    class VehicleItemViewHolder extends RecyclerView.ViewHolder
    {
        VehicleDetailItemViewInterface mParentHandler;
        @BindView(R.id.year) TextView year;
        @BindView(R.id.make) TextView make;
        @BindView(R.id.model) TextView model;
        @BindView(R.id.damageCount) TextView damageCount;
        @BindView(R.id.txtDamages) TextView txtDamages;
        @BindView(R.id.lotTxt) TextView lotTxt;
        @BindView(R.id.vinTxt) TextView vinTxt;
        @BindView(R.id.detailBtn) Button detailBtn;
        @BindView(R.id.inspectionBtn) Button inspectionBtn;
        @BindView(R.id.carDetail) ImageView carDetail;
        @BindView(R.id.btCancel) ImageButton btCancel;
        VehicleItemViewHolder(View itemView, @NonNull VehicleDetailItemViewInterface handler) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mParentHandler = handler;
            detailBtn.setOnClickListener((view) -> mParentHandler.OnClickDetail(getAdapterPosition()));
            inspectionBtn.setOnClickListener((view) -> mParentHandler.OnClickInspection(getAdapterPosition()));
            btCancel.setOnClickListener(v->mParentHandler.OnClickRemove(getAdapterPosition()));
        }

        void SetDataContent(@NonNull VehicleModel vModel)
        {
            year.setText(vModel.getYear());
            make.setText(vModel.getMake());
            model.setText(vModel.getModel());
            vinTxt.setText(vModel.getVinCode());
            lotTxt.setText(vModel.getLot());
            //todo I think we need to add damage count and damage informtion to vehicle information class
            damageCount.setText("No damages");
            if(vModel.getCarPicture().isEmpty()) {
                Picasso.get().load(R.drawable.car).into(carDetail);
            }
            else {
                Picasso.get().load(vModel.getCarPicture()).into(carDetail);
            }
            txtDamages.setText("");
        }
    }
}
