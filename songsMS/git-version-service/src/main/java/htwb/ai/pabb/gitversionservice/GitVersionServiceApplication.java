package htwb.ai.pabb.gitversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GitVersionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitVersionServiceApplication.class, args);
    }

}