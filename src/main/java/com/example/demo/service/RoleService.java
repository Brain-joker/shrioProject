package com.example.demo.service;

import com.example.demo.base.result.Results;
import com.example.demo.model.SysRole;
import com.example.demo.model.SysUser;

import java.util.List;

public interface RoleService {

    Results<SysRole> getAllRoles();

    Results<SysRole> getAllRolesByPage(Integer offset, Integer limit);

    Results findRoleByFuzzyRoleName(String roleName, Integer offset, Integer limit);
}
