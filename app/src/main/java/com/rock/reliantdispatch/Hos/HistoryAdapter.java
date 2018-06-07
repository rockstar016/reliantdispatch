package com.rock.reliantdispatch.Hos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.R;

/**
 * Created by michalejackson on 3/23/18.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    OnHistoryItemSelectedListener parentActivity;
    Context context;

    public HistoryAdapter(Context context, OnHistoryItemSelectedListener parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.MyViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentActivity.onItemSelection(getAdapterPosition());
                }
            });
        }
    }
    public interface OnHistoryItemSelectedListener{
        public void onItemSelection(int position);
    }
}