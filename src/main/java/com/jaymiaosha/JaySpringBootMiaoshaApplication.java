package com.jaymiaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
@MapperScan("com.jaymiaosha.dao")
public class JaySpringBootMiaoshaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaySpringBootMiaoshaApplication.class, args);
    }
}
