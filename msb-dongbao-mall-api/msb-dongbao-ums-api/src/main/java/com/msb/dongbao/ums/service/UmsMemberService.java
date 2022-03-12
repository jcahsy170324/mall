package com.msb.dongbao.ums.service;

import com.msb.dongbao.common.base.result.ResultWrapper;
import com.msb.dongbao.ums.entity.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msb.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author JinCheng
 * @since 2022-03-10
 */
public interface UmsMemberService  {
   ResultWrapper register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

   ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO);

   ResultWrapper edit(UmsMember umsMember);
}
