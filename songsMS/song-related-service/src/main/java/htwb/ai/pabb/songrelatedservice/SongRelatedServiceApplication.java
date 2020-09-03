package htwb.ai.pabb.songrelatedservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SongRelatedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongRelatedServiceApplication.class, args);
    }

}