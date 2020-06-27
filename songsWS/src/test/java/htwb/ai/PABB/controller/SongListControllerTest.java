package htwb.ai.PABB.controller;

import htwb.ai.PABB.dao.IAuthenticationDAO;
import htwb.ai.PABB.dao.ISongListDAO;
import htwb.ai.PABB.dao.IUserDAO;
import htwb.ai.PABB.model.SongList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
@SpringBootTest
@TestPropertySource(locations = "/test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SongListControllerTest {
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
        mockMvc = MockMvcBuilders.standaloneSetup(new SongListController(songListDAO, authentication)).build();
        mockMvcUser = MockMvcBuilders.standaloneSetup(new UserController(userDAO, authentication)).build();

    }

    @Test
    void postSongList() throws Exception {
        String s = "{\"userId\":\"mmuster\","
                + "\"firstName\":\"Maxime\", "
                + "\"lastName\":\"Muster\","
                + "\"email\":\"mm@mail.de\"}";

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
}*/