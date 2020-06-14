package com.exam.zy613;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 工程启动类
 * @author 31515
 */
@SpringBootApplication
@MapperScan(basePackages = "com.exam.zy613.mapper")
public class Zy613Application {

    public static void main(String[] args) {
        SpringApplication.run(Zy613Application.class, args);
    }

}
