package com.xll.sbshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xll.sbshiro.mapper")
public class SbShiroFreemarkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbShiroFreemarkerApplication.class, args);
    }

}
