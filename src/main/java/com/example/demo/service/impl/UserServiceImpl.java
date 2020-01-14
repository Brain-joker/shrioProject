package com.example.demo.service.impl;

import com.example.demo.base.result.Results;
import com.example.demo.dao.RoleUserDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.model.SysRoleUser;
import com.example.demo.model.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//事务
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public SysUser getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public Results<SysUser> getAllUsersByPage(Integer offset, Integer limit) {
        //返回count  userList 需要封装到Results中
        return Results.success(userDao.countAllUsers().intValue(),userDao.getAllUsersByPage(offset,limit));
    }

    @Override
    public Results<SysUser> save(SysUser sysUser, Integer roleId) {
        //判断roleId不是kong
        if(roleId != null){
            userDao.sava(sysUser);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setUserId(sysUser.getId().intValue());
            roleUserDao.save(sysRoleUser);
            return Results.success();
        }
        return Results.failure();
    }

    @Override
    public SysUser getUserByPhone(String telephone) {
        return userDao.getUserByPhone(telephone);
    }

    @Override
    public SysUser getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public SysUser getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public Results<SysUser> updateUser(UserDto userDto, Integer roleId) {

        if(roleId != null){
            //sys_user updating
            userDao.updateUser(userDto);

            //sys_role_user  updating/saving
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserId(userDto.getId().intValue());
            sysRoleUser.setRoleId(roleId);
            if(roleUserDao.getRoleUserByUserId(userDto.getId().intValue()) != null){
                //update
                roleUserDao.updateSysRoleUser(sysRoleUser);
            }else{
                roleUserDao.save(sysRoleUser);
            }
            return Results.success();
        }else{
            return Results.failure();
        }

    }

    @Override
    public int deleteUser(Long id) {
        //删除roleUser
        roleUserDao.deleteRoleUserByUserId(id.intValue());
        //删除user
        return userDao.deleteUser(id.intValue());
    }

    @Override
    public Results<SysUser> getUserByFuzzyUserName(String username, Integer startPosition, Integer limit) {
        return Results.success(userDao.countUserByFuzzyUserName(username).intValue(),userDao.getUserByFuzzyUserNamePage(username,startPosition,limit));
    }


}
