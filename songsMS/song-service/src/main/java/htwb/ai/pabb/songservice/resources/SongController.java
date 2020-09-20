package htwb.ai.pabb.songservice.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.pabb.songservice.dao.SongService;
import htwb.ai.pabb.songservice.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    /**
     * Nimmt GET Anfragen entgegen und gibt alle Songs zurück
     *
     * @return
     */
    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Song>> getAllSongs() {
        HttpHeaders responseHeaders = new HttpHeaders();

        List<Song> songs = songService.getAllSongs();
        return new ResponseEntity<List<Song>>(songs, responseHeaders, HttpStatus.OK);
    }

    /**
     * Nimmt GET Anfragen entgegen und gibt einen Songs mit bestimmter Id zurück
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Song> getSong(@PathVariable("id") int id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        Song song = songService.getSong(id);
        if (song.getId() != id) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(song, responseHeaders, HttpStatus.OK);
        }
    }

    /**
     * Nimmt POST Anfragen entgegen und erzeugt einen neuen Song
     *
     * @param song
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> postSong(@RequestBody Song song) throws JsonProcessingException {
        HttpHeaders responseHeaders = new HttpHeaders();
        songService.addSong(song);
        if (song.getId() != 0) {
            String uriString = "http://localhost:8082/songs/" + song.getId();
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

    }

    /**
     * Nimmt PUT Anfragen entgegen und ändert einen Song
     *
     * @param id
     * @param song
     * @return
     */
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<String> updateSong(@PathVariable("id") int id, @RequestBody Song song) {

        if (song.getTitle() == "" || song.getTitle() == null) {
            return new ResponseEntity<String>("Invalid title", new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        boolean updated = songService.updateSong(id, song);
        if (updated) {
            return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>("Invalid song", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Nimmt DELETE Anfragen entgegen und löscht einen Song
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable("id") int id) {
        boolean deleted = songService.deleteSong(id);
        if (deleted) {
            return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>("invalid", HttpStatus.NOT_FOUND);
        }
    }
}
