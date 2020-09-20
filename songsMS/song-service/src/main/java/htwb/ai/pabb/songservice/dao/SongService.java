package htwb.ai.pabb.songservice.dao;

import htwb.ai.pabb.songservice.models.Song;
import htwb.ai.pabb.songservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    /**
     * Gibt einen bestimmten Song mit entsprechender Id zurück
     *
     * @param id
     * @return
     */
    public Song getSong(int id) {
        return songRepository.findById(id).get();
    }

    /**
     * Gibt alle Songs aus der Datenbank zurück
     *
     * @return
     */
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        songRepository.findAll().forEach(songs::add);
        return songs;
    }

    /**
     * Fügt einen neune Song der Datenbank hinzu
     *
     * @param song
     */
    public void addSong(Song song) {
        songRepository.save(song);
    }

    /**
     * Ändert einen gegebenen Song in der Datenbank
     *
     * @param id
     * @param song
     * @return
     */
    public boolean updateSong(int id, Song song) {
        try {
            songRepository.save(song);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Löscht einen bestimmten Song mit entsprechender Id aus der Datenbank
     *
     * @param id
     * @return
     */
    public boolean deleteSong(int id) {
        try {
            songRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
