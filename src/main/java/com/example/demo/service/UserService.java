package com.example.demo.service;

import com.example.demo.base.result.Results;
import com.example.demo.dto.UserDto;
import com.example.demo.model.SysUser;

public interface UserService {

    SysUser getUser(String username);

    Results<SysUser> getAllUsersByPage(Integer offset, Integer limit);

    Results<SysUser> save(SysUser sysUser, Integer roleId);

    SysUser getUserByPhone(String telephone);

    SysUser getUserByEmail(String email);
}
