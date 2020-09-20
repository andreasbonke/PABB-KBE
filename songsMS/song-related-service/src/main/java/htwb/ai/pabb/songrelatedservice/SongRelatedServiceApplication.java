package htwb.ai.pabb.songrelatedservice;

import htwb.ai.pabb.songrelatedservice.models.SongRelatedData;
import htwb.ai.pabb.songrelatedservice.repository.SongsRelatedServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SongRelatedServiceApplication implements CommandLineRunner {

    @Autowired
    private SongsRelatedServiceRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SongRelatedServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new SongRelatedData("1", "Songtext eines Songs"));
        repository.save(new SongRelatedData("2", "Songtext eines zweiten Songs"));

        for (SongRelatedData songRelatedData : repository.findAll()) {
            System.out.println(songRelatedData);
        }
    }
}
