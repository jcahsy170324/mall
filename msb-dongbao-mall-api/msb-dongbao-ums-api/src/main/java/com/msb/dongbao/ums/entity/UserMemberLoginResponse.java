package com.msb.dongbao.ums.entity;

import lombok.Data;

/**
 * @ClassName UserMemberLoginResponse
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/12 16:19
 * @Version 1.0
 **/
@Data
public class UserMemberLoginResponse {
    private String token;

    private UmsMember umsMember;
}
