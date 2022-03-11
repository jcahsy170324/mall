package com.msb.dongbao.portal.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.*;

/**
 * @ClassName MsbDongbaoPortalWebApplication
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/10 20:00
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"com.msb"})
@MapperScan("com.msb.dongbao.ums.mapper")
public class MsbDongbaoPortalWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsbDongbaoPortalWebApplication.class,args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
