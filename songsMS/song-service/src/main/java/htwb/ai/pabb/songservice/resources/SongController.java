package htwb.ai.pabb.songservice.resources;

import htwb.ai.pabb.songservice.models.Song;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Song>> getAllSongs(){

        HttpHeaders responseHeaders = new HttpHeaders();

        List<Song> songs = Arrays.asList(
                new Song(1,"Art","Te","RCA","2010"),
                new Song(2,"Dope","Fass","BGS","2001")
        );

        return new ResponseEntity<List<Song>>(songs, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<Song> getSong(@PathVariable("id") int id){
        HttpHeaders responseHeaders = new HttpHeaders();
        Song song = new Song(1,"Art","Te","RCA","2010");
        return new ResponseEntity<Song>(song, responseHeaders,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> postSong(@RequestBody Song song){
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> updateSong(@PathVariable("id") int id, @RequestBody Song song) {
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSong(@PathVariable("id") int id) {
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

}
