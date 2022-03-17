package com.msb.dongbao.portal.web.controller.api;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.msb.dongbao.portal.web.controller.api.posttest.SignDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
    public String hello() {
        return "hello api safe";
    }

    /**
     * @Author jincheng
     * @Description //测试参数防篡改
     **/
    @RequestMapping("get-test")
    public String getTest(String appId, String name, String sign, long timeStamp, HttpServletRequest httpServletRequest) {
        //参数写死
        HashMap<String, String> map = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            //获取name
            String parameterName = parameterNames.nextElement();
            //获取值
            String parameter = httpServletRequest.getParameter(parameterName);
            map.put(parameterName, parameter);
        }
//        long time = System.currentTimeMillis() - timeStamp;
//        if (time > 1000*30){
//            return "接口过期了";
//        }
        boolean b = CheckUtils.checkSign(map);
        return b == true ? "检验通过" : "检验不通过";
    }

    @PostMapping("/post-test")
    public String postTest(@RequestBody SignDTO signDTO) {
        JSONObject obj = JSONUtil.parseObj(signDTO);
        System.out.println("controller参数"+obj);
        return "post-test";
//        //参数转map
//        Map<String, String> stringObjectMap = Convert.toMap(String.class, String.class, obj);
//        //排序
//        Map<String, String> stringObjectMap1 = CheckUtils.sortMapByKey(stringObjectMap);
//        System.out.println(stringObjectMap1);
//        Object signClient = stringObjectMap1.get("sign");
//        //map生成签名
//        String signServer = CheckUtils.generatorSign(stringObjectMap1);
//        //判断生成签名
//        if (signClient.equals(signServer)) {
//            return "校验通过";
//        } else {
//            return "校验不通过";
//        }
    }
}
