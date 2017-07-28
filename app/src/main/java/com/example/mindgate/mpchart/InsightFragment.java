package com.example.mindgate.mpchart;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class InsightFragment extends android.support.v4.app.Fragment implements View.OnClickListener,OnChartValueSelectedListener {
    AppCompatImageView backButton,nextButton;
    AppCompatTextView datePicker,totalAmountTextView;
    AppCompatTextView selectedBarValue;
    BarChart barChart;
    SharedPreferences sharedPreferences;
    int totalAmount;

    List<BarEntry> dataEntries;
    BarDataSet barDataSet;
    BarData barData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_insight,container,false);
        initView(view);
        loadPreference();
        return view;
    }

    private void initView(View view) {
        backButton=view.findViewById(R.id.back);
        nextButton=view.findViewById(R.id.next);
        selectedBarValue = view.findViewById(R.id.selectedBarValue);
        datePicker=view.findViewById(R.id.datePick);
        barChart=view.findViewById(R.id.barChart);
        totalAmountTextView=view.findViewById(R.id.totalAmountTextView);
        dataEntries = new ArrayList<>();

        setClickListner();
    }
    private void loadPreference() {
        sharedPreferences= getActivity().getSharedPreferences("Week amount", Context.MODE_PRIVATE);
        totalAmount=sharedPreferences.getInt("totalAmount",0);
        if(totalAmount != 0){
            dataEntries.add(new BarEntry(1, sharedPreferences.getInt("mondayAmount",0)));
            dataEntries.add(new BarEntry(2, sharedPreferences.getInt("tuesdayAmount",0)));
            dataEntries.add(new BarEntry(3, sharedPreferences.getInt("wednesdayAmount",0)));
            dataEntries.add(new BarEntry(4, sharedPreferences.getInt("thursdayAmount",0)));
            dataEntries.add(new BarEntry(5, sharedPreferences.getInt("fridayAmount",0)));
            dataEntries.add(new BarEntry(6, sharedPreferences.getInt("saturdayAmount",0)));
            dataEntries.add(new BarEntry(7, sharedPreferences.getInt("sundayAmount",0)));

            barDataSet = new BarDataSet(dataEntries, "barDataSetEnries");
            barDataSet.setColor(Color.RED);
            barDataSet.setValueTextColor(Color.GRAY);

            barData = new BarData(barDataSet);
            barChart.setData(barData);
            barData.setBarWidth(0.25f);
            barChart.animateXY(3000,3000);

            barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    selectedBarValue.setText(String.valueOf(h.getY()));
                }

                @Override
                public void onNothingSelected() {

                }
            });

            barChart.invalidate();

            totalAmountTextView.setText(String.valueOf(totalAmount));
        }
    }

    private void setClickListner() {
        backButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        datePicker.setOnClickListener(this);
        barChart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(getContext(), "clicked...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }
}
