package htwb.ai.pabb.songservice.repository;

import htwb.ai.pabb.songservice.models.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Integer> {
}
