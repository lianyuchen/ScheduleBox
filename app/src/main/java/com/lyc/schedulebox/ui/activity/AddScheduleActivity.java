package com.lyc.schedulebox.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.lyc.schedulebox.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddScheduleActivity extends BaseActivity {


    @Bind(R.id.ll_start_time)
    LinearLayout llStartTime;
    @Bind(R.id.ll_end_time)
    LinearLayout llEndTime;
    @Bind(R.id.ll_schedule_type)
    LinearLayout llScheduleType;
    @Bind(R.id.tv_schedule_type)
    TextView tvScheduleType;
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    private TimePickerView timePickerView;
    private OptionsPickerView optionsPickerView;
    private ArrayList<String> optionsItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initTitleBar();
        setTitleBarText("新建日程");
        //时间选择器
        timePickerView = new TimePickerView(this, TimePickerView.Type.ALL);
        timePickerView.setTime(new Date());
        timePickerView.setCyclic(false);
        timePickerView.setCancelable(true);

        //时间选择后回调
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvEndTime.setText(getTime(date));
            }
        });
        //弹出时间选择器
        tvEndTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                timePickerView.show();
            }
        });

        optionsItems.add("游玩");
        optionsItems.add("出差");
        optionsItems.add("运动");
        //选项选择器
        optionsPickerView = new OptionsPickerView(this);
        optionsPickerView.setPicker(optionsItems);
        optionsPickerView.setCyclic(false);
        optionsPickerView.setSelectOptions(1);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                tvScheduleType.setText(optionsItems.get(options1));
            }
        });
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @OnClick({R.id.ll_end_time, R.id.ll_schedule_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_end_time:
                timePickerView.show();
                break;
            case R.id.ll_schedule_type:
                optionsPickerView.show();
                break;
        }
    }

}
