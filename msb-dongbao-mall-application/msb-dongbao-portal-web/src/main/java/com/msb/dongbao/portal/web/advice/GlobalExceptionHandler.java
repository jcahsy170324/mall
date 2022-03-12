package com.msb.dongbao.portal.web.advice;

import com.msb.dongbao.common.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/11 16:18
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResultWrapper customException(){

        return ResultWrapper.builder().code(301).msg("统一异常").build();
    }
}
