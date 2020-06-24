package htwb.ai.PABB.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.PABB.dao.IAuthenticationDAO;
import htwb.ai.PABB.dao.ISongDAO;
import htwb.ai.PABB.dao.ISongListDAO;
import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.SongList;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SongListController {
    private ISongListDAO songListDAO;
    private IAuthenticationDAO authentication;


    public SongListController(ISongListDAO songListDAO, IAuthenticationDAO authentication) {
        this.authentication = authentication;
        this.songListDAO = songListDAO;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> postSongList(
            @RequestBody SongList songList, @RequestHeader("Authorization") String token) throws IOException {

        if (token != null) {
            if (authentication.authenticate(token)&& authentication.getUser(token) != null) {
                songList.setUser(authentication.getUser(token));
                songListDAO.addSongList(songList);

                ObjectMapper mapper = new ObjectMapper();
                Song[] songs = mapper.readValue((JsonParser) songList.getSongs(), Song[].class);



                HttpHeaders responseHeaders = new HttpHeaders();
                if (songList.getId() != 0) {
                    String uriString = "http://localhost:8080/songsWS-PABB/rest/songlist/" + songList.getId();
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
            } else {
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}
