package com.rock.reliantdispatch.Main.Shipper.DraftList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;

/**
 * Created by rockzeus on 3/22/18.
 */

public class DraftListAdapter<String> extends RecyclerView.Adapter<DraftListAdapter.DraftViewHodler> {
    BaseRecyclerInterface parentHandler;
    Context context;

    public DraftListAdapter(Context context, BaseRecyclerInterface parentHandler) {
        this.context = context;
        this.parentHandler = parentHandler;
    }

    @NonNull
    @Override
    public DraftListAdapter.DraftViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivered, parent, false);
        return new DraftViewHodler(view, parentHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull DraftListAdapter.DraftViewHodler holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class DraftViewHodler extends BaseRecyclerViewHolder<String> {
        public DraftViewHodler(View itemView, BaseRecyclerInterface mInterface) {
            super(itemView, mInterface);
        }

        @Override
        public void SetDataContent(String model) {

        }
    }
}

