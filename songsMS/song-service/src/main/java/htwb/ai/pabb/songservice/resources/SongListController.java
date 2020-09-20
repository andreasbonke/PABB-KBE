package htwb.ai.pabb.songservice.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.pabb.songservice.dao.SongListService;
import htwb.ai.pabb.songservice.models.SongList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/songLists")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @GetMapping(value = "/{id}",  produces = {"application/json", "application/xml"})
    public ResponseEntity<SongList> getSongList(@PathVariable("id") int id){
        HttpHeaders responseHeaders = new HttpHeaders();
        if (songListService.getSongList(id) != null){
            SongList songList = songListService.getSongList(id);
            if (songList == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                if (songList.getPrivate() == null || songList.getPrivate() == true) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }
            return new ResponseEntity<>(songList, responseHeaders ,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<SongList>> getSongListByOwnerId(@RequestParam("userId") String userId){
        HttpHeaders responseHeaders = new HttpHeaders();
        List<SongList> songLists = songListService.getSongList(userId);

        if (songLists.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(songLists, responseHeaders, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> postSongList(@RequestBody SongList songList) throws JsonProcessingException {

        HttpHeaders responseHeaders = new HttpHeaders();
        songListService.addSongList(songList);
        if (songList.getId() != 0){
            String uriString = "http://localhost:8083/songLists/" + songList.getId();
            URI location = null;
            try {
                location = new URI(uriString);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            responseHeaders.setLocation(location);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } else {
            String json = new ObjectMapper().writeValueAsString("Wrong Body");
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<String> updateSongList(@PathVariable("id") int id, @RequestBody SongList songList){
        boolean updated = songListService.updateSongList(id,songList);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("invalid",HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSongList(@PathVariable("id") int id){
        boolean deleted = songListService.deleteSongLists(id);
        if (deleted) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>("invalid", HttpStatus.NOT_FOUND);
        }

    }
}
