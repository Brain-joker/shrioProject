package com.example.demo.service;

import com.example.demo.base.result.Results;
import com.example.demo.model.SysRole;

public interface RoleService {

    Results<SysRole> getAllRoles();
}
