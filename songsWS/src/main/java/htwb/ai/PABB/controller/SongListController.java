package htwb.ai.PABB.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.PABB.dao.IAuthenticationDAO;
import htwb.ai.PABB.dao.ISongDAO;
import htwb.ai.PABB.dao.ISongListDAO;
import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.SongList;
import htwb.ai.PABB.model.SongListCollection;
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
@RequestMapping(value = "/songsWS-PABB/rest/songLists")
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

                if (songs != null && !songs.isEmpty()) {
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
                        String uriString = "http://localhost:8080/songsWS-PABB/rest/songlists/" + songList.getId();
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
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<SongList> getSongList(@PathVariable("id") int id, @RequestHeader("Authorization") String token) throws IOException {

        if (token != null) {
            if (authentication.authenticate(token)) {
                if (songListDAO.getSongList(id) != null) {
                    SongList songList = songListDAO.getSongList(id);

                    HttpHeaders responseHeaders = new HttpHeaders();
                    if (songList == null) {
                        return new ResponseEntity<SongList>(HttpStatus.NOT_FOUND);
                    } else {
                        if (!authentication.getUser(token).getUserid().equals(songList.getOwnerId())) {
                            //&& songList.getPrivate() == false) || (authentication.getUser(token) == songList.getUser())) {
                            if (songList.getIsPrivate() == null ||  songList.getIsPrivate() == true) {
                                return new ResponseEntity<SongList>(HttpStatus.FORBIDDEN);
                            }
                        }
                        return new ResponseEntity<SongList>(songList, responseHeaders, HttpStatus.OK);
                    }
                } else {
                    HttpHeaders responseHeaders = new HttpHeaders();
                    return new ResponseEntity<SongList>(responseHeaders, HttpStatus.NOT_FOUND);
                }
            } else {
                HttpHeaders responseHeaders = new HttpHeaders();
                return new ResponseEntity<SongList>(responseHeaders, HttpStatus.UNAUTHORIZED);
            }
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<SongList>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<SongListCollection> getSongListByOwnerID(@RequestHeader("Authorization") String token, @RequestParam("userId") String userId) throws IOException {

        if (token != null) {
            if (authentication.authenticate(token)) {
                User tmpuser = authentication.getUser(token);
                List<SongList> songs = songListDAO.getSongList(userId);
                if (songs.isEmpty()) {
                    return new ResponseEntity<SongListCollection>(HttpStatus.NOT_FOUND);
                }
                HttpHeaders responseHeaders = new HttpHeaders();
                SongListCollection songListCollection = new SongListCollection();
                songListCollection.setSongList(songs);
                if (tmpuser.getUserid().equals(userId)) {
                    return new ResponseEntity<SongListCollection>(songListCollection, responseHeaders, HttpStatus.OK);
                } else {
                    List<SongList> tmpsongs = new ArrayList<SongList>();
                    for (int i = 0; i < songs.size(); i++) {
                        if (songs.get(i).getIsPrivate() != null && songs.get(i).getIsPrivate() == false) {
                            tmpsongs.add(songs.get(i));
                        }
                    }
                    songListCollection.setSongList(tmpsongs);
                    return new ResponseEntity<SongListCollection>(songListCollection, responseHeaders, HttpStatus.OK);
                }
            } else {
                HttpHeaders responseHeaders = new HttpHeaders();
                return new ResponseEntity<SongListCollection>(responseHeaders, HttpStatus.UNAUTHORIZED);
            }
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<SongListCollection>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSongList(@PathVariable("id") int id, @RequestHeader("Authorization") String token) throws IOException {
        if (token != null) {
            if (authentication.authenticate(token)) {
                SongList songList = songListDAO.getSongList(id);

                if (songList != null) {
                    if (songList.getOwnerId().equals(authentication.getUser(token).getUserid())) {
                        boolean deletedID = songListDAO.deleteSong(id);
                        if (deletedID == true) {
                            return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.NO_CONTENT);
                        } else {
                            return new ResponseEntity<String>("invalid", HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> updateSongList(@PathVariable("id") int id, @RequestBody SongList songList, @RequestHeader("Authorization") String token) throws IOException {
        if (token != null) {
            if (authentication.authenticate(token) && authentication.getUser(token) != null) {
                if (songList.getSongs() == null){
                    return new ResponseEntity<>("Invalid Songs", new HttpHeaders(), HttpStatus.NOT_FOUND);
                }
                boolean updated = songListDAO.updateSongList(songList, id);
                if (updated) {
                    return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<String>("Invalid SongList", new HttpHeaders(), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

}
