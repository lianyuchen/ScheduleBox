package com.lyc.schedulebox.utils;

import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;
import com.lyc.schedulebox.logic.model.ScheduleListBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lianyuchen on 16/3/11.
 */
public class ModelUtils {

    public static WeekViewEvent toWeekViewEvent(ScheduleListBean.ObjEntity.ListEntity bean) {
        Date start = new Date();
        Date end = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            start = sdf.parse(bean.getScheduleStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            end = sdf.parse(bean.getScheduleEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar startTime = Calendar.getInstance();
        startTime.setTime(start);

//        startTime.setTimeInMillis(start.getTime());
//        startTime.set(Calendar.YEAR, now.get(Calendar.YEAR));
//        startTime.set(Calendar.MONTH, now.get(Calendar.MONTH));
//        startTime.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(end);

//        endTime.setTimeInMillis(end.getTime());
//        endTime.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
//        endTime.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
//        endTime.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));
        // Create an week view event.
        WeekViewEvent weekViewEvent = new WeekViewEvent(bean.getScheduleId(),bean.getScheduleContent(),startTime,endTime);
        weekViewEvent.setColor(Color.parseColor(bean.getScheduleColor()));

        return weekViewEvent;
    }

}
