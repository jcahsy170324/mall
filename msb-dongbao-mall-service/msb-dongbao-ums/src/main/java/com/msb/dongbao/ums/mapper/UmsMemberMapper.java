package com.msb.dongbao.ums.mapper;

import com.msb.dongbao.ums.entity.UmsMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author JinCheng
 * @since 2022-03-10
 */
@Repository
public interface UmsMemberMapper extends BaseMapper<UmsMember> {

    UmsMember selectByName(String username);
}
