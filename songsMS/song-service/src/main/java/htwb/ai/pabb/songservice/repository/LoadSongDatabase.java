package htwb.ai.pabb.songservice.repository;

import htwb.ai.pabb.songservice.models.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadSongDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadSongDatabase.class);

    @Bean
    CommandLineRunner initSongDatabase(SongRepository songRepository) {
        return args -> {
            log.info("Preloading " + songRepository.save(new Song(1, "MacArthur Park", "Richard Harris", "Dunhill Records", "1968")));
            log.info("Preloading " + songRepository.save(new Song(2, "Afternoon Delight", "Starland Vocal Band", "Windsong", "1976")));
            log.info("Preloading " + songRepository.save(new Song(3, "Muskrat Love", "Captain and Tennille", "A&M", "1976")));
            log.info("Preloading " + songRepository.save(new Song(4, "Sussudio", "Phil Collins", "Virgin", "1985")));
            log.info("Preloading " + songRepository.save(new Song(5, "We Built This City", "Starship", "Grunt/RCA", "1985")));
            log.info("Preloading " + songRepository.save(new Song(6, "Achy Breaky Heart", "Billy Ray Cyrus", "PolyGram Mercury", "1992")));
            log.info("Preloading " + songRepository.save(new Song(7, "Whats Up?", "4 Non Blondes", "Interscope", "1993")));
            log.info("Preloading " + songRepository.save(new Song(8, "Who Let the Dogs Out?", "Baha Men", "S-Curve", "2000")));
            log.info("Preloading " + songRepository.save(new Song(9, "My Humps", "Black Eyed Peas", "Universal Music", "2005")));
            log.info("Preloading " + songRepository.save(new Song(10, "Chinese Food", "Alison Gold", "PMW Live", "2013")));
        };
    }
}
