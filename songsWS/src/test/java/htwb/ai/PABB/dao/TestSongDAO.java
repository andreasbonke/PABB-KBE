package htwb.ai.PABB.dao;

import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestSongDAO implements ISongDAO{

    //private Map<Integer, Song> mySongs= new ConcurrentHashMap<Integer, Song>();
    private List<Song> mySongs = new ArrayList<>();


    @Override
    public void addSong(Song song) {
        //mySongs.put(song.getId(), song);
        mySongs.add(song);
    }

    @Override
    public Song getSong(int id) {
        for(int i = 0; i < mySongs.size(); i++) {
            if(mySongs.get(i).getId() == id) {
                return mySongs.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Song> getSongs() {
        return mySongs;
    }

    @Override
    public boolean updateSong(Song song, int id) {
        for(int i = 0; i < mySongs.size(); i++) {
            if(mySongs.get(i).getId() == id) {
                mySongs.get(i).setTitle(song.getTitle());
                mySongs.get(i).setReleased(song.getReleased());
                mySongs.get(i).setLabel(song.getLabel());
                mySongs.get(i).setArtist(song.getArtist());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteSong(int id) {
        for(int i = 0; i < mySongs.size(); i++) {
            if(mySongs.get(i).getId() == id) {
                mySongs.remove(i);
                return true;
            }
        }
        return false;
    }
}