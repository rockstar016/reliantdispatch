package com.rock.reliantdispatch.Main.Shipper.CarrierTrailer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by michalejackson on 4/14/18.
 */

public class CarrierTrailerAdapter extends RecyclerView.Adapter<CarrierTrailerAdapter.CarrierTrailerViewHolder> {
    Activity activity;
    BaseRecyclerInterface listener;

    public CarrierTrailerAdapter(Activity activity, BaseRecyclerInterface listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarrierTrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_carrier_trailer, parent, false);
        return new CarrierTrailerViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CarrierTrailerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }


    public class CarrierTrailerViewHolder extends BaseRecyclerViewHolder<String> {
        public CarrierTrailerViewHolder(View itemView, BaseRecyclerInterface handler) {
            super(itemView, handler);
        }

        @Override
        public void SetDataContent(String model) {

        }
    }

    public interface OnItemSelectedListener {
        public void itemSelection(int position);
    }
}
