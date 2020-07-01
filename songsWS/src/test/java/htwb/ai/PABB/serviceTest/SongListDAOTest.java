package htwb.ai.PABB.serviceTest;

import ch.qos.logback.core.db.dialect.DBUtil;
import htwb.ai.PABB.dao.DBSongDAO;
import htwb.ai.PABB.dao.DBUserDAO;
import htwb.ai.PABB.dao.SongListDAO;
import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.SongList;
import htwb.ai.PABB.model.User;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SongListDAOTest {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("songList-TEST-PU");
    private final String PU = "songList-TEST-PU";

    private SongListDAO songListDAO;
    private DBSongDAO songDAO;
    private DBUserDAO userDAO;

    private SongList testSongList1 = new SongList();
    private SongList testSongList2 = new SongList();
    private SongList testSongList3 = new SongList();
    private Set<Song> songs = new HashSet<>();
    private Set<Song> songs2 = new HashSet<>();
    private Set<Song> songs3 = new HashSet<>();
    private User susi;
    private User tom;


    @BeforeEach
    public void setup() {
        Song song1 = new Song();
        //song1.setId(1);
        song1.setArtist("Starship");
        song1.setTitle("We Built This City");
        song1.setReleased(1985);
        song1.setLabel("Grunt/RCA");

        Song song2 = new Song();
        //song2.setId(2);
        song2.setArtist("Phil Collins");
        song2.setTitle("Sussudio");
        song2.setReleased(1985);
        song2.setLabel("Virgin");

        Song song3 = new Song();
        //song3.setId(3);
        song3.setArtist("JAVA");
        song3.setTitle("HI");
        song3.setReleased(1985);
        song3.setLabel("DU");

        songs.add(song1);
        songs.add(song2);

        tom = new User("babo", "geheim", "tom", "tomi");
        susi = new User("susi", "pass", "susi", "sorglos");


        songs2.add(song1);

        songs3.add(song3);

        //  testSongList1.setId(1);
        testSongList1.setIsPrivate(false);
        testSongList1.setOwnerId(tom);
        testSongList1.setName("HITS de TOM");
        testSongList1.setSongs(songs);

        //testSongList2.setId(2);
        testSongList2.setIsPrivate(false);
        testSongList2.setOwnerId(tom);
        testSongList2.setName("HITS de Susi");
        testSongList2.setSongs(songs2);

        // testSongList3.setId(3);
        testSongList3.setIsPrivate(true);
        testSongList3.setOwnerId(susi);
        testSongList3.setName("PRIVATE HITS de TOM");
        testSongList3.setSongs(songs3);

        songDAO = new DBSongDAO(ENTITY_MANAGER_FACTORY);
        userDAO = new DBUserDAO(ENTITY_MANAGER_FACTORY);

        songDAO.addSong(song1);
        songDAO.addSong(song2);
        songDAO.addSong(song3);

//        songListDAO.addSongList(testSongList1);

    }

    @Order(1)
    @Test
    void addSongList() {
        songListDAO = new SongListDAO(ENTITY_MANAGER_FACTORY);
        songListDAO.addSongList(testSongList1);
        SongList songList = songListDAO.getSongList(1);
        Set<Song> tmpsongs = songList.getSongs();

        Assertions.assertEquals(false, songList.getIsPrivate());
        Assertions.assertEquals("babo", songList.getOwnerId());
        Assertions.assertEquals("HITS de TOM", songList.getName());

        Assertions.assertEquals(songs.size(), tmpsongs.size());

        boolean isSongInList = false;
        for (Song a : tmpsongs) {
            for (Song b : songs) {
                if (a.getArtist().equals(b.getArtist()))
                    isSongInList = true;
                break;
            }
        }
        Assertions.assertTrue(isSongInList);
    }

    @Order(2)
    @Test
    void getSongListBYIDWITH1SongListSHOULDWORK() {
        songListDAO = new SongListDAO(ENTITY_MANAGER_FACTORY);
        songListDAO.addSongList(testSongList1);
        SongList songList = songListDAO.getSongList(1);
        Set<Song> tmpsongs = songList.getSongs();

        Assertions.assertEquals(false, songList.getIsPrivate());
        Assertions.assertEquals("babo", songList.getOwnerId());
        Assertions.assertEquals("HITS de TOM", songList.getName());

        Assertions.assertEquals(songs.size(), tmpsongs.size());

        boolean isSongInList = false;
        for (Song a : tmpsongs) {
            for (Song b : songs) {
                if (a.getArtist().equals(b.getArtist()))
                    isSongInList = true;
                break;
            }
        }
        Assertions.assertTrue(isSongInList);
    }

    @Order(3)
    @Test
    void testGetSongListBYOWNERIDWITH1SongListSHOULDWORK() {
        songListDAO = new SongListDAO(ENTITY_MANAGER_FACTORY);
        songListDAO.addSongList(testSongList1);
        List<SongList> songListList = songListDAO.getSongList("babo");
        Assertions.assertEquals(1, songListList.size());
        SongList songList = songListList.get(0);

        Set<Song> tmpsongs = songList.getSongs();

        Assertions.assertEquals(false, songList.getIsPrivate());
        Assertions.assertEquals("babo", songList.getOwnerId());
        Assertions.assertEquals("HITS de TOM", songList.getName());

        Assertions.assertEquals(songs.size(), tmpsongs.size());

        boolean isSongInList = false;
        for (Song a : tmpsongs) {
            for (Song b : songs) {
                if (a.getArtist().equals(b.getArtist()))
                    isSongInList = true;
                break;
            }
        }
        Assertions.assertTrue(isSongInList);
    }

    @Order(4)
    @Test
    void getSongListBYIDWITH1SongListWRONGID() {
        songListDAO = new SongListDAO(ENTITY_MANAGER_FACTORY);
        songListDAO.addSongList(testSongList1);
        Assertions.assertNull(songListDAO.getSongList(2));

        //SongList songList = songListDAO.getSongList(1);
        //Set<Song> tmpsongs = songList.getSongs();

       /* Assertions.assertEquals(false, songList.getIsPrivate());
        Assertions.assertEquals("babo", songList.getOwnerId());
        Assertions.assertEquals("HITS de TOM", songList.getName());

        Assertions.assertEquals(songs.size(), tmpsongs.size());

        boolean isSongInList = false;
        for (Song a : tmpsongs) {
            for (Song b : songs) {
                if (a.getArtist().equals(b.getArtist()))
                    isSongInList = true;
                break;
            }
        }
        Assertions.assertTrue(isSongInList);*/
    }

    @Order(5)
    @Test
    void testGetSongListBYOWNERIDWITH1SongListWRONDUSER() {
        songListDAO = new SongListDAO(ENTITY_MANAGER_FACTORY);
        songListDAO.addSongList(testSongList1);
        List<SongList> songListList = songListDAO.getSongList("dsaads");
        List<SongList> songs = new ArrayList<>();
        Assertions.assertEquals(songs, songListList);
    }

    @Order(7)
    @Test
    void deleteSongSHOULDWORK() {
        songListDAO = new SongListDAO(ENTITY_MANAGER_FACTORY);
        songListDAO.addSongList(testSongList1);
        SongList songList = songListDAO.getSongList(1);
        Set<Song> tmpsongs = songList.getSongs();

        Assertions.assertEquals(false, songList.getIsPrivate());
        Assertions.assertEquals("babo", songList.getOwnerId());
        Assertions.assertEquals("HITS de TOM", songList.getName());

        Assertions.assertEquals(songs.size(), tmpsongs.size());

        boolean isSongInList = false;
        for (Song a : tmpsongs) {
            for (Song b : songs) {
                if (a.getArtist().equals(b.getArtist()))
                    isSongInList = true;
                break;
            }
        }
        Assertions.assertTrue(isSongInList);

        songListDAO.deleteSong(1);
        Assertions.assertNull(songListDAO.getSongList(1));
    }

    @Order(6)
    @Test
    void deleteSongWRONGID() {
        songListDAO = new SongListDAO(ENTITY_MANAGER_FACTORY);
        songListDAO.addSongList(testSongList1);
        SongList songList = songListDAO.getSongList(1);
        Set<Song> tmpsongs = songList.getSongs();

        Assertions.assertEquals(false, songList.getIsPrivate());
        Assertions.assertEquals("babo", songList.getOwnerId());
        Assertions.assertEquals("HITS de TOM", songList.getName());

        Assertions.assertEquals(songs.size(), tmpsongs.size());

        boolean isSongInList = false;
        for (Song a : tmpsongs) {
            for (Song b : songs) {
                if (a.getArtist().equals(b.getArtist()))
                    isSongInList = true;
                break;
            }
        }
        Assertions.assertTrue(isSongInList);

        songListDAO.deleteSong(2);
        Assertions.assertNotNull(songListDAO.getSongList(1));
    }

}
