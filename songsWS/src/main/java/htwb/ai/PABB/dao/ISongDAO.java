package htwb.ai.PABB.dao;

import htwb.ai.PABB.model.Song;

import java.util.List;

public interface ISongDAO {

    public void addSong(Song song);
    public Song getSong(int id);
    public List<Song> getSongs();
    public boolean updateSong(Song song, int id);
    public boolean deleteSong(int id);

}
