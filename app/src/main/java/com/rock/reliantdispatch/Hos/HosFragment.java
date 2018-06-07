package com.rock.reliantdispatch.Hos;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.rock.reliantdispatch.Base.BaseFragment;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.ScrollDisableLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HosFragment extends BaseFragment implements HistoryAdapter.OnHistoryItemSelectedListener {
    HistoryAdapter adapter;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.dutyChart)
    BarChart dutyChart;
    @BindView(R.id.historyRecycler)
    RecyclerView historyRecycler;
    Unbinder unbinder;

    public HosFragment() {
        // Required empty public constructor
    }

    public static HosFragment newInstance() {
        HosFragment fragment = new HosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hos, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new HistoryAdapter(parent, this);
        ScrollDisableLayoutManager layoutManager = new ScrollDisableLayoutManager(getActivity());
        layoutManager.setScrollEnabled(false);
        historyRecycler.setLayoutManager(layoutManager);
        historyRecycler.setAdapter(adapter);
        SetupBarChart();
        return view;
    }

    private void SetupBarChart() {

        YAxis yAxis = dutyChart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(24);
        yAxis.setDrawLabels(false);
        dutyChart.getAxisRight().setEnabled(false);
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 11; i < 19; i++) {
            entries.add(new BarEntry(i, i));
        }
        IAxisValueFormatter xAxisFormatter = new DefaultAxisValueFormatter(8);

        XAxis xAxis = dutyChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setGranularity(1f); // only intervals of 1 day
//        xAxis.setLabelCount(8);
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setTextColor(Color.parseColor("#ffffff"));
        xAxis.setValueFormatter(xAxisFormatter);
        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
        dataset.setValueTextColor(Color.parseColor("#ffffff"));
        dataset.setValueTextSize(10f);
        dataset.setColor(getResources().getColor(R.color.bar_color));
        dutyChart.getDescription().setText("");
        dutyChart.getLegend().setEnabled(false);
        dutyChart.getAxisLeft().setDrawGridLines(false);
        dutyChart.getXAxis().setDrawGridLines(false);

        BarData data = new BarData(dataset);
        data.setBarWidth(0.2f);
        dutyChart.setData(data);
        dutyChart.invalidate();

    }

    @Override
    public void onItemSelection(int position) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
