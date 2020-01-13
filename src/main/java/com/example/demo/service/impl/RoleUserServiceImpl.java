package com.example.demo.service.impl;

import com.example.demo.base.result.Results;
import com.example.demo.dao.RoleUserDao;
import com.example.demo.model.SysRoleUser;
import com.example.demo.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public Results getRoleUserByUserId(Integer userId) {

        SysRoleUser sysRoleUser = roleUserDao.getRoleUserByUserId(userId);

        if(sysRoleUser != null){
            return Results.success(sysRoleUser);
        }else{
            return Results.success();
        }

    }
}
