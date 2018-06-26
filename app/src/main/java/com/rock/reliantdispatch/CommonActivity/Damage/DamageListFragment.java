package com.rock.reliantdispatch.CommonActivity.Damage;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.rock.reliantdispatch.Interface.BaseRecyclerInterface;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DamageListFragment extends DialogFragment implements BaseRecyclerInterface{

    @BindView(R.id.damageList)
    RecyclerView damageList;
    @BindView(R.id.btCancel)
    Button btCancel;
    Unbinder unbinder;
    DamageRecyclerAdapter adapter;

    DamageDialogInterface parentHandler;
    public DamageListFragment() {
        // Required empty public constructor
    }

    public static DamageListFragment newInstance() {
        DamageListFragment fragment = new DamageListFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_damage_list, container, true);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);

        unbinder = ButterKnife.bind(this, view);
        damageList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DamageRecyclerAdapter(this);
        damageList.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btCancel)
    public void onViewClicked() {
        this.dismiss();
    }

    @Override
    public void OnRecyclerItemClick(int position) {
        if(parentHandler != null){
            parentHandler.OnChooseDamageItem(position);
        }
        this.dismiss();
    }

    public void setParentHandler(DamageDialogInterface handler){
        parentHandler = handler;
    }

    interface DamageDialogInterface{
        void OnChooseDamageItem(int position);
    }
}
