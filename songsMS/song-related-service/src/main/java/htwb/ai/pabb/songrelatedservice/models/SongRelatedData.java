package htwb.ai.pabb.songrelatedservice.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class SongRelatedData {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public SongRelatedData(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
}
