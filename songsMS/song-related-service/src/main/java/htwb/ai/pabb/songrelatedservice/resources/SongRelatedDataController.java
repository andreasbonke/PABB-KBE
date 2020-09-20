package htwb.ai.pabb.songrelatedservice.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.pabb.songrelatedservice.dao.SongRelatedDataService;
import htwb.ai.pabb.songrelatedservice.models.SongRelatedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/songsrelated")
public class SongRelatedDataController {

    @Autowired
    private SongRelatedDataService songRelatedDataService;

    /**
     * Nimmt GET Anfragen entgegen und gibt Songtexte mit einer entsprechenden Id zurück
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<SongRelatedData> getSongInfo(@PathVariable("id") String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        SongRelatedData data = songRelatedDataService.getSongRelatedData(id);
        if (data == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(data, responseHeaders, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> postSongInfo(@RequestBody SongRelatedData songRelatedData) throws JsonProcessingException {

        HttpHeaders responseHeaders = new HttpHeaders();
        songRelatedDataService.addSongRelatedData(songRelatedData);
        if(songRelatedData.getId() != "0"){
            String uriString = "http://localhost:8083/songsrelated/" + songRelatedData.getId();
            URI location = null;
            try {
                location = new URI(uriString);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            responseHeaders.setLocation(location);
            return new ResponseEntity<>(responseHeaders,HttpStatus.CREATED);
        } else {
            String json = new ObjectMapper().writeValueAsString("Wrong Body");
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        }

    }
}
