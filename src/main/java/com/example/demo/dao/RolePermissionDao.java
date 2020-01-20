package com.example.demo.dao;

import com.example.demo.model.SysRolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionDao {

    int save(@Param("roleId")Integer id, @Param("permissionIds") List<Long> permissionIds);

    @Delete("delete from sys_role_permission where roleId = #{id}")
    int deleteRolePermission(Integer id);

    @Delete("delete from sys_role_permission where permissionId = #{permissionId}")
    int delete(SysRolePermission rolePermission);

}
