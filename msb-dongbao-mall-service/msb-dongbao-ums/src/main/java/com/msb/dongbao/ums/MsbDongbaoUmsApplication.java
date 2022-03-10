package com.msb.dongbao.ums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName MsbDongbaoUmsApplication
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/10 14:30
 * @Version 1.0
 **/

@SpringBootApplication
@MapperScan("com.msb.dongbao.ums.mapper")
public class MsbDongbaoUmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsbDongbaoUmsApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
