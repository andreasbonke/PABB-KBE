package htwb.ai.pabb.songservice.resources;

import htwb.ai.pabb.songservice.dao.SongListService;
import htwb.ai.pabb.songservice.models.SongList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songLists")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @GetMapping(value = "/{id}",  produces = {"application/json", "application/xml"})
    public ResponseEntity<SongList> getSongList(@PathVariable("id") int id){
        HttpHeaders responseHeaders = new HttpHeaders();

        SongList songList = songListService.getSongList(id);
        return new ResponseEntity<>(songList, responseHeaders ,HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<SongList>> getSongListByOwnerId(@RequestParam("userId") String userId){
        HttpHeaders responseHeaders = new HttpHeaders();
        List<SongList> songLists = songListService.getSongList(userId);
        return new ResponseEntity<>(songLists, responseHeaders, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> postSongList(@RequestBody SongList songList){
        songListService.addSongList(songList);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<String> updateSongList(@PathVariable("id") int id, @RequestBody SongList songList){
        songListService.updateSongList(id,songList);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSongList(@PathVariable("id") int id){
        songListService.deleteSongLists(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
