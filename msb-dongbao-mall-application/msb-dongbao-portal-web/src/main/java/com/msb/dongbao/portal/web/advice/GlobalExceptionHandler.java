package com.msb.dongbao.portal.web.advice;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.msb.dongbao.common.base.TokenException;
import com.msb.dongbao.common.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/11 16:18
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public ResultWrapper customException() {

        return ResultWrapper.builder().code(301).msg("统一异常").build();
    }

    @ExceptionHandler(TokenException.class)
    public ResultWrapper loginException(Exception e){
        return ResultWrapper.getFailBuilder().msg(e.getMessage()).build();
    }

    @ExceptionHandler(KaptchaException.class)
    public String kcaptchaException(KaptchaException e){
        if (e instanceof KaptchaTimeoutException){
            return "超时";
        }else if (e instanceof KaptchaIncorrectException) {
            return "不正确";
        }else if (e instanceof KaptchaNotFoundException) {
            return "没找到";
        }else {
            return "反正错了";
        }

    }


}
