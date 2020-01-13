package com.example.demo.dao;

import com.example.demo.model.SysRoleUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RoleUserDao {

    @Insert("insert into sys_role_user(userId,roleId) values (#{userId},#{roleId})")
    void save(SysRoleUser sysRoleUser);

    @Select("select * from sys_role_user where userId = #{userId}")
    SysRoleUser getRoleUserByUserId(Integer userId);


    int updateSysRoleUser(SysRoleUser sysRoleUser);
}
