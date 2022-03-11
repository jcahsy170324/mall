package com.msb.dongbao.portal.web.md5;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * @ClassName EncoderOrDecoderTest
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/11 10:25
 * @Version 1.0
 **/
public class EncoderOrDecoderTest {
    @Test
    public void encodeAndDecode() {
        String sourceString = "123456";
        String s = DigestUtils.md5DigestAsHex(sourceString.getBytes());
        //s和数据库里的值做对比
        System.out.println("第一次加密值:" + s);
         s = DigestUtils.md5DigestAsHex(sourceString.getBytes());
        System.out.println("第二次加密值:" + s);
    }

    @Test
    public void bcrypt(){
        String sourceString = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(sourceString);
        System.out.println("第一次加密的值:"+encode);
        boolean matches = encoder.matches(sourceString, encode);
        System.out.println("第一次验证:"+matches);
        encode = encoder.encode(sourceString);
        System.out.println("第二次加密的值:"+encode);
        boolean matches1 = encoder.matches(sourceString, encode);
        System.out.println("第二次验证:"+matches1);

    }
}
