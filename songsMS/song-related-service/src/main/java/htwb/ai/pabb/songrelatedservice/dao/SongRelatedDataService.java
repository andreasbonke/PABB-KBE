package htwb.ai.pabb.songrelatedservice.dao;

import htwb.ai.pabb.songrelatedservice.models.SongRelatedData;
import htwb.ai.pabb.songrelatedservice.repository.SongsRelatedServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongRelatedDataService {

    @Autowired
    private SongsRelatedServiceRepository relatedServiceRepository;

    /**
     * Gibt die Songtexte zurück mit der entsprechenden Song Id
     *
     * @param id
     * @return
     */
    public SongRelatedData getSongRelatedData(String id) {
        try {
            return relatedServiceRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Fügt einen neuen Songtext in die Datenbank hinzu
     * @param songRelatedData
     */
    public void addSongRelatedData(SongRelatedData songRelatedData) {
        relatedServiceRepository.save(songRelatedData);
    }
}
