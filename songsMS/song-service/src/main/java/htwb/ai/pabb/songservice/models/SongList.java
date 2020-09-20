package htwb.ai.pabb.songservice.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class SongList implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String ownerId;
    private String name;
    private Boolean isPrivate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "songlist_songid", joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "songlist_id", referencedColumnName = "id"))
    private List<Song> songs;

    public SongList() {

    }

    public SongList(int id, String ownerId, String name, Boolean isPrivate) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.isPrivate = isPrivate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongList songList = (SongList) o;
        return id == songList.id &&
                Objects.equals(ownerId, songList.ownerId) &&
                Objects.equals(name, songList.name) &&
                Objects.equals(isPrivate, songList.isPrivate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, name, isPrivate);
    }
}