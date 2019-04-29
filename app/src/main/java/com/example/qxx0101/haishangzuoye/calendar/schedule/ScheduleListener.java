package com.example.qxx0101.haishangzuoye.calendar.schedule;

import com.example.qxx0101.haishangzuoye.calendar.bean.Schedule;
import com.example.qxx0101.haishangzuoye.calendar.bean.ScheduleListBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 */
public interface ScheduleListener {
    void OnGetSelectScheduleList(ScheduleListBean scheduleListBean);
    void OnGetAllScheduleList(List<Schedule> allScheduleList);
}
