package com.msb.dongbao.portal.web.controller.studyCaptcha;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.ramostear.captcha.HappyCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName HappyCaptchaController
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/13 16:15
 * @Version 1.0
 **/
@RestController
@RequestMapping("/happy-captcha")
public class HappyCaptchaController {
    String attrName = "verifyCode";

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request,response).build().finish();

    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {
        Boolean aBoolean = HappyCaptcha.verification(request,verifyCode,true);
        if (aBoolean) {
            HappyCaptcha.remove(request);
            return "通过";
        }
        return "不通过";
    }
}
