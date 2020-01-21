package com.example.demo.service.impl;

import com.example.demo.base.result.ResponseCode;
import com.example.demo.base.result.Results;
import com.example.demo.dao.RoleDao;
import com.example.demo.dao.RolePermissionDao;
import com.example.demo.dao.RoleUserDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.RoleDto;
import com.example.demo.model.SysRole;
import com.example.demo.model.SysRoleUser;
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

    @Autowired
    private RoleUserDao roleUserDao;

    @Autowired
    private UserDao userDao;

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

    @Override
    public SysRole getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public Results updateRole(RoleDto roleDto) {

        List<Long> permissionsIds = roleDto.getPermissionIds();
        permissionsIds.remove(0L);
        //1、更新角色权限之前要删除该角色之前的所有权限
        rolePermissionDao.deleteRolePermission(roleDto.getId());
        //2、判断该角色是否有赋予权限值，有就添加"
        if(!CollectionUtils.isEmpty(permissionsIds)){
            rolePermissionDao.save(roleDto.getId(),permissionsIds);
        }
        //3、更新角色表
        int countData = roleDao.update(roleDto);
        if(countData > 0){
            return Results.success();
        }else{
            return Results.failure();
        }
    }

    @Override
    public Results deleteRole(Integer id) {
        List<SysRoleUser> datas = roleUserDao.listAllSysRoleUserByRoleId(id);  //根据roleId查询user
        //如果没有用户关联这个角色  就删除这个角色
        if(datas.size() <= 0){
            roleDao.delete(id);
            return Results.success();
        }
        return Results.failure(ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getCode(),ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getMessage());
    }


}
