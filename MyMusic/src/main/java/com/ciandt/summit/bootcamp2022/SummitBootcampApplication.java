package com.ciandt.summit.bootcamp2022;

import com.ciandt.summit.bootcamp2022.config.LogConfig;
import com.ciandt.summit.bootcamp2022.config.LogType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@EnableWebMvc
@SpringBootApplication
public class SummitBootcampApplication {

    private static final LogConfig log = new LogConfig(SummitBootcampApplication.class);
    public static void main(String[] args) {

        SpringApplication.run(SummitBootcampApplication.class, args);
        log.create(LogType.INFO, "Iniciando a aplicacao MyMusic! \nAcesse a documentação pela URL: http://localhost:9090/api/swagger-ui.html");
    }
}
