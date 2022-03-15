package com.msb.dongbao.portal.web.controller.studyCaptcha;

import com.baomidou.kaptcha.Kaptcha;
import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.portal.web.custom.MyGoogleKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName KcaptchaController
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/14 16:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/kcaptcha")
public class KcaptchaController {



    @Autowired
    private Kaptcha kaptcha;

    @Autowired
    private MyGoogleKaptcha myGoogleKaptcha;

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        kaptcha.render();
    }


    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {
        Boolean aBoolean = kaptcha.validate(verifyCode);
        if (aBoolean) {
            return "通过";
        }
        return "不通过";
    }

    @GetMapping("/generator-my")
    @TokenCheck(required = false)
    public void generatorCodeMy(HttpServletRequest request, HttpServletResponse response) {
        myGoogleKaptcha.render();
    }


    @GetMapping("/verify-my")
    @TokenCheck(required = false)
    public String verifyMy(String verifyCode, HttpServletRequest request) {
        Boolean aBoolean = myGoogleKaptcha.validate(verifyCode);
        if (aBoolean) {
            return "通过";
        }
        return "不通过";
    }
}
