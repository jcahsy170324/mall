package com.msb.dongbao.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName JwtUtil
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/11 17:27
 * @Version 1.0
 **/
public class JwtUtil {

    private static final String secret = "hhhh";

    /**
     * @Author jincheng
     * @Description //用户名，密码输入正确后，根据主题生成token
     **/
    public static String createToken(String subject) {
        String token = Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 1000*10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }

    public static String parseToken(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String subject = body.getSubject();
        return subject;
    }

    public static void main(String[] args) throws InterruptedException {
        String name = "教育";
        String token = createToken(name);
        System.out.println(token);

        String srcStr = parseToken(token);
        System.out.println("解析出来的值:" + srcStr);

        TimeUnit.SECONDS.sleep(5);

        srcStr = parseToken(token);
        System.out.println("解析出来的值:" + srcStr);
    }
}
