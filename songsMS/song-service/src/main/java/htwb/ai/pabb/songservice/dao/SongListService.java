package htwb.ai.pabb.songservice.dao;

import htwb.ai.pabb.songservice.models.SongList;
import htwb.ai.pabb.songservice.repository.SongListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongListService {

    @Autowired
    private SongListRepository songListRepository;

    /**
     * Gibt eine Song Liste mit einer bestimmten ID zurück
     *
     * @param id
     * @return
     */
    public SongList getSongList(int id) {
        return songListRepository.findById(id).get();
    }

    /**
     * Gibt alle Song Listen des übergebenen Users zurück
     *
     * @param ownerId
     * @return
     */
    public List<SongList> getSongList(String ownerId) {
        List<SongList> songLists = new ArrayList<>();
        songListRepository.findAll().forEach(songLists::add);

        //TODO: gibt alle zurück, anstatt des Gesuchten
        songLists.stream().filter(songList -> songList.getOwnerId().equals(ownerId));
        return songLists;
    }

    /**
     * Fügt eine neue Song Liste in die Datenbank hinzu
     *
     * @param songList
     */
    public void addSongList(SongList songList) {
        songListRepository.save(songList);
    }

    /**
     * Ändert eine gegebene Song Liste in der Datenbank
     *
     * @param id
     * @param songList
     * @return
     */
    public boolean updateSongList(int id, SongList songList) {
        try {
            songListRepository.save(songList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Löscht eine Song Liste mit einer entsprechenden Id aus der Datenbank
     *
     * @param id
     * @return
     */
    public boolean deleteSongLists(int id) {
        try {
            songListRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
