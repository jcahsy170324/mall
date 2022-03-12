package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.common.base.result.ResultWrapper;
import com.msb.dongbao.common.util.JwtUtil;
import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.msb.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResultWrapper register(@RequestBody @Valid UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){
        ResultWrapper register = umsMemberService.register(umsMemberRegisterParamDTO);
        return register;
    }

    @PostMapping("/login")
    public ResultWrapper login(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
        ResultWrapper login = umsMemberService.login(umsMemberLoginParamDTO);
        return login;
    }

    @GetMapping("/verify")
    public String verify(String token){
        String s = JwtUtil.parseToken(token);
        return s;
    }

    @PostMapping("/edit")
    public ResultWrapper edit(@RequestBody UmsMember umsMember){
        System.out.println("edit");
        return umsMemberService.edit(umsMember);
    }

}
