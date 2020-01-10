package com.example.demo.controller;

import com.example.demo.base.result.PageTableRequest;
import com.example.demo.base.result.ResponseCode;
import com.example.demo.base.result.Results;
import com.example.demo.dto.UserDto;
import com.example.demo.model.SysUser;
import com.example.demo.service.UserService;
import com.example.demo.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    @ResponseBody                //返回参数为json字符串
    public SysUser user(@PathVariable String username) {
        log.info("UserController.user(): param ( username = " + username +" )");
        return userService.getUser(username);
    }

    @GetMapping("/list")
    @ResponseBody
    public Results<SysUser> getUsers(PageTableRequest pageTableRequest) {
        log.info("UserController.getUsers():param(pageTableRequest1 = "+pageTableRequest+")");
        pageTableRequest.countOffset();
        return userService.getAllUsersByPage( pageTableRequest.getOffset(),pageTableRequest.getLimit());
    }

    //页面跳转  跳转到用户添加页面
    @GetMapping("/add")
    public String addUser(Model model) {
      model.addAttribute(new SysUser());
      return "user/user-add";
    }

    //添加用户信息
    @PostMapping(value = "/add")
    @ResponseBody
    public Results<SysUser> saveUser(UserDto userDto, Integer roleId) {
        userDto.setStatus(1);
        userDto.setPassword(MD5.crypt(userDto.getPassword()));
        return userService.save(userDto,roleId);
    }

    String pattern = "yyyy-MM-dd";

    //String日期转date
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern), true));// CustomDateEditor为自定义日期编辑器
    }

}
