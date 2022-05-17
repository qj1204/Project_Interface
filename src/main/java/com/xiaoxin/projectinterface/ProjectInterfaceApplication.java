package com.xiaoxin.projectinterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 14290
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.xiaoxin.projectinterface.mapper"})
@EnableTransactionManagement
public class ProjectInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectInterfaceApplication.class, args);
    }

}
