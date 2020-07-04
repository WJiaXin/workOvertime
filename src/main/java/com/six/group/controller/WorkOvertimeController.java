package com.six.group.controller;


import com.six.group.entity.WorkOvertime;
import com.six.group.service.WorkOvertimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "加班餐/费接口")
@RequestMapping("workOvertime")
public class WorkOvertimeController {
    @Autowired
    WorkOvertimeService workOvertimeService;

    @GetMapping("/{data}")
    @ApiOperation("获取指定日期餐/费选择")
    @ApiImplicitParam(name = "data", value = "日期", required = false,
            dataType = "string", paramType = "path", defaultValue = "2020-06-29")
    public WorkOvertime getData(@PathVariable("data") String data){
       return workOvertimeService.getData(data);
    }


    @PostMapping()
    @ApiOperation("添加指定日期餐/费选择")
    public int postData(@RequestBody WorkOvertime workOvertime){
       return workOvertimeService.postData(workOvertime);
    }

    @PatchMapping()
    @ApiOperation("修改指定日期餐/费选择")
    public int putData(@RequestBody WorkOvertime workOvertime){
        return workOvertimeService.putData(workOvertime);
    }

    @GetMapping("/user/{data}/{page}")
    @ApiOperation("获取指定日期加班人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "日期", required = false,
                    dataType = "string", paramType = "path", defaultValue = "2020-06-29"),
            @ApiImplicitParam(name = "page", value = "页码", required = false,
                    dataType = "int", paramType = "path", defaultValue = "1")
    }
    )
    public JSONArray getDataUsers(@PathVariable("data") String data, @PathVariable("page") Integer page){

        return workOvertimeService.getDataUsers(data,page);
    }
    @GetMapping("/logs/{data}/{page}")
    @ApiOperation("获取指定日期申请记录")
    @ApiImplicitParam(name = "data", value = "日期", required = false,
            dataType = "string", paramType = "path", defaultValue = "2020-06-28 至 2020-06-28")
    public JSONArray getDataLogs(@PathVariable("data") String data, @PathVariable("page") Integer page){
        String[] SEdata=data.split(" 至 ");
        return workOvertimeService.getDataLogs(SEdata[0],SEdata[1],page);
    }

    @PutMapping("/logs/{log_id}/{state}")
    @ApiOperation("审批指定日期申请记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "log_id", value = "记录id", required = false,
                    dataType = "int", paramType = "path", defaultValue = "1"),
            @ApiImplicitParam(name = "state", value = "状态(1:同意、2:拒绝", required = false,
                    dataType = "int", paramType = "path", defaultValue = "1")
    }
    )
    public int putLogs(@PathVariable("log_id") Integer log_id, @PathVariable("state") Integer state){
        return workOvertimeService.putLogs(log_id,state);
    }
}
