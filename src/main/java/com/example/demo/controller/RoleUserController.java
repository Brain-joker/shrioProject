package com.example.demo.controller;


import com.example.demo.base.result.Results;
import com.example.demo.service.RoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   //相当于@Controller&@ResponseBody
@RequestMapping("/roleuser")
@Slf4j
public class RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    @PostMapping(value = "/getRoleUserByUserId")
    //@RestController定义之后 不用再写@ResponseBody
    public Results getRoleUserByUserId(Integer userId) {
        log.info("RoleUserController.getRoleUserByUserId:param = " + userId);
        return roleUserService.getRoleUserByUserId(userId);
    }

}
