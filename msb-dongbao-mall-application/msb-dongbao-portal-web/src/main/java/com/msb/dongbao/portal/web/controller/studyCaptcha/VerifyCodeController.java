package com.msb.dongbao.portal.web.controller.studyCaptcha;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.portal.web.code.ImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @ClassName VerifyCodeController
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/13 10:11
 * @Version 1.0
 **/
@RestController
@RequestMapping("/code")
public class VerifyCodeController {

    String attrName = "verifyCode";

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImageCode imageCode = ImageCode.getInstance();
            //验证码的值
            String code = imageCode.getCode();
            request.getSession().setAttribute(attrName, code);
            //验证码图片
            ByteArrayInputStream image = imageCode.getImage();
            response.setContentType("image/jpeg");
            byte[] bytes = new byte[1024];
            try {
                ServletOutputStream out = response.getOutputStream();
                while (image.read(bytes) != -1) {
                    out.write(bytes);
                }
            } catch (IOException e) {

            }
        } catch (Exception e) {
            System.out.println("异常");
        }
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {
        String s = request.getSession().getAttribute(attrName).toString();
        if (verifyCode.equals(s)) {
            return "校验码校验通过";
        }
        return "验证码校验不通过";
    }


    @GetMapping("/generator-base64")
    @TokenCheck(required = false)
    public String generatorCodeBase64(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImageCode imageCode = ImageCode.getInstance();
            //验证码的值
            String code = imageCode.getCode();
            request.getSession().setAttribute(attrName, code);
            //验证码图片
            ByteArrayInputStream image = imageCode.getImage();

            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int r= 0;
            while ((r=image.read(buff,0,1024)) > 0){
                swapStream.write(buff, 0, r);
            }

            byte[] data = swapStream.toByteArray();
            return Base64.getEncoder().encodeToString(data);

        } catch (Exception e) {
            System.out.println("异常");
            return "";
        }
    }
}
