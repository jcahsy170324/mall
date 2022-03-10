package com.msb.dongbao.ums.mapper;

import com.msb.dongbao.ums.entity.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @ClassName UserMemberTest
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/10 14:08
 * @Version 1.0
 **/
@SpringBootTest
public class UserMemberTest {
    @Autowired
    UmsMemberMapper umsMemberMapper;
    @Test
    void testInsert(){
        UmsMember t = new UmsMember();
        t.setUsername("jincheng");
        t.setStatus(0);
        t.setPassword("1");
        t.setNote("note");
        t.setNickName("nick");
        t.setEmail("email");
        t.setGmtCreate(new Date());
        t.setGmtUpdate(new Date());

        umsMemberMapper.insert(t);

    }
}
