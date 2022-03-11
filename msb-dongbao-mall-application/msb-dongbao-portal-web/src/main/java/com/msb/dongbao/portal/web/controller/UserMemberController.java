package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.msb.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    public String register(@RequestBody UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){
        umsMemberService.register(umsMemberRegisterParamDTO);
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
        String login = umsMemberService.login(umsMemberLoginParamDTO);
        return login;
    }

}
