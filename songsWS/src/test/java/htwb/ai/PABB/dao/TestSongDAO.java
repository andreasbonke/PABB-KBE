package htwb.ai.PABB.dao;

import htwb.ai.PABB.model.Song;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestSongDAO implements ISongDAO{


    @Override
    public void addSong(Song song) {

    }

    @Override
    public Song getSong(int id) {
        return null;
    }

    @Override
    public List<Song> getSongs() {
        return null;
    }

    @Override
    public boolean updateSong(Song song, int id) {
        return false;
    }

    @Override
    public boolean deleteSong(int id) {
        return false;
    }
}