package com.example.demo.service;

import com.example.demo.base.result.Results;
import com.example.demo.dto.RoleDto;
import com.example.demo.model.SysRole;
import com.example.demo.model.SysRoleUser;
import com.example.demo.model.SysUser;

import java.util.List;

public interface RoleService {

    Results<SysRole> getAllRoles();

    Results<SysRole> getAllRolesByPage(Integer offset, Integer limit);

    Results findRoleByFuzzyRoleName(String roleName, Integer offset, Integer limit);

    Results<SysRole> save(RoleDto roleDto);

    SysRole getRoleById(Integer id);

    Results updateRole(RoleDto roleDto);

    Results deleteRole(Integer id);
}
