package com.rock.reliantdispatch.CommonActivity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rock.model.Common.InspectModel;
import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InspectionRecyclerAdapter extends RecyclerView.Adapter<InspectionRecyclerAdapter.InspectionItem> {

    Activity context;
    BaseRecyclerInterface mParent;
    ArrayList<InspectModel> mDataProvider;
    int choosePosition;

    public InspectionRecyclerAdapter(Activity context, BaseRecyclerInterface mParent){
        this.context = context;
        this.mParent = mParent;
        choosePosition = -1;
    }

    public int getChoosePosition() {
        return choosePosition;
    }

    public void setChoosePosition(int newPos) {
        if(choosePosition > -1)
            notifyItemChanged(choosePosition);
        this.choosePosition = newPos;
        notifyItemChanged(newPos);
    }

    @NonNull
    @Override
    public InspectionItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_inspection_photo, parent, false);
        return new InspectionItem(itemView, mParent);
    }

    @Override
    public void onBindViewHolder(@NonNull InspectionItem holder, int position) {
        holder.setSelected(position == choosePosition);
        holder.SetDataContent(mDataProvider.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataProvider.size();
    }

    public void updateDataProvider(ArrayList<InspectModel> model)
    {
        this.mDataProvider = model;
        notifyDataSetChanged();
    }

    class InspectionItem extends BaseRecyclerViewHolder<InspectModel>
    {
        @BindView(R.id.imgContent)
        ImageView imgContent;

        boolean isSelected;
        InspectionItem(View itemView, BaseRecyclerInterface handler) {
            super(itemView, handler);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void SetDataContent(InspectModel model) {
            imgContent.setBackgroundResource(isSelected?R.drawable.inspection_select_background:R.drawable.inspection_unselect_background);
            Picasso.get().load(model.getFilePath()).error(R.drawable.car).into(imgContent);
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
