package com.six.group.service;


import com.six.group.entity.Logs;
import com.six.group.entity.WorkOvertime;

import java.util.List;

public interface LogsService {
    int postApplys(Logs logs);
    List<Logs> getApplys(String userId);
    WorkOvertime getNeedWork(String workDay);
    int getApplyState(String userId, String workDay);
}
