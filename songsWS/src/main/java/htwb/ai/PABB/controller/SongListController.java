package htwb.ai.PABB.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.PABB.dao.IAuthenticationDAO;
import htwb.ai.PABB.dao.ISongDAO;
import htwb.ai.PABB.dao.ISongListDAO;
import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.SongList;
import htwb.ai.PABB.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/songsWS-PABB/rest/songList")
public class SongListController {

    private ISongListDAO songListDAO;

    private IAuthenticationDAO authentication;

    private ISongDAO songDAO;


    public SongListController(ISongListDAO songListDAO, IAuthenticationDAO authentication, ISongDAO songDAO) {
        this.authentication = authentication;
        this.songListDAO = songListDAO;
        this.songDAO = songDAO;
    }

    private static boolean compare(String str1, String str2) {
        return (str1 == null ? str2 == null : str1.equals(str2));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> postSongList(
            @RequestBody SongList songList, @RequestHeader("Authorization") String token) throws IOException {

        if (token != null) {
            if (authentication.authenticate(token) && authentication.getUser(token) != null) {

                Set<Song> songs = songList.getSongs();

                if (songs != null) {
                    HttpHeaders responseHeaders = new HttpHeaders();

                    for (Song song : songs) {
                        if (songDAO.getSong(song.getId()) != null) {
                            Song tmpsong = songDAO.getSong(song.getId());
                            if (tmpsong.getId() == song.getId() && compare(tmpsong.getTitle(), song.getTitle()) && compare(tmpsong.getArtist(), song.getArtist()) && compare(tmpsong.getLabel(), song.getLabel())) {

                            } else {
                                String json = new ObjectMapper().writeValueAsString("Wrong Song");
                                return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
                            }
                        } else {
                            String json = new ObjectMapper().writeValueAsString("Wrong Song");
                            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
                        }
                    }

                    songList.setOwnerId(authentication.getUser(token));
                    songListDAO.addSongList(songList);
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
                    String json = new ObjectMapper().writeValueAsString("no songs");
                    return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
                }
            } else {
                String json = new ObjectMapper().writeValueAsString("1");
                return new ResponseEntity<String>(json,HttpStatus.BAD_REQUEST);
            }
        } else {
            String json = new ObjectMapper().writeValueAsString("2");
            return new ResponseEntity<String>(json,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<SongList> getSongList(@PathVariable("id") int id, @RequestHeader("Authorization") String token) throws IOException {

        if (token != null) {
            if (authentication.authenticate(token) && songListDAO.getSongList(id) != null) {
                SongList songList = songListDAO.getSongList(id);

                HttpHeaders responseHeaders = new HttpHeaders();
                if (songList == null) {
                    return new ResponseEntity<SongList>(HttpStatus.NOT_FOUND);
                } else {
                    if (!authentication.getUser(token).getUserid().equals(songList.getOwnerId())) {
                        //&& songList.getPrivate() == false) || (authentication.getUser(token) == songList.getUser())) {
                        if (songList.getIsPrivate() == true) {
                            return new ResponseEntity<SongList>(HttpStatus.FORBIDDEN);
                        }
                    }
                    return new ResponseEntity<SongList>(songList, responseHeaders, HttpStatus.OK);
                }

            } else {
                HttpHeaders responseHeaders = new HttpHeaders();
                return new ResponseEntity<SongList>(responseHeaders, HttpStatus.BAD_REQUEST);
            }
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<SongList>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<List<SongList>> getSongListByOwnerID(@RequestHeader("Authorization") String token, @RequestParam("userId") String userId) throws IOException {

        if (token != null) {
            if (authentication.authenticate(token)) {
                User tmpuser = authentication.getUser(token);
                List<SongList> songs = songListDAO.getSongList(userId);
                HttpHeaders responseHeaders = new HttpHeaders();
                if (tmpuser.getUserid().equals(userId)) {
                    return new ResponseEntity<List<SongList>>(songs, responseHeaders, HttpStatus.ACCEPTED);
                } else {
                    List<SongList> tmpsongs = new ArrayList<SongList>();
                    for (int i = 0; i < songs.size(); i++) {
                        if (songs.get(i).getIsPrivate() == false) {
                            tmpsongs.add(songs.get(i));
                        }
                    }
                    return new ResponseEntity<List<SongList>>(tmpsongs, responseHeaders, HttpStatus.ACCEPTED);
                }
            } else {
                HttpHeaders responseHeaders = new HttpHeaders();
                return new ResponseEntity<List<SongList>>(responseHeaders, HttpStatus.BAD_REQUEST);
            }
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<List<SongList>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSongList(@PathVariable("id") int id, @RequestHeader("Authorization") String token) throws IOException {
        if (token != null) {
            if (authentication.authenticate(token)) {
                SongList songList = songListDAO.getSongList(id);

                if (songList != null) {
                    if(songList.getOwnerId().equals(authentication.getUser(token).getUserid())){
                        boolean deletedID = songListDAO.deleteSong(id);
                        if (deletedID == true) {
                            return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.NO_CONTENT);
                        } else {
                            return new ResponseEntity<String>("invalid", HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
                    }
                }

            }
        }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

    }
}
