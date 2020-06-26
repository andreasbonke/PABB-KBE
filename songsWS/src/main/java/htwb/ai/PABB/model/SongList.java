package htwb.ai.PABB.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XmlRootElement
@Entity
@Table(name = "songlist")
public class SongList implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerid")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "isPrivate")
    @JsonProperty("isPrivate")
    @XmlAttribute(name = "isPrivate")
    private Boolean isPrivate;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @Column(name = "songs")
    @JoinTable(name = "songlist_songid",
           joinColumns = {@JoinColumn(name = "listid", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "songid", referencedColumnName = "id")})
    @JsonProperty("songList")
    private Set<Song> songs = new HashSet<Song>();

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
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

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
