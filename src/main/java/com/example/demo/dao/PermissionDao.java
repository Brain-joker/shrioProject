package com.example.demo.dao;

import com.example.demo.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionDao {
    @Select("select * from sys_permission")
    List<SysPermission> findAll();

   /* @Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId " +
            "where rp.roleId = #{roleId} order by p.sort")*/
    List<SysPermission> listAllPermissionByRoleId(Integer roleId);
}
