package htwb.ai.pabb.songservice.resources;

import htwb.ai.pabb.songservice.dao.SongService;
import htwb.ai.pabb.songservice.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Song>> getAllSongs(){
        HttpHeaders responseHeaders = new HttpHeaders();

        List<Song> songs = songService.getAllSongs();
        return new ResponseEntity<List<Song>>(songs, responseHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Song> getSong(@PathVariable("id") int id){
        HttpHeaders responseHeaders = new HttpHeaders();
        Song song = songService.getSong(id);
        return new ResponseEntity<Song>(song, responseHeaders,HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> postSong(@RequestBody Song song){
        songService.addSong(song);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<String> updateSong(@PathVariable("id") int id, @RequestBody Song song) {
        songService.updateSong(id, song);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable("id") int id) {
        songService.deleteSong(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

}
