package com.example.demo.service;


import com.alibaba.fastjson.JSONArray;
import com.example.demo.base.result.Results;
import com.example.demo.model.SysPermission;

public interface PermissionService {

    Results<JSONArray> listAllPermission();

    Results<SysPermission> listAllPermissionByRoleId(Integer id);
}
