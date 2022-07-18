package com.ciandt.summit.bootcamp2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableWebMvc
@SpringBootApplication
@EnableJpaRepositories("com.ciandt.summit.bootcamp2022.*")
@ComponentScan(basePackages = { "com.ciandt.summit.bootcamp2022.*" })
@EntityScan("com.ciandt.summit.bootcamp2022.*")

public class SummitBootcampApplication {
    public static void main(String[] args) {
        SpringApplication.run(SummitBootcampApplication.class, args);
    }
}
