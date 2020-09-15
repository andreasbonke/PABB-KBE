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

    public Song getSong(int id) {
        return songRepository.findById(id).get();
    }

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        songRepository.findAll().forEach(songs::add);
        return songs;
    }

    public void addSong(Song song){
        songRepository.save(song);
    }

    public void updateSong(int id, Song song){
        songRepository.save(song);
    }

    public void deleteSong(int id){
        songRepository.deleteById(id);
    }
}
