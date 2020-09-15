package htwb.ai.pabb.songrelatedservice.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songsrelated")
public class SongRelatedDataController {

    @GetMapping(value = "/{id}",  produces = {"application/json", "application/xml"})
    public ResponseEntity<String> getSongInfo(){

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
