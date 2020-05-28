package htwb.ai.PABB.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.PABB.dao.ISongDAO;
import htwb.ai.PABB.dao.IUserDAO;
import htwb.ai.PABB.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="/songs")
public class SongController {

    //@Autowired
    private ISongDAO songDAO;

    public SongController (ISongDAO songDAO) {
        this.songDAO = songDAO;
    }

    //@PostMapping
    //@GetMapping(value="/{id}")
    @RequestMapping(method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<String> postSong(
            @RequestBody Song song) throws IOException {

        //int id = contactDAO.addContact(contact);
        songDAO.addSong(song);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");
        if(song.getId() != 0) {
            String json = new ObjectMapper().writeValueAsString("Location: /songsWS-PABB/rest/songs/" + song.getId());
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.CREATED);
        } else {
            String json = new ObjectMapper().writeValueAsString("Wrong Body");
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getAllSongs() throws IOException {

        List<Song> songs = songDAO.getSongs();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        String json = new ObjectMapper().writeValueAsString(songs);
        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getSong(@PathVariable("id") int id) throws IOException {

        Song song = songDAO.getSong(id);

        HttpHeaders responseHeaders = new HttpHeaders();
        if (song == null) {
            return new ResponseEntity<String>("Invalid song id",
                    responseHeaders, HttpStatus.NOT_FOUND);
        } else {
            responseHeaders.add("Content-Type", "application/json");

            String json = new ObjectMapper().writeValueAsString(song);
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<String> updateSong(@PathVariable("id") int id,
                                                @RequestBody Song song) throws IOException {
        if(song.getTitle() == "" || song.getTitle() == null){
            return new ResponseEntity<String>("Invalid title", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        boolean updated = songDAO.updateSong(song, id);
        if (updated) {
            return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Invalid song", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSong(@PathVariable("id") int id) throws IOException {
        boolean deletedID = songDAO.deleteSong(id);
        if (deletedID == true) {
            return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("invalid", HttpStatus.NOT_FOUND);
        }
    }
}
