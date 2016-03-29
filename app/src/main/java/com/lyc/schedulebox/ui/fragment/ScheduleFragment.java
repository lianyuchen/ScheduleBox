package com.lyc.schedulebox.ui.fragment;

import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.lyc.schedulebox.R;
import com.lyc.schedulebox.presenter.ISchedulePresenter;
import com.lyc.schedulebox.presenter.impl.SchedulePresenterImpl;
import com.lyc.schedulebox.utils.logutils.LogUtils;
import com.lyc.schedulebox.view.IScheduleFragView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lianyuchen on 15/12/30.
 */
public class ScheduleFragment extends BaseFragment implements IScheduleFragView, MonthLoader.MonthChangeListener,
        WeekView.EventClickListener,WeekView.EventLongPressListener, WeekView.EmptyViewClickListener {

    @Bind(R.id.weekView)
    WeekView weekView;
    private View mViews = null;
    private ISchedulePresenter mSchedulePresenter;
    private List<WeekViewEvent> events = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mViews == null) {
            mViews = inflater.inflate(R.layout.fragment_schedule, null);
            ButterKnife.bind(this, mViews);
            init();
        }
        ViewGroup parent = (ViewGroup) mViews.getParent();
        if (parent != null) {
            parent.removeView(mViews);
        }
        ButterKnife.bind(this, mViews);
        return mViews;
    }

    private void init() {
        mSchedulePresenter = new SchedulePresenterImpl(this);
        mSchedulePresenter.showSchedule();
        weekView.setEventLongPressListener(this);
        weekView.setOnEventClickListener(this);
        weekView.setMonthChangeListener(this);
        weekView.setEmptyViewClickListener(this);
        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        LogUtils.i(newYear + "----" + newMonth);
        return events;
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

    }


    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEmptyViewClicked(Calendar time) {
        Toast.makeText(getActivity(),String.format(""+time.get(Calendar.HOUR_OF_DAY)) + String.format(""+time.get(Calendar.MINUTE)),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showScheduleInfoList(List<WeekViewEvent> events) {
        this.events = events;
        weekView.notifyDatasetChanged();

    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        weekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

}