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

    public SongList getSongList(int id) {
        return songListRepository.findById(id).get();
    }

    public List<SongList> getSongList(String ownerId) {
        List<SongList> songLists = new ArrayList<>();
        songListRepository.findAll().forEach(songLists::add);

        //TODO: gibt alle zurück, anstatt des Gesuchten
        songLists.stream().filter(songList -> songList.getOwnerId().equals(ownerId));
        return songLists;
    }

    public void addSongList(SongList songList) {
        songListRepository.save(songList);
    }

    public boolean updateSongList(int id, SongList songList) {
        try {
            songListRepository.save(songList);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSongLists(int id) {
       try {
           songListRepository.deleteById(id);
           return true;
       } catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }
}
