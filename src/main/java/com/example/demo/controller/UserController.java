package com.example.demo.controller;

import com.example.demo.base.result.PageTableRequest;
import com.example.demo.base.result.Results;
import com.example.demo.model.SysUser;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("user")
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

    @GetMapping("/add")
    public String addUser(Model model) {
      model.addAttribute(new SysUser());
      return "user/user-add";
    }
}
