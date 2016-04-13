package com.lyc.schedulebox.ui.activity;

import android.os.Bundle;

import com.lyc.schedulebox.R;

import org.eazegraph.lib.charts.PieChart;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnalysisScheduleActivity extends BaseActivity {

    @Bind(R.id.piechart)
    PieChart piechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_schedule);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitleBar();
        setTitleBarText("统计分析");
    }
}
