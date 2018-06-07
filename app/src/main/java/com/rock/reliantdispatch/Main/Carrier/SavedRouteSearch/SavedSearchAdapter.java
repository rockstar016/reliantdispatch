package com.rock.reliantdispatch.Main.Carrier.SavedRouteSearch;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.Interface.DirectDispatchInterface;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by michalejackson on 4/14/18.
 */

public class SavedSearchAdapter extends RecyclerView.Adapter<SavedSearchAdapter.SavedSearchViewHolder> {
    BaseRecyclerInterface parentHandler;
    Activity activity;

    public SavedSearchAdapter(Activity parent, BaseRecyclerInterface listener) {
        this.activity = parent;
        this.parentHandler = listener;
    }

    @NonNull
    @Override
    public SavedSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.item_saved_search, parent, false);
        return new SavedSearchViewHolder(itemView, parentHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedSearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }



    public class SavedSearchViewHolder extends BaseRecyclerViewHolder<String> {
        public SavedSearchViewHolder(View itemView, BaseRecyclerInterface handler) {
            super(itemView, handler);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void SetDataContent(String model) {

        }
    }

}
