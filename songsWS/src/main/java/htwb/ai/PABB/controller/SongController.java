package htwb.ai.PABB.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.PABB.dao.IAuthenticationDAO;
import htwb.ai.PABB.dao.ISongDAO;
import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.SongCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/songs")
public class SongController {

    @Autowired
    private ISongDAO songDAO;
    @Autowired
    private IAuthenticationDAO authentication;


    /*public SongController(ISongDAO songDAO, IAuthenticationDAO authentication) {
        this.authentication = authentication;
        this.songDAO = songDAO;
    }*/


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> postSong(
            @RequestBody Song song, @RequestHeader("Authorization") String token) throws IOException {

        if (token != null) {
            if (authentication.authenticate(token)) {

                songDAO.addSong(song);
                HttpHeaders responseHeaders = new HttpHeaders();
                if (song.getId() != 0) {
                    String uriString = "http://localhost:8080/songsWS-PABB/rest/songs/" + song.getId();
                    URI location = null;
                    try {
                        location = new URI(uriString);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    responseHeaders.setLocation(location);
                    return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
                } else {
                    String json = new ObjectMapper().writeValueAsString("Wrong Body");
                    return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<SongCollection> getAllSongs(@RequestHeader("Authorization") String token) throws IOException {

        if (token != null) {
            if (authentication.authenticate(token)) {

                List<Song> songs = songDAO.getSongs();
                SongCollection songCollection = new SongCollection();
                songCollection.setSongList(songs);
                HttpHeaders responseHeaders = new HttpHeaders();
                return new ResponseEntity<SongCollection>(songCollection, responseHeaders, HttpStatus.OK);
            } else {
                return new ResponseEntity<SongCollection>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<SongCollection>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<Song> getSong(@PathVariable("id") int id, @RequestHeader("Authorization") String token) throws IOException {

        if (token != null) {
            if (authentication.authenticate(token)) {
                Song song = songDAO.getSong(id);

                HttpHeaders responseHeaders = new HttpHeaders();
                if (song == null) {
                    return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
                } else {

                    return new ResponseEntity<Song>(song, responseHeaders, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<Song>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<Song>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> updateSong(@PathVariable("id") int id,
                                             @RequestBody Song song, @RequestHeader("Authorization") String token) throws IOException {
        if (token != null) {
            if (authentication.authenticate(token)) {
                if (song.getTitle() == "" || song.getTitle() == null) {
                    return new ResponseEntity<String>("Invalid title", new HttpHeaders(), HttpStatus.NOT_FOUND);
                }
                boolean updated = songDAO.updateSong(song, id);
                if (updated) {
                    return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<String>("Invalid song", new HttpHeaders(), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSong(@PathVariable("id") int id, @RequestHeader("Authorization") String token) throws IOException {
        if (token != null) {
            if (authentication.authenticate(token)) {
                boolean deletedID = songDAO.deleteSong(id);
                if (deletedID == true) {
                    return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<String>("invalid", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}