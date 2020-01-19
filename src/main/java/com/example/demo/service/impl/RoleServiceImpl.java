package com.example.demo.service.impl;

import com.example.demo.base.result.Results;
import com.example.demo.dao.RoleDao;
import com.example.demo.model.SysRole;
import com.example.demo.model.SysUser;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//事务
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Results<SysRole> getAllRoles() {
        return Results.success(roleDao.countAllroles().intValue(),roleDao.getAllRoles());
    }

    @Override
    public Results<SysRole> getAllRolesByPage(Integer offset, Integer limit) {
        return Results.success(roleDao.countAllroles().intValue(),roleDao.getAllRolesByPage(offset,limit));
    }

    @Override
    public Results findRoleByFuzzyRoleName(String roleName, Integer offset, Integer limit) {
        return Results.success(roleDao.countRoleByFuzzyUserName(roleName).intValue(),roleDao.getRoleByFuzzyUserNamePage(roleName,offset,limit));
    }
}
