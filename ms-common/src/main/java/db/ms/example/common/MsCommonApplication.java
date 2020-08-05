package db.ms.example.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCommonApplication.class, args);
    }

}
