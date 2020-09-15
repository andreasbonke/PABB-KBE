package htwb.ai.pabb.songservice.repository;

import htwb.ai.pabb.songservice.models.SongList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadSongListDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadSongListDatabase.class);

    @Bean
    CommandLineRunner initSongListDatabase(SongListRepository songListRepository) {
        return args -> {
            log.info("Preloading " + songListRepository.save(new SongList(1, "mmuster","MusterPrivate",true)));
            log.info("Preloading " + songListRepository.save(new SongList(2, "mmuster","MusterPublic",false)));
            log.info("Preloading " + songListRepository.save(new SongList(3, "eschuler","SchulerPrivate",true)));
            log.info("Preloading " + songListRepository.save(new SongList(4, "eschuler","SchulerPublic",false)));
        };
    }
}
