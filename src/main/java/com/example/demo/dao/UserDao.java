package com.example.demo.dao;

import com.example.demo.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from sys_user t where t.username = #{username}")
    SysUser getUser(String username);
}
