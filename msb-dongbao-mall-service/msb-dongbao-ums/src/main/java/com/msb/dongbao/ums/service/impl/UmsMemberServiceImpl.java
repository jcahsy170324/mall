package com.msb.dongbao.ums.service.impl;

import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.mapper.UmsMemberMapper;
import com.msb.dongbao.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String register(){
        UmsMember u = new UmsMember();
        u.setNickName("c");
        umsMemberMapper.insert(u);
        return "success";
    }
}