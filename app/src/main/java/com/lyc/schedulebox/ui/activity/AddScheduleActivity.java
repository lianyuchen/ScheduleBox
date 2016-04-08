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
import com.tencent.stat.StatService;

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
    @Bind(R.id.tv_schedule_color)
    TextView tvScheduleColor;
    @Bind(R.id.ll_schedule_color)
    LinearLayout llScheduleColor;
    private TimePickerView timePickerView;
    private OptionsPickerView optionsPickerView, colorOptionsPickerView;
    private ArrayList<String> optionsItems = new ArrayList<>();
    private ArrayList<String> colorOptionsItems = new ArrayList<>();
    private IAddSchedulePresenter mAddSchedulePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    private void init() {
        initTitleBar();
        setTitleBarText("新建日程");
        tvStartTime.setText(getIntent().getStringExtra("startTime").toString());

        setTimePicker();
        setTypeOptionsPicker();
        setColorOptionsPicker();

    }

    /**
     * 设置颜色的选项选择器
     */
    private void setColorOptionsPicker() {

        colorOptionsItems.add("高");
        colorOptionsItems.add("中");
        colorOptionsItems.add("低");

        colorOptionsPickerView = new OptionsPickerView(this);
        colorOptionsPickerView.setPicker(colorOptionsItems);
        colorOptionsPickerView.setCyclic(false);
        colorOptionsPickerView.setSelectOptions(1);
        colorOptionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                tvScheduleColor.setText(colorOptionsItems.get(options1));
            }
        });
    }

    /**
     * 设置时间选择器
     */
    private void setTimePicker() {
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
    }

    /**
     * 设置类型的选项选择器
     */
    private void setTypeOptionsPicker() {
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

    @OnClick({R.id.ll_end_time, R.id.ll_schedule_type, R.id.tv_end_time, R.id.btn_add,
            R.id.tv_schedule_color, R.id.ll_schedule_color})
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
            case R.id.tv_schedule_color:
                colorOptionsPickerView.show();
                break;
            case R.id.ll_schedule_color:
                colorOptionsPickerView.show();
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

        Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
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

    @Override
    public String getScheduleColor() {
        String text, defColor = "";
        text = tvScheduleColor.getText().toString().trim();
        if ("高".equals(text)) {
            defColor = "#EA0000";
        } else if ("中".equals(text)) {
            defColor = "#C6A300";
        } else if ("低".equals(text)) {
            defColor = "#66B3FF";
        }
        return defColor;
    }
}
