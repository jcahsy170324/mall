package com.msb.dongbao.portal.web.controller.studyCaptcha;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.ramostear.captcha.HappyCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName HappyCaptchaController
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/13 16:15
 * @Version 1.0
 **/
@RestController
@RequestMapping("/easy-captcha")
public class EasyCaptchaController {
    String attrName = "verifyCode";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/verify-redis")
    @TokenCheck(required = false)
    public String verifyRedis(Map<String,String> map, HttpServletRequest request) {
        String uuid = map.get("uuid");
        String s = stringRedisTemplate.opsForValue().get(uuid);

        if (map.get("base64").equals(s)) {
            HappyCaptcha.remove(request);
            return "通过";
        }
        return "不通过";
    }

    @GetMapping("/generator-redis")
    @TokenCheck(required = false)
    public Map<String,String> generatorCodeRedis(HttpServletRequest request, HttpServletResponse response) {
        SpecCaptcha specCaptcha = new SpecCaptcha(100, 50);
        String text = specCaptcha.text();
        System.out.println("验证码:" + text);
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(uuid, text);
        String s = specCaptcha.toBase64();
        Map<String,String> map = new HashMap<>();
        map.put("uuid",uuid);
        map.put("base64",s);
        return map;
    }

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            CaptchaUtil.out(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {
        Boolean aBoolean = CaptchaUtil.ver(verifyCode, request);
        if (aBoolean) {
            HappyCaptcha.remove(request);
            return "通过";
        }
        return "不通过";
    }


}
