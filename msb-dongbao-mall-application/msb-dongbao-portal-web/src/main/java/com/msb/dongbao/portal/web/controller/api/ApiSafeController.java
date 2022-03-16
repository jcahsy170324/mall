package com.msb.dongbao.portal.web.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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

    /**
     * @Author jincheng
     * @Description //测试参数防篡改
     **/
    @RequestMapping("get-test")
    public String getTest(String appId,String name,String sign){
        HashMap<String,Object> map = new HashMap<>();
        map.put("appId",appId);
        map.put("name",name);
        String s = CheckUtils.generatorSign(map);
        if (s.equals(sign)){
            return "校验通过";
        }else {
            return "校验不通过";
        }
    }
}
