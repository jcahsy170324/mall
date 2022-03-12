package com.msb.dongbao.ums.service.impl;

import com.msb.dongbao.common.base.enums.StateCodeEnum;
import com.msb.dongbao.common.base.result.ResultWrapper;
import com.msb.dongbao.common.util.JwtUtil;
import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.entity.UserMemberLoginResponse;
import com.msb.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.msb.dongbao.ums.mapper.UmsMemberMapper;
import com.msb.dongbao.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author JinCheng
 * @since 2022-03-10
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    UmsMemberMapper umsMemberMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResultWrapper register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterParamDTO,umsMember);
        String encode = passwordEncoder.encode(umsMemberRegisterParamDTO.getPassword());
        umsMember.setPassword(encode);
        try {
            umsMemberMapper.insert(umsMember);
        }catch (Exception e){
            return ResultWrapper.getFailBuilder().msg("用户名重复").build();
        }
        return ResultWrapper.getSuccessBuilder().build();
    }

    @Override
    public ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
        UmsMember umsMember = umsMemberMapper.selectByName(umsMemberLoginParamDTO.getUsername());
        if (null != umsMember){
            String passwordDb = umsMember.getPassword();
            if (!passwordEncoder.matches(umsMemberLoginParamDTO.getPassword(),passwordDb)){
                return ResultWrapper.getFailBuilder().code(StateCodeEnum.PASSWORD_ERROR.getCode())
                        .msg(StateCodeEnum.PASSWORD_ERROR.getMsg()).build();
            }else {
                String token = JwtUtil.createToken(umsMember.getUsername());
                UserMemberLoginResponse userMemberLoginResponse = new UserMemberLoginResponse();
                userMemberLoginResponse.setToken(token);
                umsMember.setPassword("");
                userMemberLoginResponse.setUmsMember(umsMember);
                return ResultWrapper.getSuccessBuilder().data(userMemberLoginResponse).build();
            }
        }else {
            return ResultWrapper.getFailBuilder().code(StateCodeEnum.USER_EMPTY.getCode())
                    .msg(StateCodeEnum.USER_EMPTY.getMsg()).build();
        }
    }

    @Override
    public ResultWrapper edit(UmsMember umsMember) {
        UmsMember umsMemberDb = umsMemberMapper.selectById(umsMember.getId());
        if (null != umsMemberDb){
            String passwordDb = umsMember.getPassword();
            if(!StringUtils.isBlank(umsMember.getPassword())){
                if (!passwordEncoder.matches(umsMember.getPassword(),passwordDb)){
                    String encode = passwordEncoder.encode(umsMember.getPassword());
                    umsMember.setPassword(encode);
                }
            }
        }
        umsMemberMapper.updateById(umsMember);
        return ResultWrapper.getSuccessBuilder().data(umsMember).build();
    }
}
