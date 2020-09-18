package htwb.ai.pabb.songrelatedservice.repository;

import htwb.ai.pabb.songrelatedservice.models.SongRelatedData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SongsRelatedServiceRepository extends MongoRepository<SongRelatedData, String> {
    public Optional<SongRelatedData> findById(String id);
}
