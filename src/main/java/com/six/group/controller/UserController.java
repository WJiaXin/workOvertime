package com.six.group.controller;


import com.six.group.entity.User;
import com.six.group.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Api("user相关的Api")
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    //  private int zt2;
    @ApiOperation("登录功能")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody(required=true) Map<String,Object> map, HttpServletRequest request) throws Exception{
        String a="请求成功";
        System.out.println("请求成功");
        /*String username = map.get("username").toString();
        String password = map.get("password").toString();
        System.out.println("username: " + username);
        System.out.println("password: " + password);*/

        String username = map.get("username").toString();
        String password = map.get("password").toString();
        String st = map.get("st").toString();
        int role;
        role=Integer.parseInt(st);
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println("st: " + st);
        User  userList=userService.login(username,password,role);
        //System.out.println("控制层" +goodsList.get(0).getUsername());
        request.getSession().setAttribute("user",userList);
        try
        {
            if(userList.getName()!=null&&userList.getName()!="")
            {
                return userList;
            }
        }
        catch(Exception e)
        {
            return null;
        }
        return userList;

//zt2=goods.getBMZT();
    }
    @ApiOperation("登录判断")
    @RequestMapping(value = "/checklogin", method = RequestMethod.POST)
    public boolean checklogin(HttpServletRequest request)
    {
        User goods= (User) request.getSession().getAttribute("goods");
        if(goods==null)
        { return false; }
        else return true;
    }
}
