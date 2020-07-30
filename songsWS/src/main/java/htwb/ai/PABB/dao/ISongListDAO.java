package htwb.ai.PABB.dao;

import htwb.ai.PABB.model.SongList;

import java.util.List;

public interface ISongListDAO {

    public void addSongList(SongList songList);
    public SongList getSongList(int id);
    public List<SongList> getSongList(String ownerid);
    public boolean deleteSong(int id);
    public boolean updateSongList(SongList songList,int id);
    //public Song findSongById(Integer id);

    }
