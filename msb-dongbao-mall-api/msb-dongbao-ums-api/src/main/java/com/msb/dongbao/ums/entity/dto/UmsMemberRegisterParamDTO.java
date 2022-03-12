package com.msb.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @Size(min = 1,max = 64,message = "用户名长度在1-64之间")
    private String username;
    private String password;
    private String icon;
    @Email
    private String email;
    private String nickName;
    private String note;

}
