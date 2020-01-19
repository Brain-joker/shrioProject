package com.example.demo.service.impl;

import com.example.demo.base.result.Results;
import com.example.demo.dao.RoleDao;
import com.example.demo.dao.RolePermissionDao;
import com.example.demo.dto.RoleDto;
import com.example.demo.model.SysRole;
import com.example.demo.model.SysUser;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
//事务
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired(required = true)
    private RolePermissionDao rolePermissionDao;

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

    @Override
    public Results<SysRole> save(RoleDto roleDto) {
        //先保存角色
        roleDao.save(roleDto);
        List<Long> permissionIds = roleDto.getPermissionIds();
        //移除0,permission id是从1开始
        permissionIds.remove(0L);

        //保存角色权限信息
        if (!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.save(roleDto.getId(), permissionIds);
        }
        return Results.success();
    }
}
