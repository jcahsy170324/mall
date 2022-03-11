package com.msb.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName UmsMemberRegisterParamDTO
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/11 9:58
 * @Version 1.0
 **/
@Data
@ToString
public class UmsMemberLoginParamDTO {
    private String username;
    private String password;
}
