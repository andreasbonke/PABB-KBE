package htwb.ai.PABB.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(
                new SongListController(new TestSongListDAO(), testAuthenticationDAO, testSongDAO)).build();

        Song song1 = new Song();
        song1.setId(1);
        song1.setArtist("ELIF");
        song1.setTitle("EIN LETZTES MAL");
        song1.setReleased(2020);
        song1.setLabel("Roadrunner");

        Song song2 = new Song();
        song2.setId(2);
        song2.setArtist("Tic Tac Toe");
        song2.setTitle("Funky");
        song2.setReleased(1998);
        song2.setLabel("blub");

        Set<Song> songs = new HashSet<>();
        songs.add(song1);
        songs.add(song2);
        testSongDAO.addSong(song1);
        testSongDAO.addSong(song2);

        User tom = new User();
        tom.setUserid("babo");
        tom.setFirstname("tom");
        tom.setLastname("tomi");
        tom.setPassword("geheim");

        String token = testAuthenticationDAO.generateToken(tom,10);
        tokenMap.put(tom.getUserid(), token);

        testSongList1.setIsPrivate(false);
        testSongList1.setOwnerId(tom);
        testSongList1.setName("HITS de TOM");
        testSongList1.setSongs(songs);
    }

   @Test
    void postSongListExpectCREATED201() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(testSongList1);
        System.out.println(jsonString);
        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization" , tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content("{\"user\":\"babo\",\"name\":\"HITS de TOM\",\"isPrivate\":false,\"songList\":[{\"id\":1,\"title\":\"EIN LETZTES MAL\",\"artist\":\"ELIF\",\"label\":\"Roadrunner\",\"released\":2020},{\"id\":2,\"title\":\"Funky\",\"artist\":\"Tic Tac Toe\",\"label\":\"blub\",\"released\":1998}]}\n"))
                        .andExpect(status().isCreated()).andReturn();
//.content(asJson(testSongList1)))
        String headerValue = result.getResponse().getHeader("Location");
        Assertions.assertTrue("http://localhost:8080/songsWS-PABB/rest/songList/1".equals(headerValue));
    }

    @Test
    void getSongList() {
    }

    @Test
    void getSongListByOwnerID() {
    }

    @Test
    void deleteSongList() {
    }

    private String asJson(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
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