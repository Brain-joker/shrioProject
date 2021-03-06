package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.base.result.Results;
import com.example.demo.dao.PermissionDao;
import com.example.demo.model.SysPermission;
import com.example.demo.service.PermissionService;
import com.example.demo.util.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Results<JSONArray> listAllPermission() {
        log.info(getClass().getName() + ".listAllPermission()");
        List datas = permissionDao.findAll();
        JSONArray array = new JSONArray();
        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        TreeUtils.setPermissionsTree(0, datas, array);
        return Results.success(array);
    }

    @Override
    public Results<SysPermission> listAllPermissionByRoleId(Integer roleId) {
        return Results.success(0, permissionDao.listAllPermissionByRoleId(roleId));
    }

    @Override
    public Results getMenuAll() {
        return Results.success(0,permissionDao.findAll());
    }

    @Override
    public Results<SysPermission> save(SysPermission sysPermission) {
        return (permissionDao.save(sysPermission) > 0 ) ? Results.success() : Results.failure();
    }
}
