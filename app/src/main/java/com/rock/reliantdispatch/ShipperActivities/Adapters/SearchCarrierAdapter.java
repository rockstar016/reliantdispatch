package com.rock.reliantdispatch.ShipperActivities.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;

import java.util.ArrayList;

public class SearchCarrierAdapter extends RecyclerView.Adapter<SearchCarrierAdapter.SearchCarrierViewHolder<String>> {

    ArrayList<String> dataProvider;
    Activity parentActivity;
    BaseRecyclerInterface mInterface;
    public SearchCarrierAdapter(Activity parentActivity, ArrayList<String> dataProvider, BaseRecyclerInterface mInterface) {
        super();
        this.dataProvider = dataProvider;
        this.parentActivity = parentActivity;
        this.mInterface = mInterface;
    }

    @NonNull
    @Override
    public SearchCarrierViewHolder<String> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = parentActivity.getLayoutInflater().inflate(R.layout.item_search_carrier, parent, false);
        return new SearchCarrierViewHolder<String>(rootView, mInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCarrierViewHolder<String> holder, int position) {
        holder.SetDataContent(dataProvider.get(position));
    }

    @Override
    public int getItemCount() {
        return dataProvider.size();
    }
    class SearchCarrierViewHolder<String> extends BaseRecyclerViewHolder<String>
    {
        public SearchCarrierViewHolder(View itemView, BaseRecyclerInterface mInterface) {
            super(itemView, mInterface);
        }

        @Override
        public void SetDataContent(String model) {

        }
    }
}


