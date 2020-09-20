package htwb.ai.pabb.songservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String artist;
    private String label;
    private String released;

    public Song() {

    }

    public Song(int id, String title, String artist, String label, String released) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.released = released;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id == song.id &&
                Objects.equals(title, song.title) &&
                Objects.equals(artist, song.artist) &&
                Objects.equals(label, song.label) &&
                Objects.equals(released, song.released);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist, label, released);
    }
}
