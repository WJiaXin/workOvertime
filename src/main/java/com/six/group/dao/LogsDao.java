package com.six.group.dao;


import com.six.group.entity.Logs;
import com.six.group.entity.WorkOvertime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogsDao {
    int insert(Logs record);
    List<Logs> getApplys(String logUserId);
    WorkOvertime getNeedWork(@Param("workDay") String workDay);
    int getApplyState(@Param("logUserId")String logUserId,@Param("logDate") String logDate);
}