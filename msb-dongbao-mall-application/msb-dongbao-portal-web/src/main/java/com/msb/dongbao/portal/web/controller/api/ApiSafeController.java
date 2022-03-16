package com.msb.dongbao.portal.web.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ApiSafeController
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/16 19:41
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api-safe")
public class ApiSafeController {

    @GetMapping("/hello")
    public String hello(){
        return "hello api safe";
    }
}
