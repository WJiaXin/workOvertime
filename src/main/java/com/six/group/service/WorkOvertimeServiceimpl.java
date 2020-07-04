package com.six.group.service;


import com.six.group.dao.WorkOvertimeDao;
import com.six.group.entity.WorkOvertime;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOvertimeServiceimpl implements WorkOvertimeService{
    @Autowired
    WorkOvertimeDao workOvertimeDao;

    @Override
    public WorkOvertime getData(String time){
        return workOvertimeDao.getData(time);
    }
    @Override
    public int postData(WorkOvertime workOvertime){
        return workOvertimeDao.postData(workOvertime);
    }
    @Override
    public int putData(WorkOvertime workOvertime){
        return workOvertimeDao.putData(workOvertime);
    }
    @Override
    public JSONArray getDataUsers(String time,int page){
        JSONArray pageDataUsers=new JSONArray();   //加班人员页面数据（1：总页数、2：人员信息）
        int dataUsersNums=workOvertimeDao.getDataUsersC(time);  //人员总数
        int start= (page-1)*10;    //
        pageDataUsers.add(0,(dataUsersNums%10==0)?dataUsersNums/10:(dataUsersNums/10+1));
        pageDataUsers.add(1,workOvertimeDao.getDataUsers(time,start));
        return pageDataUsers;
    }
    @Override
    public JSONArray getDataLogs(String startTime,String endTime,int page){
        JSONArray pageDataLogs=new JSONArray();   //审批记录数据（1：总页数、2：人员信息）
        int dataLogsNums=workOvertimeDao.getDataLogsC(startTime,endTime);
        int start= (page-1)*4;
        pageDataLogs.add(0,(dataLogsNums%4==0)?dataLogsNums/4:(dataLogsNums/4+1));
        pageDataLogs.add(1,workOvertimeDao.getDataLogs(startTime,endTime,start));
        return pageDataLogs;
    }
    @Override
    public int putLogs(Integer logId,Integer state){
        return workOvertimeDao.putLogs(logId,state);
    }
}
