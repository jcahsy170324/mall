package com.msb.dongbao.common.base.result;

import com.msb.dongbao.common.base.enums.StateCodeEnum;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;


import java.io.Serializable;

/**
 * @ClassName ResultWrapper
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/11 14:10
 * @Version 1.0
 **/
@Builder
@Data
public class ResultWrapper<T> implements Serializable {
    //状态码
    private int code;

    //提示信息
    private String msg;

    private T data;

    @Tolerate
    public ResultWrapper() {
    }

    /**
     * 返回成功的包装
     **/
    public static ResultWrapper.ResultWrapperBuilder getSuccessBuilder(){
        return ResultWrapper.builder().code(StateCodeEnum.SUCCESS.getCode()).msg(StateCodeEnum.SUCCESS.getMsg());
    }

    public static ResultWrapper.ResultWrapperBuilder getFailBuilder() {
        return ResultWrapper.builder().code(StateCodeEnum.ERROR.getCode()).msg(StateCodeEnum.ERROR.getMsg());
    }

}
