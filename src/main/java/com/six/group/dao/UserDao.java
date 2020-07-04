package com.six.group.dao;

import com.six.group.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    //方法名与Mapper中的方法名对应
   User login(@Param("phone") String phone, @Param("pwd") String pwd, @Param("role") int role);
}
