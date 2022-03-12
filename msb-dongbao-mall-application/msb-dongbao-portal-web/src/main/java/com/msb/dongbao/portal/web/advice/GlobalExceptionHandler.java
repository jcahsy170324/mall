package com.msb.dongbao.portal.web.advice;

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


}
