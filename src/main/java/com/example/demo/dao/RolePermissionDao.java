package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionDao {

    int save(@Param("roleId")Integer id, @Param("permissionIds") List<Long> permissionIds);
}
