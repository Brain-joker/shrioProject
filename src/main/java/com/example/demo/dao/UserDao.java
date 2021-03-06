package com.example.demo.dao;

import com.example.demo.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from sys_user t where t.username = #{username}")
    SysUser getUser(String username);


    @Select("select * from sys_user t order by t.id limit #{startPosition},#{limit} ")
    List<SysUser> getAllUsersByPage(@Param("startPosition") Integer startPosition,
                                    @Param("limit") Integer limit);

    @Select("select count(*) from sys_user t")
    Long countAllUsers();


    //@Options(useGeneratedKeys = true, keyProperty = "id")  id主键自增
    //@Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
    int sava(SysUser sysUser);

    @Select("select * from sys_user t where t.telephone = #{telephone}")
    SysUser getUserByPhone(String telephone);

    //@Select("select * from sys_user t where t.email = #{email}")
    SysUser getUserByEmail(String email);

    @Select("select * from sys_user t where t.id = #{id}")
    SysUser getUserById(Long id);

    int updateUser(SysUser sysUser);

    @Delete("delete from sys_user where id = #{userId}")
    int deleteUser(int userId);

    @Select("select count(*) from sys_user t where t.username like '%${username}%'")
    Long countUserByFuzzyUserName(String username);

    @Select("select * from sys_user t where t.username like '%${username}%' limit #{startPosition},#{limit}")
    List<SysUser> getUserByFuzzyUserNamePage(@Param("username") String username,@Param("startPosition")Integer startPosition,@Param("limit")Integer limit);
}
