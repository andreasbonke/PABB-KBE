package htwb.ai.PABB.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class SongListCollection {
    private List<SongList> songList;

    public List<SongList> getSongList() {
        return songList;
    }

    public void setSongList(List<SongList> songList) {
        this.songList = songList;
    }
}
