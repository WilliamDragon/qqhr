package com.qqhr.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@EnableDubboConfiguration
@MapperScan("com.qqhr.*")
@ImportResource("classpath:properties/dubbo-server.xml")
@SpringBootApplication(scanBasePackages={"com.qqhr.*"})
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
