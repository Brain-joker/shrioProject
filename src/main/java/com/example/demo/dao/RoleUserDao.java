package com.example.demo.dao;

import com.example.demo.model.SysRoleUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleUserDao {

    @Insert("insert into sys_role_user(userId,roleId) values (#{userId},#{roleId})")
    void save(SysRoleUser sysRoleUser);

    @Select("select * from sys_role_user where userId = #{userId}")
    SysRoleUser getRoleUserByUserId(Integer userId);


    int updateSysRoleUser(SysRoleUser sysRoleUser);

    @Delete("delete from sys_role_user where userId = #{userId}")
    int deleteRoleUserByUserId(Integer userId);

    @Select("select * from sys_role_user t where t.roleId = #{roleId}")
    List<SysRoleUser> listAllSysRoleUserByRoleId(Integer roleId);
}
