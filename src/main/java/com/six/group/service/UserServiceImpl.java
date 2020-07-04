package com.six.group.service;

import com.six.group.entity.User;
import com.six.group.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User login(String phone, String pwd, int role)
    {
        System.out.println("服务层"+phone);
        User user=userDao.login(phone,pwd,role);
        //System.out.println("服务层"+login.get(0).getUsername());
        return user;
    }
}
