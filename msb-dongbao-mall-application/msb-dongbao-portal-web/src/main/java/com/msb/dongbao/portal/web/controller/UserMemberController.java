package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserMemberController
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/10 19:57
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user-member")
public class UserMemberController {
    @Autowired
    UmsMemberService umsMemberService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/register")
    public String register(){
        umsMemberService.register();
        return "register";
    }

}
