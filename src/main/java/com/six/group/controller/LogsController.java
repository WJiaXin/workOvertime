package com.six.group.controller;


import com.six.group.entity.Logs;
import com.six.group.entity.User;
import com.six.group.entity.WorkOvertime;
import com.six.group.service.LogsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
@RequestMapping("/logs")
@Api(value = "组员controller",tags = {"组员操作接口"})
public class LogsController {
    @Autowired
    private LogsService logsService;

    Logger logger=LoggerFactory.getLogger(LogsController.class);

    @PostMapping(value = "/applys")
    @ResponseBody
    public int applyWorkOvertime(HttpSession session) throws ParseException {
        User user = (User) session.getAttribute("user");
        String userId = user.getPhone();
        java.util.Date nowdate = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(nowdate);
        Logs logs = new Logs();
        logs.setLogUserId(userId);
        logs.setLogDate(date);
        logs.setLogState(0);
        return logsService.postApplys(logs);
    }

    @GetMapping(value = "/applys")
    @ResponseBody
    public Map getApplys(HttpSession session) {
        int state = -2;
        User user = (User) session.getAttribute("user");
        String userId =user.getPhone();
        java.util.Date nowdate = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(nowdate);
        WorkOvertime workOvertime = logsService.getNeedWork(date);
        if (workOvertime!=null){
            state = logsService.getApplyState(userId,date);
        }
        List<Logs> logs = logsService.getApplys(userId);
        logger.info(logs.toString());
        Map map = new HashMap();
        map.put("workOverTime",workOvertime);
        map.put("logs",logs);
        map.put("state",state);
        return map;
    }

    @GetMapping(value = "/workday")
    @ApiImplicitParam(name = "workDay",value = "想查询的日期(例：2020-07-01)",dataType = "String",paramType = "query")
    @ResponseBody
    public WorkOvertime getNeedWork(String workDay) throws ParseException {
        return logsService.getNeedWork(workDay);}

}