package htwb.ai.PABB.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.PABB.dao.*;
import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.SongList;
import htwb.ai.PABB.model.SongListCollection;
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
    private SongList testSongList3 = new SongList();

    private TestAuthenticationDAO testAuthenticationDAO = new TestAuthenticationDAO();
    private TestSongDAO testSongDAO = new TestSongDAO();
    private Map<String, String> tokenMap = new HashMap<>();
    private TestSongListDAO testSongListDAO = new TestSongListDAO();
    private Set<Song> songs = new HashSet<>();


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

        Song song3 = new Song();
        song3.setId(3);
        song3.setArtist("JAVA");
        song3.setTitle("HI");
        song3.setReleased(1985);
        song3.setLabel("DU");

        songs.add(song1);
        songs.add(song2);
        testSongDAO.addSong(song1);
        testSongDAO.addSong(song2);

        Set<Song> songs2 = new HashSet<>();
        songs2.add(song1);

        Set<Song> songs3 = new HashSet<>();
        songs2.add(song3);

        User tom = new User("babo", "geheim", "tom", "tomi");

        String token = testAuthenticationDAO.generateToken(tom, 10);
        tokenMap.put(tom.getUserid(), token);

        User susi = new User("susi", "pass", "susi", "sorglos");

        String token2 = testAuthenticationDAO.generateToken(susi, 10);
        tokenMap.put(susi.getUserid(), token2);

        testSongList1.setIsPrivate(false);
        testSongList1.setOwnerId(tom);
        testSongList1.setName("HITS de TOM");
        testSongList1.setSongs(songs);

        testSongList3.setIsPrivate(true);
        testSongList3.setOwnerId(tom);
        testSongList3.setName("PRIVATE HITS de TOM");
        testSongList3.setSongs(songs3);

        testSongList2.setIsPrivate(true);
        testSongList2.setOwnerId(susi);
        testSongList2.setName("HITS de Susi");
        testSongList2.setSongs(songs2);

        System.out.println(testSongList2.getId() + "   " + testSongList2.getName() + testSongList2.getIsPrivate() + testSongList2.getSongs());
        System.out.println(susi.getUserid());
        System.out.println(testAuthenticationDAO.getToken("susi"));

    }

    @Test
    void postSongListExpectCREATED201() throws Exception {

        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization", tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("{\n" +
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
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization", tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("{\n" +
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
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization", tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("{\n" +
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
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization", tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("{\n" +
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
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization", "hahahi").contentType(MediaType.APPLICATION_JSON).content(("{\n" +
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
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization", tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("")))
                        .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void postSongListExpect400NOSONG() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(testSongList1);
        System.out.println(jsonString);
        MvcResult result =
                mockMvc.perform(post("/songsWS-PABB/rest/songList").header("Authorization", tokenMap.get("babo")).contentType(MediaType.APPLICATION_JSON).content(("{\n" +
                        "\t\"isPrivate\": false,\n" +
                        "\t\"name\": \"MaximesPrivate\",\n" +
                        "\t\"songList\": [\n" +
                        "\t]\n" +
                        "}")))
                        .andExpect(status().isBadRequest()).andReturn();
    }


    ////////////////////////////////////////////////////////////////////////////////


    @Test
    void getSongList200ByOwnerID() throws Exception {
        toSongList(testSongList1);
        MvcResult result =

                mockMvc.perform(get("/songsWS-PABB/rest/songList?userId=babo").header("Authorization", tokenMap.get("babo")).header("Content-Type", "application/json").header("Accept", "application/json"))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn();

        String resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        Assertions.assertTrue(resultString.contains("{\"id\":1,\"name\":\"HITS de TOM\",\"isPrivate\":false,\"ownerId\":\"babo\""));
    }


    @Test
    void getSongListByOwnerID200XML() throws Exception {
        toSongList(testSongList1);

        MvcResult result =

                mockMvc.perform(get("/songsWS-PABB/rest/songList?userId=babo").header("Authorization", tokenMap.get("babo")).header("Content-Type", "application/xml").header("Accept", "application/xml"))
                        .andExpect(content().contentType(MediaType.APPLICATION_XML))
                        .andExpect(status().isOk()).andReturn();

        String resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        Assertions.assertTrue(resultString.contains("<SongListCollection><songList><songList><id>1</id><name>HITS de TOM</name><isPrivate>false</isPrivate><ownerId>babo</ownerId><songList><songList>"));
    }

    @Test
    void getSongListByOwnerID401WRONGAUTH() throws Exception {
        toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList?userId=babo").header("Authorization", "dsadsdas"))
                .andExpect(status().isUnauthorized()).andReturn();
    }

    @Test
    void getSongListByOwnerID400NOAUTH() throws Exception {
        toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList?userId=babo"))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void getSongListByOwnerID404PRIVATEWRONGUSER() throws Exception {
        toSongList(testSongList2);
        mockMvc.perform(get("/songsWS-PABB/rest/songList?userId=cvcvcv").header("Authorization", tokenMap.get("babo")))
                .andExpect(status().isNotFound()).andReturn();
    }


    @Test
    void getSongListByOwnerID404NOSONGLIST() throws Exception {
        //toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList?userId=babo").header("Authorization", tokenMap.get("babo")))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getSongList200ByOwnerIDNOTOWNER() throws Exception {
        toSongList(testSongList1);
        toSongList(testSongList2);
        toSongList(testSongList3);

        MvcResult result =
                mockMvc.perform(get("/songsWS-PABB/rest/songList?userId=babo").header("Authorization", tokenMap.get("susi")).header("Content-Type", "application/json").header("Accept", "application/json"))
                        .andExpect(status().isOk()).andReturn();

        String resultString = result.getResponse().getContentAsString();
        Assertions.assertTrue(resultString.contains("{\"id\":1,\"name\":\"HITS de TOM\",\"isPrivate\":false,\"ownerId\":\"babo\""));
        Assertions.assertFalse(resultString.contains("\"isPrivate\":true"));

        /*ObjectMapper mapper = new ObjectMapper();
        //SongListCollection songListCollection = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<SongListCollection>() {});
        SongListCollection songListCollection = mapper.readValue(result.getResponse().getContentAsString(), SongListCollection.class);
        Assertions.assertEquals(songListCollection.getSongList().size(), 1);
        Assertions.assertEquals(songListCollection.getSongList().get(0).getOwnerId(), "Tom");
        Assertions.assertEquals(songListCollection.getSongList().get(1).getName(), "HITS de TOM");*/
    }

    ////////////////////////////////////////////////////////////////////////////////

    @Test
    void getSongList200() throws Exception {
        toSongList(testSongList1);

        MvcResult result =

                mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn();

        String resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        Assertions.assertTrue(resultString.contains("{\"id\":1,\"name\":\"HITS de TOM\",\"isPrivate\":false,\"ownerId\":\"babo\""));
    }

    @Test
    void getSongList200XML() throws Exception {
        toSongList(testSongList1);
        MvcResult result =

                mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")).header("Content-Type", "application/xml").header("Accept", "application/xml"))
                        .andExpect(content().contentType(MediaType.APPLICATION_XML))
                        .andExpect(status().isOk()).andReturn();

        String resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        Assertions.assertTrue(resultString.contains("<SongList><id>1</id><name>HITS de TOM</name><isPrivate>false</isPrivate><ownerId>babo</ownerId>"));
    }

    @Test
    void getSongList401WRONGAUTH() throws Exception {
        toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", "dsadsdas"))
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
        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")))
                .andExpect(status().isForbidden()).andReturn();
    }

    @Test
    void getSongList404WRONGID() throws Exception {
        toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList/2").header("Authorization", tokenMap.get("babo")))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getSongList404NOSONGLIST() throws Exception {
        //toSongList(testSongList1);
        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")))
                .andExpect(status().isNotFound()).andReturn();
    }


    @Test
    void getSongList200NOTOWNER() throws Exception {
        toSongList(testSongList1);
        //toSongList(testSongList2);
        //toSongList(testSongList3);

        MvcResult result =
                mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("susi")).header("Content-Type", "application/json").header("Accept", "application/json"))
                        .andExpect(status().isOk()).andReturn();

        String resultString = result.getResponse().getContentAsString();
        Assertions.assertTrue(resultString.contains("{\"id\":1,\"name\":\"HITS de TOM\",\"isPrivate\":false,\"ownerId\":\"babo\""));
        Assertions.assertFalse(resultString.contains("\"isPrivate\":true"));
    }


    ////////////////////////////////////////////////////////////////////////////////


    @Test
    void deleteSongListEXPECT200() throws Exception {
        toSongList(testSongList1);
        MvcResult result =
                mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")).header("Content-Type", "application/json").header("Accept", "application/json"))
                        .andExpect(status().isOk()).andReturn();
        String resultString = result.getResponse().getContentAsString();
        Assertions.assertTrue(resultString.contains("{\"id\":1,\"name\":\"HITS de TOM\",\"isPrivate\":false,\"ownerId\":\"babo\""));
        Assertions.assertFalse(resultString.contains("\"isPrivate\":true"));

        mockMvc.perform(delete("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")))
                .andExpect(status().isNoContent()).andReturn();

        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void deleteSongListEXPECT403WRONGUSER() throws Exception {
        toSongList(testSongList1);
        MvcResult result =
                mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")).header("Content-Type", "application/json").header("Accept", "application/json"))
                        .andExpect(status().isOk()).andReturn();
        String resultString = result.getResponse().getContentAsString();
        Assertions.assertTrue(resultString.contains("{\"id\":1,\"name\":\"HITS de TOM\",\"isPrivate\":false,\"ownerId\":\"babo\""));
        Assertions.assertFalse(resultString.contains("\"isPrivate\":true"));

        mockMvc.perform(delete("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("susi")))
                .andExpect(status().isForbidden()).andReturn();

        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void deleteSongListEXPECT401WRONGTOKEN() throws Exception {
        toSongList(testSongList1);
        MvcResult result =
                mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")).header("Content-Type", "application/json").header("Accept", "application/json"))
                        .andExpect(status().isOk()).andReturn();
        String resultString = result.getResponse().getContentAsString();
        Assertions.assertTrue(resultString.contains("{\"id\":1,\"name\":\"HITS de TOM\",\"isPrivate\":false,\"ownerId\":\"babo\""));
        Assertions.assertFalse(resultString.contains("\"isPrivate\":true"));

        mockMvc.perform(delete("/songsWS-PABB/rest/songList/1").header("Authorization", "dsadsdas"))
                .andExpect(status().isUnauthorized()).andReturn();

        mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void deleteSongListEXPECT404WRONGSONGID() throws Exception {
        toSongList(testSongList1);
        MvcResult result =
                mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")).header("Content-Type", "application/json").header("Accept", "application/json"))
                        .andExpect(status().isOk()).andReturn();
        String resultString = result.getResponse().getContentAsString();
        Assertions.assertTrue(resultString.contains("{\"id\":1,\"name\":\"HITS de TOM\",\"isPrivate\":false,\"ownerId\":\"babo\""));
        Assertions.assertFalse(resultString.contains("\"isPrivate\":true"));

        mockMvc.perform(delete("/songsWS-PABB/rest/songList/2").header("Authorization", tokenMap.get("babo")))
                .andExpect(status().isNotFound()).andReturn();

        //mockMvc.perform(get("/songsWS-PABB/rest/songList/1").header("Authorization", tokenMap.get("babo")))
          //      .andExpect(status().isOk()).andReturn();
    }


    ////////////////////////////////////////////////////////////////////////////////


    private void toSongList(SongList songList) {
        testSongListDAO.addSongList(songList);
    }

}