package com.six.group.service;


import com.six.group.entity.WorkOvertime;
import net.sf.json.JSONArray;

public interface WorkOvertimeService {
    WorkOvertime getData(String time);
    int postData(WorkOvertime workOvertime);
    int putData(WorkOvertime workOvertime);
    JSONArray getDataUsers(String time, int page);
    JSONArray getDataLogs(String startTime, String endTime, int page);
    int putLogs(Integer logId, Integer state);
}
