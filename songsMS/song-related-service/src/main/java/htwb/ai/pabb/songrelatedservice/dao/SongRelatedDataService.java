package htwb.ai.pabb.songrelatedservice.dao;

import htwb.ai.pabb.songrelatedservice.models.SongRelatedData;
import htwb.ai.pabb.songrelatedservice.repository.SongsRelatedServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongRelatedDataService {

    @Autowired
    private SongsRelatedServiceRepository relatedServiceRepository;

    public SongRelatedData getSongRelatedData(String id){
        return relatedServiceRepository.findById(id).get();
    }
}
