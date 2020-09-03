package htwb.ai.pabb.songservice.models;

import java.util.HashSet;
import java.util.Set;

public class SongList {

    private int id;
    private User user;
    private String name;
    private Boolean isPrivate;
    private Set<Song> songs = new HashSet<Song>();

    public SongList(){

    }

    public SongList(int id, User user, String name, Boolean isPrivate, Set<Song> songs) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.isPrivate = isPrivate;
        this.songs = songs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}