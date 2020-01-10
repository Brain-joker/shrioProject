package com.example.demo.controller;

import com.example.demo.base.result.PageTableRequest;
import com.example.demo.base.result.Results;
import com.example.demo.model.SysRole;
import com.example.demo.model.SysUser;
import com.example.demo.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("/all")
    @ResponseBody
    public Results<SysRole> getAll() {
        log.info("RoleController.getAll())");
        return roleService.getAllRoles();
    }
}
