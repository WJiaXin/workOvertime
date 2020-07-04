package com.six.group.service;


import com.six.group.entity.User;


public interface UserService {
    //方法名与Mapper中的方法名对应
   User login(String phone, String pwd, int role);
}
