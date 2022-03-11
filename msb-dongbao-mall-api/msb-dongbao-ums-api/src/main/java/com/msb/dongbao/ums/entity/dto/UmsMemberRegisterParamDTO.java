package com.msb.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName UmsMemberRegisterParamDTO
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/11 9:58
 * @Version 1.0
 **/
@Data
@ToString
public class UmsMemberRegisterParamDTO {
    @NotEmpty(message = "用户名密码不为空")
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;
    private String note;

}
