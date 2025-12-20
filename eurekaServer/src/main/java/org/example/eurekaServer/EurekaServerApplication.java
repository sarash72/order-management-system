package org.example.eurekaServer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(     scanBasePackages = {"org.example.eurekaServer"},

        exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
})
@EnableEurekaServer
//@EnableJpaRepositories(basePackages = {"org.example.order.repository", "org.example.product.repository"})
//@EntityScan(basePackages = {"org.example.order.entity", "org.example.product.entity"})


public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }}