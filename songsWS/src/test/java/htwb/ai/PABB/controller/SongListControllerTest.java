package htwb.ai.PABB.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.PABB.dao.*;
import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.SongList;
import htwb.ai.PABB.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@SpringBootTest
//@TestPropertySource(locations = "/resources/META-INF/persistence.xml")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SongListControllerTest {


    private MockMvc mockMvc;

    private SongList testSongList1 = new SongList();
    private SongList testSongList2 = new SongList();
    private TestAuthenticationDAO testAuthenticationDAO = new TestAuthenticationDAO();
    private TestSongDAO testSongDAO = new TestSongDAO();
    private Map<String, String> tokenMap = new HashMap<>();
    private TestSongListDAO testSongListDAO = new TestSongListDAO();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(
                new SongListController(testSongListDAO, testAuthenticationDAO, testSongDAO)).build();

        Song song1 = new Song();
        song1.setId(1);
        song1.setArtist("Starship");
        song1.setTitle("We Built This City");
        song1.setReleased(1985);
        song1.setLabel("Grunt/RCA");

        Song song2 = new Song();
        song2.setId(2);
        song2.setArtist("Phil Collins");
        song2.setTitle("Sussudio");
        song2.setReleased(1985);
        song2.setLabel("Virgin");

        Set<Song> songs = new HashSet<>();
        songs.add(song1);
        songs.add(song2);
        testSongDAO.addSong(song1);
        testSongDAO.addSong(song2);

        Set<Song> songs2 = new HashSet<>();
        songs2.add(song1);

        User tom = new User();
        tom.setUserid("babo");
        tom.setFirstname("tom");
        tom.setLastname("tomi");
        tom.setPassword("geheim");

        String token = testAuthenticationDAO.generateToken(tom,10);
        tokenMap.put(tom.getUserid(), token);

        User susi = new User();
        susi.setUserid("susi");
        susi.setFirstname("susi");
        susi.setLastname("sorglos");
        susi.setPassword("pass");

        String token2 = testAuthenticationDAO.generateToken(susi,10);
        tokenMap.put(susi.getUserid(), token2);

        testSongList1.setIsPrivate(false);
        testSongList1.setOwnerId(tom);
        testSongList1.setName("HITS de TOM");
        testSongList1.setSongs(songs);


        testSongList2.setIsPrivate(true);
        testSongList2.setOwnerId(susi);
        testSongList2.setName("HITS de Susi");
        testSongList2.setSongs(songs2);

        System.out.println(testSongList2.getId() + "   " + testSongList2.getName() + testSongList2.getIsPrivate()+ testSongList2.getSongs());
        System.out.println(susi.getUserid());
        System.out.println(testAuthenticationDAO.getToken(susi.getUserid()));

    }

   @Test
    void postSongListExpectCREATED201() throws Exception {

        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization" , tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("{\n" +
                        "\t\"isPrivate\": false,\n" +
                        "\t\"name\": \"MaximesPrivate\",\n" +
                        "\t\"songList\": [\n" +
                        "\t\t{\n" +
                        "\t\t\t\"id\": 1,\n" +
                        "\t\t\t\"title\": \"We Built This City\",\n" +
                        "\t\t\t\"artist\": \"Starship\",\n" +
                        "\t\t\t\"label\": \"Grunt/RCA\",\n" +
                        "\t\t\t\"released\": 1985\n" +
                        "\t\t}\n" +
                        "\t]\n" +
                        "}")))
                        .andExpect(status().isCreated()).andReturn();
        String headerValue = result.getResponse().getHeader("Location");
        Assertions.assertTrue("http://localhost:8080/songsWS-PABB/rest/songlist/1".equals(headerValue));
    }

    @Test
    void postSongListExpectCREATED201NONAME() throws Exception {

        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization" , tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("{\n" +
                        "\t\"isPrivate\": false,\n" +
                        "\t\"songList\": [\n" +
                        "\t\t{\n" +
                        "\t\t\t\"id\": 1,\n" +
                        "\t\t\t\"title\": \"We Built This City\",\n" +
                        "\t\t\t\"artist\": \"Starship\",\n" +
                        "\t\t\t\"label\": \"Grunt/RCA\",\n" +
                        "\t\t\t\"released\": 1985\n" +
                        "\t\t}\n" +
                        "\t]\n" +
                        "}")))
                        .andExpect(status().isCreated()).andReturn();
        String headerValue = result.getResponse().getHeader("Location");
        Assertions.assertTrue("http://localhost:8080/songsWS-PABB/rest/songlist/1".equals(headerValue));
    }

    @Test
    void postSongListExpectCREATED201NOSETPRIVATE() throws Exception {

        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization" , tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("{\n" +
                        "\t\"songList\": [\n" +
                        "\t\t{\n" +
                        "\t\t\t\"id\": 1,\n" +
                        "\t\t\t\"title\": \"We Built This City\",\n" +
                        "\t\t\t\"artist\": \"Starship\",\n" +
                        "\t\t\t\"label\": \"Grunt/RCA\",\n" +
                        "\t\t\t\"released\": 1985\n" +
                        "\t\t}\n" +
                        "\t]\n" +
                        "}")))
                        .andExpect(status().isCreated()).andReturn();
        String headerValue = result.getResponse().getHeader("Location");
        Assertions.assertTrue("http://localhost:8080/songsWS-PABB/rest/songlist/1".equals(headerValue));
    }

    @Test
    void postSongListExpect400WRONGSONG() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(testSongList1);
        System.out.println(jsonString);
        //System.out.println(testSongDAO.getSong(1).getArtist());
        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization" , tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("{\n" +
                        "\t\"isPrivate\": false,\n" +
                        "\t\"name\": \"MaximesPrivate\",\n" +
                        "\t\"songList\": [\n" +
                        "\t\t{\n" +
                        "\t\t\t\"id\": 1,\n" +
                        "\t\t\t\"title\": \"Halli\",\n" +
                        "\t\t\t\"artist\": \"Hallo\",\n" +
                        "\t\t\t\"label\": \"Grunt/RCA\",\n" +
                        "\t\t\t\"released\": 1985\n" +
                        "\t\t}\n" +
                        "\t]\n" +
                        "}")))
                        .andExpect(status().isBadRequest()).andReturn();
    }
    @Test
    void postSongListExpect400NOAUTH() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(testSongList1);
        System.out.println(jsonString);
        //System.out.println(testSongDAO.getSong(1).getArtist());
        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").contentType(MediaType.APPLICATION_JSON).content(("{\n" +
                        "\t\"isPrivate\": false,\n" +
                        "\t\"name\": \"MaximesPrivate\",\n" +
                        "\t\"songList\": [\n" +
                        "\t\t{\n" +
                        "\t\t\t\"id\": 1,\n" +
                        "\t\t\t\"title\": \"We Built This City\",\n" +
                        "\t\t\t\"artist\": \"Starship\",\n" +
                        "\t\t\t\"label\": \"Grunt/RCA\",\n" +
                        "\t\t\t\"released\": 1985\n" +
                        "\t\t}\n" +
                        "\t]\n" +
                        "}")))
                        .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void postSongListExpect401WRONGAUTH() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(testSongList1);
        System.out.println(jsonString);
        //System.out.println(testSongDAO.getSong(1).getArtist());
        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization" , "hahahi").contentType(MediaType.APPLICATION_JSON).content(("{\n" +
                        "\t\"isPrivate\": false,\n" +
                        "\t\"name\": \"MaximesPrivate\",\n" +
                        "\t\"songList\": [\n" +
                        "\t\t{\n" +
                        "\t\t\t\"id\": 1,\n" +
                        "\t\t\t\"title\": \"We Built This City\",\n" +
                        "\t\t\t\"artist\": \"Starship\",\n" +
                        "\t\t\t\"label\": \"Grunt/RCA\",\n" +
                        "\t\t\t\"released\": 1985\n" +
                        "\t\t}\n" +
                        "\t]\n" +
                        "}")))
                        .andExpect(status().isUnauthorized()).andReturn();
    }

    @Test
    void postSongListExpect400NOCONTENT() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(testSongList1);
        System.out.println(jsonString);
        //System.out.println(testSongDAO.getSong(1).getArtist());
        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization" , tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("")))
                        .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void postSongListExpect400NOSONG() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(testSongList1);
        System.out.println(jsonString);
        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization" , tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("{\n" +
                        "\t\"isPrivate\": false,\n" +
                        "\t\"name\": \"MaximesPrivate\",\n" +
                        "\t\"songList\": [\n" +
                        "\t]\n" +
                        "}")))
                        .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void getSongList200() throws Exception {
        toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization" , tokenMap.get("babo")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    /*@Test
    void getSongList200XML() throws Exception {
        toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization" , tokenMap.get("babo")).header("Content-Type", "application/xml"))
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk()).andReturn();
    }*/

    @Test
    void getSongList401WRONGAUTH() throws Exception {
        toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization" , "dsadsdas"))
                .andExpect(status().isUnauthorized()).andReturn();
    }

    @Test
    void getSongList400NOAUTH() throws Exception {
        toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList/1"))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void getSongList403PRIVATEWRONGUSER() throws Exception {
        toSongList(testSongList2);
        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization" , tokenMap.get("babo")))
                .andExpect(status().isForbidden()).andReturn();
    }

    @Test
    void getSongList404WRONGID() throws Exception {
        toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList/2").header("Authorization" , tokenMap.get("babo")))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getSongList404NOSONGLIST() throws Exception {
        //toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization" , tokenMap.get("babo")))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getSongListByOwnerID() {
    }

    @Test
    void deleteSongList() {
    }

    private void toSongList(SongList songList) {
        testSongListDAO.addSongList(songList);
    }
    /*

        private MockMvc mockMvc;
    private MockMvc mockMvcUser;

    @Autowired
    private ISongListDAO songListDAO;

    @Autowired
    private IAuthenticationDAO authentication;

    @Autowired
    private IUserDAO userDAO;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(new SongListController()).build();
        mockMvcUser = MockMvcBuilders.standaloneSetup(new UserController()).build();

    }
    //METHODE//
        String SongListToSave = "{\n" +
                "\t\"isPrivate\": false,\n" +
                "\t\"name\": \"MaximesPrivate\",\n" +
                "\t\"songList\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 3,\n" +
                "\t\t\t\"title\": \"Muskrat Love\",\n" +
                "\t\t\t\"artist\": \"Captain and Tennille\",\n" +
                "\t\t\t\"label\": \"A&M\",\n" +
                "\t\t\t\"released\": 1976\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        String UserLogin = "{\"userid\":\"mmuster\","
                + "\"password\":\"pass1234\"}";

        mockMvcUser.perform(MockMvcRequestBuilders.post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserLogin))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/songList").header("Authorization",authentication.getToken("mmuster"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(SongListToSave))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/rest/songList")));

        List<SongList> savedSongList = songListDAO.getSongList("mmuster");
        assertTrue(savedSongList.size() == 1);
        SongList songList = savedSongList.get(0);
        assertTrue(songList.getUser().getUserid().equals("mmuster"));
        assertTrue(songList.getPrivate() == false);
        assertTrue(songList.getName().equals("MaximesPrivate"));
        assertTrue(songList.getId() == 0);
     */
}