package com.rock.reliantdispatch.Base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;

public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder{
    BaseRecyclerInterface parentHandler;
    public BaseRecyclerViewHolder(View itemView, BaseRecyclerInterface handler) {
        super(itemView);
        this.parentHandler = handler;
        itemView.setOnClickListener((view) -> {parentHandler.OnRecyclerItemClick(getAdapterPosition());});
    }

    abstract public void SetDataContent(T model);
}
