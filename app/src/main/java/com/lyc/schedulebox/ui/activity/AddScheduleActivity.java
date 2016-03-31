package com.lyc.schedulebox.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.lyc.schedulebox.R;
import com.lyc.schedulebox.presenter.IAddSchedulePresenter;
import com.lyc.schedulebox.presenter.impl.SchedulePresenterImpl;
import com.lyc.schedulebox.utils.SharedPreferenceUtils;
import com.lyc.schedulebox.view.IAddScheduleView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddScheduleActivity extends BaseActivity implements IAddScheduleView {


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
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.et_content)
    EditText etContent;
    private TimePickerView timePickerView;
    private OptionsPickerView optionsPickerView;
    private ArrayList<String> optionsItems = new ArrayList<String>();
    private IAddSchedulePresenter mAddSchedulePresenter;

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
        tvStartTime.setText(getIntent().getStringExtra("startTime").toString());
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

    @OnClick({R.id.ll_end_time, R.id.ll_schedule_type, R.id.tv_end_time, R.id.btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_end_time:
                timePickerView.show();
                break;
            case R.id.ll_end_time:
                timePickerView.show();
                break;
            case R.id.ll_schedule_type:
                optionsPickerView.show();
                break;
            case R.id.btn_add:
                mAddSchedulePresenter = new SchedulePresenterImpl(this);
                mAddSchedulePresenter.addSchedule();
                break;
        }
    }

    @Override
    public String getStartTime() {
        return tvStartTime.getText().toString().trim();
    }

    @Override
    public String getEndTime() {
        return tvEndTime.getText().toString().trim();
    }

    @Override
    public String getScheduleType() {
        return tvScheduleType.getText().toString().trim();
    }

    @Override
    public void completeAddSchedule() {

        Toast.makeText(this,"添加成功！",Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public int getUserId() {
        return SharedPreferenceUtils.getValue(this, "login_info", "userId", -1);
    }

    @Override
    public String getScheduleContent() {
        return etContent.getText().toString().trim();
    }
}
