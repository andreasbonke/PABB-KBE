package htwb.ai.pabb.songservice.resources;

import htwb.ai.pabb.songservice.models.SongList;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/songLists")
public class SongListController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<SongList> getSongList(@PathVariable("id") int id){
        HttpHeaders responseHeaders = new HttpHeaders();

        SongList songList = new SongList();

        return new ResponseEntity<SongList>(songList, responseHeaders ,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<List<SongList>> getSongListByOwnerId(@RequestParam("userId") String userId){
        HttpHeaders responseHeaders = new HttpHeaders();

        List<SongList> songLists = new ArrayList<>();

        return new ResponseEntity<List<SongList>>(songLists, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> postSongList(@RequestBody SongList songList){
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> updateSongList(@PathVariable("id") int id, @RequestBody SongList songList){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSongList(@PathVariable("id") int id){
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
