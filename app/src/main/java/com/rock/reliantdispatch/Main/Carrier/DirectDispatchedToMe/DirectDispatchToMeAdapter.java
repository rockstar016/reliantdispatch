package com.rock.reliantdispatch.Main.Carrier.DirectDispatchedToMe;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rock.model.Carrier.OrderListing;
import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Constants.OrderConfig;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DirectDispatchToMeAdapter extends RecyclerView.Adapter<DirectDispatchToMeAdapter.DirectDispatchToMeViewHolder> {
    BaseRecyclerInterface parentHandler;
    Activity activity;

    public DirectDispatchToMeAdapter(BaseRecyclerInterface parentHandler, Activity activity) {
        this.parentHandler = parentHandler;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DirectDispatchToMeAdapter.DirectDispatchToMeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.item_vehicles_listed, parent, false);
        return new DirectDispatchToMeViewHolder(itemView, parentHandler);

    }

    @Override
    public void onBindViewHolder(@NonNull DirectDispatchToMeAdapter.DirectDispatchToMeViewHolder holder, int position) {
        OrderListing model = new OrderListing();
        model.setType(getItemViewType(position));
        holder.SetDataContent(model);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class DirectDispatchToMeViewHolder extends BaseRecyclerViewHolder<OrderListing>
    {
        @BindView(R.id.viewIndicator) View viewIndicator;
        @BindView(R.id.txtIndicator)
        TextView txtIndicator;
        @BindView(R.id.txtOrderId) TextView txtOrderId;
        OrderListing model;
        DirectDispatchToMeViewHolder(View itemView, BaseRecyclerInterface handler) {
            super(itemView, handler);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void SetDataContent(OrderListing model) {
            this.model = model;
            viewIndicator.setBackgroundResource(OrderConfig.ORDER_TYPE_COLOR_ARRAY[model.getType()]);
            txtIndicator.setTextColor(ContextCompat.getColor(itemView.getContext(), OrderConfig.ORDER_TYPE_COLOR_ARRAY[model.getType()]));
            txtIndicator.setText(OrderConfig.ORDER_TYPE_STRING_ARRAY[model.getType()]);
        }
    }


}
