package htwb.ai.pabb.authservice.repository;

import htwb.ai.pabb.authservice.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadUserDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadUserDatabase.class);

    @Bean
    CommandLineRunner initUserDatabase(UserRepository userRepository) {

        return args -> {
            log.info("Preloading " + userRepository.save(new User(1,"mmuster", "pass1234", "Maxi", "Musterfrau")));
            log.info("Preloading " + userRepository.save(new User(2,"eschuler", "pass1234", "Elena", "Schuler")));

            log.info(userRepository.findAll().toString());
        };
    }
}
