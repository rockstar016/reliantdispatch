package com.rock.reliantdispatch.ShipperActivities.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rock.reliantdispatch.Model.Common.VehicleModel;
import com.rock.reliantdispatch.R;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rock on 3/26/18.
 */

public class InspectionVehicleCountAdapter extends RecyclerView.Adapter<InspectionVehicleCountAdapter.InspectionBaseViewHolder> {

    public static final int HEADER_TYPE = 0;
    public static final int CONTENT_TYPE = 1;
    private ArrayList<VehicleModel> DataProvider;
    private Activity ParentActivity;
    public InspectionVehicleCountAdapter(Activity ParentActivity, ArrayList<VehicleModel> dataProvider)
    {
       this.DataProvider = dataProvider;
       this.ParentActivity = ParentActivity;
    }

    public void SetDataProvider(ArrayList<VehicleModel> response)
    {
        this.DataProvider = response;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
        {
            return HEADER_TYPE;
        }
        else
        {
            return CONTENT_TYPE;
        }
    }

    @NonNull
    @Override
    public InspectionBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == CONTENT_TYPE) {
            View itemView = LayoutInflater.from(ParentActivity).inflate(R.layout.custom_vehicle_info, parent, false);
            return new InspectionVehicleViewHolder(itemView);
        }
        else {
            View itemView = LayoutInflater.from(ParentActivity).inflate(R.layout.item_just_textview, parent, false);
            return new InspectionHeaderViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull InspectionBaseViewHolder holder, int position) {
        if(holder instanceof InspectionHeaderViewHolder)
        {
            ((InspectionHeaderViewHolder)holder).SetData(DataProvider.size());
        }
        else
        {
            ((InspectionVehicleViewHolder)holder).SetData(DataProvider.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        if(DataProvider.size() == 0) return 0;
        else return DataProvider.size() + 1;
    }

    class InspectionBaseViewHolder extends RecyclerView.ViewHolder
    {

        InspectionBaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    class InspectionVehicleViewHolder extends InspectionBaseViewHolder
    {
        @BindView(R.id.year)
        TextView year;
        @BindView(R.id.make)
        TextView make;
        @BindView(R.id.model)
        TextView model;
        @BindView(R.id.damageCount)
        TextView damageCount;
        @BindView(R.id.txtDamages)
        TextView txtDamages;
        @BindView(R.id.carDetail)
        ImageView carDetail;
        @BindView(R.id.vinTxt)
        TextView vinTxt;
        @BindView(R.id.lotTxt)
        TextView lotTxt;
        @BindView(R.id.detailBtn)
        Button detailBtn;
        @BindView(R.id.inspectionBtn)
        Button inspectionBtn;

        InspectionVehicleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void SetData(VehicleModel vinModel)
        {
                year.setText(vinModel.getYear());
                make.setText(vinModel.getMake());
                model.setText(vinModel.getModel());
                damageCount.setText("No damages");
                txtDamages.setText("");
                carDetail.setImageResource(R.drawable.car);
                vinTxt.setText(vinModel.getVinCode());
                lotTxt.setText(vinModel.getLot());
        }
    }

    class InspectionHeaderViewHolder extends InspectionBaseViewHolder
    {
        @BindView(R.id.txtContent)
        TextView txtContent;
        InspectionHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        void SetData(int Count)
        {
            if(Count == 1)
                txtContent.setText(String.format(Locale.US,"%d Vehicle", Count));
            else
                txtContent.setText(String.format(Locale.US, "%d Vehicles", Count));
        }
    }

}
