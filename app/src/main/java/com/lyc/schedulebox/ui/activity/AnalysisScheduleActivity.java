package com.lyc.schedulebox.ui.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.lyc.schedulebox.R;
import com.lyc.schedulebox.logic.model.AnalysisScheduleListModel;
import com.lyc.schedulebox.presenter.IAnalysisSchedulePresenter;
import com.lyc.schedulebox.presenter.impl.SchedulePresenterImpl;
import com.lyc.schedulebox.utils.SharedPreferenceUtils;
import com.lyc.schedulebox.view.IAnalysisView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnalysisScheduleActivity extends BaseActivity implements IAnalysisView{

    @Bind(R.id.piechart)
    PieChart piechart;

    private IAnalysisSchedulePresenter mAnalysisSchedulePresenter;

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
        mAnalysisSchedulePresenter = new SchedulePresenterImpl(this);
        mAnalysisSchedulePresenter.getAnalysisScheduleList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAnalysisSchedulePresenter.getAnalysisScheduleList();
    }

    @Override
    public int getUserId() {
        return SharedPreferenceUtils.getValue(this,"login_info", "userId",-1);
    }

    @Override
    public void showAnalysisSchedule(List<AnalysisScheduleListModel.ObjEntity.ListEntity> list) {

        if (list.size()<1){
            return;
        }
        for (AnalysisScheduleListModel.ObjEntity.ListEntity entity:list) {
            piechart.addPieSlice(new PieModel(entity.getName(),entity.getValue(), Color.parseColor(entity.getColor())));
        }
        piechart.startAnimation();
    }
}
