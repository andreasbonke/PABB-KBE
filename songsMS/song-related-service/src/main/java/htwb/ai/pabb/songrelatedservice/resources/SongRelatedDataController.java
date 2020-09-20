package htwb.ai.pabb.songrelatedservice.resources;

import htwb.ai.pabb.songrelatedservice.dao.SongRelatedDataService;
import htwb.ai.pabb.songrelatedservice.models.SongRelatedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songsrelated")
public class SongRelatedDataController {

    @Autowired
    private SongRelatedDataService songRelatedDataService;

    @GetMapping(value = "/{id}",  produces = {"application/json", "application/xml"})
    public ResponseEntity<SongRelatedData> getSongInfo(@PathVariable("id") String id){
        HttpHeaders responseHeaders = new HttpHeaders();
        SongRelatedData data = songRelatedDataService.getSongRelatedData(id);
        if (data == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(data, responseHeaders,HttpStatus.OK);
        }

    }
}
