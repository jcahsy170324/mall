package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.portal.web.util.SliderUtil;
import com.msb.dongbao.portal.web.util.VerificationVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MySliderController
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/14 19:16
 * @Version 1.0
 **/
@RestController
@RequestMapping("/my-slider")
public class MySliderController {

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public VerificationVO generatorCode(HttpServletRequest request, HttpServletResponse response) {
        return SliderUtil.cut();
    }


    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {

        return "不通过";
    }
}
