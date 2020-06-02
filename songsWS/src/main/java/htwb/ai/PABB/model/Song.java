package htwb.ai.PABB.model;

import com.sun.istack.NotNull;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "songs")
public class Song implements Serializable {

    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private int id;

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "artist")
    private String artist;

    @Column(name = "label")
    private String label;

    @Column(name = "released")
    private int released;

    /*public static long getSerialVersionUID() {
        return serialVersionUID;
    }*/

    public int getId() { return id; }

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

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }
}
