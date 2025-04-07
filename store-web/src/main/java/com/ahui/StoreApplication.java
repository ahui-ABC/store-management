package com.ahui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName 启动类$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.ahui.store",
        "com.ahui.service"
})
@MapperScan("com.ahui.store.mapper")
public class StoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }
}
