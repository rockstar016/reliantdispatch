package com.rock.reliantdispatch.CommonActivity.Damage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Constants.DamageConfig;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DamageRecyclerAdapter extends RecyclerView.Adapter<DamageRecyclerAdapter.DamageViewHolder> {
    BaseRecyclerInterface parentHandler;

    public DamageRecyclerAdapter(BaseRecyclerInterface parentHandler) {
        this.parentHandler = parentHandler;
    }

    @NonNull
    @Override
    public DamageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_just_textview, parent, false);
        return new DamageViewHolder(view, parentHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull DamageViewHolder holder, int position) {
        holder.SetDataContent(DamageConfig.damageListProvider[position]);
    }

    @Override
    public int getItemCount() {
        return DamageConfig.damageListProvider.length;
    }

    class DamageViewHolder extends BaseRecyclerViewHolder<String>
    {
        @BindView(R.id.txtContent)
        TextView txtContent;
        DamageViewHolder(View itemView, BaseRecyclerInterface parentHandler) {
            super(itemView, parentHandler);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void SetDataContent(String model) {
            txtContent.setText(model);
        }

    }
}
