package com.example.demo.controller;

import com.example.demo.base.result.PageTableRequest;
import com.example.demo.base.result.Results;
import com.example.demo.dto.RoleDto;
import com.example.demo.model.SysRole;
import com.example.demo.model.SysRoleUser;
import com.example.demo.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    @ResponseBody
    public Results list(PageTableRequest request) {
        log.info("RoleController.list(): param ( request = " + request + " )");
        request.countOffset();
        return roleService.getAllRolesByPage(request.getOffset(), request.getLimit());
    }

    @GetMapping("/findRoleByFuzzyRoleName")
    @ResponseBody
    public Results findRoleByFuzzyRoleName(PageTableRequest pageTableRequest, String roleName) {
        log.info("UserController.findUserByFuzzyUserName():param(pageTableRequest1 = " + pageTableRequest + ",username=" + roleName + ")");
        pageTableRequest.countOffset();
        return roleService.findRoleByFuzzyRoleName(roleName, pageTableRequest.getOffset(), pageTableRequest.getLimit());
    }

    @GetMapping("/add")
    public String addRole(Model model) {
        model.addAttribute("sysRole", new SysRole());
        return "role/role-add";
    }

    @PostMapping("/add")
    @ResponseBody
    public Results<SysRole> addRole(@RequestBody RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @GetMapping("/edit")
    public String editRole(Model model,SysRole sysRole) {
        model.addAttribute("sysRole",roleService.getRoleById(sysRole.getId()));
        return "role/role-edit";
    }
    @PostMapping("/edit")
    @ResponseBody
    public Results updateRole(@RequestBody RoleDto roleDto) {
        return roleService.updateRole(roleDto);
    }

    @GetMapping("/delete")
    @ResponseBody
    public Results deleteRole( RoleDto roleDto) {
        return roleService.deleteRole(roleDto.getId());
    }
}
