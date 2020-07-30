package htwb.ai.PABB.dao;

import htwb.ai.PABB.model.SongList;

import java.util.ArrayList;
import java.util.List;

public class TestSongListDAO implements ISongListDAO {

    private List<SongList> mySongLists = new ArrayList<>();
    private int counter = 0;

    @Override
    public void addSongList(SongList songList) {
        counter++;
        songList.setId(counter);
        mySongLists.add(songList);
    }

    @Override
    public SongList getSongList(int id) {
        //SongList songList = null;
        for (int i = 0; i < mySongLists.size(); i++) {
            if (mySongLists.get(i).getId() == id) {
                return mySongLists.get(i);
            }
        }
        return null;
    }

    @Override
    public List<SongList> getSongList(String ownerid) {
        List<SongList> ownerSongLists = new ArrayList<>();
        for (int i = 0; i < mySongLists.size(); i++) {
            if (mySongLists.get(i).getOwnerId().equals(ownerid)) {
                ownerSongLists.add(mySongLists.get(i));
            }
        }
        return ownerSongLists;
    }

    @Override
    public boolean deleteSong(int id) {
        for (int i = 0; i < mySongLists.size(); i++) {
            if (mySongLists.get(i).getId() == id) {
                mySongLists.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateSongList(SongList songList, int id) {
        return false;
    }
}
