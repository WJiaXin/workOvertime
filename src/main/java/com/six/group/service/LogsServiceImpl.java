package com.six.group.service;



import com.six.group.dao.LogsDao;
import com.six.group.entity.Logs;
import com.six.group.entity.WorkOvertime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogsServiceImpl implements LogsService{
    @Autowired
    private LogsDao logsDao;

    @Override
    public int postApplys(Logs logs) {
        return logsDao.insert(logs);
    }
    @Override
    public List<Logs> getApplys(String userId) {
       return logsDao.getApplys(userId);
    }
    @Override
    public WorkOvertime getNeedWork(String workDay) {
        return logsDao.getNeedWork(workDay);
    }
    @Override
    public int getApplyState(String userId, String workDay) {
        return logsDao.getApplyState(userId,workDay);}
}
