package com.six.group.dao;

import com.six.group.entity.WorkOvertime;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WorkOvertimeDao {
    WorkOvertime getData(String time);
    int postData(WorkOvertime workOvertime);
    int putData(WorkOvertime workOvertime);
    int getDataUsersC(String time);  //获取总页数
    List<JSONObject> getDataUsers(@Param("time") String time, @Param("page") int page);
    int getDataLogsC(@Param("startTime") String startTime, @Param("endTime") String endTime);
    List<JSONObject> getDataLogs(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("page") int page);
    int putLogs(@Param("logId") Integer logId, @Param("state") Integer state);
}
