package com.rock.reliantdispatch.Accounts.MyAddressBook;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.rock.reliantdispatch.Base.BaseRecyclerViewHolder;
import com.rock.reliantdispatch.Interface.AddressRecyclerInterface;
import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.Main.Carrier.SavedRouteSearch.SavedSearchAdapter;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressBookAdapter extends RecyclerView.Adapter<AddressBookAdapter.AddressBookViewHolder> {

    AddressRecyclerInterface parentHandler;
    Activity activity;

    public AddressBookAdapter(AddressRecyclerInterface parentHandler, Activity parent) {
        this.parentHandler = parentHandler;
        this.activity = parent;
    }

    @NonNull
    @Override
    public AddressBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.item_addressbook, parent, false);
        return new AddressBookViewHolder(itemView, parentHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressBookViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class AddressBookViewHolder extends BaseRecyclerViewHolder<String>
    {
        @BindView(R.id.btRemove)
        ImageButton btRemove;
        public AddressBookViewHolder(View itemView, AddressRecyclerInterface handler) {
            super(itemView, handler);
            ButterKnife.bind(this, itemView);
            btRemove.setOnClickListener((view) -> {handler.OnClickRemoveItem(getAdapterPosition());});
        }

        @Override
        public void SetDataContent(String model) {

        }
    }
}
