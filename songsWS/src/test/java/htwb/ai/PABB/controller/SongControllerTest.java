package htwb.ai.PABB.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;

/*class SongControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(
                new SongController(new TestSongDAO())).build();
    }

    @Test
    void postSong201CREATEDwithLOCATIONHEADER() throws Exception {
        Song song = new Song();
        song.setId(1);
        song.setArtist("ELIF");
        song.setTitle("EIN LETZTES MAL");
        song.setReleased(2020);
        song.setLabel("Roadrunner");

        MvcResult result =
                mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song)))
                        .andExpect(status().isCreated()).andReturn();
        String headerValue = result.getResponse().getHeader("Location");
        Assert.assertTrue("http://localhost:8080/songsWS-PABB/rest/songs/1".equals(headerValue));
    }

    @Test
    void postSong400BADrequestIfIDequalsZERO() throws Exception {
        Song song = new Song();
        song.setId(0);
        song.setArtist("ELIF");
        song.setTitle("EIN LETZTES MAL");
        song.setReleased(2020);
        song.setLabel("Roadrunner");

        MvcResult result =
                mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song)))
                        .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void postSong400nullSong() throws Exception {
        Song song = null;
        MvcResult result =
                mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song)))
                        .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void getAllSongsExpect200() throws Exception {
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

        List<Song> songs = new ArrayList<>();
        songs.add(song1);
        songs.add(song2);

        MvcResult result1 =
                mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song1)))
                        .andExpect(status().isCreated()).andReturn();
        MvcResult result2 =
                mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song2)))
                        .andExpect(status().isCreated()).andReturn();

        MvcResult result3 =
                mockMvc.perform(get("/songs"))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        SongList songList = mapper.readValue(result3.getResponse().getContentAsString(), new TypeReference<SongList>() {
        });
        Assert.assertEquals(songList.getSongList().size(), 2);
        Assert.assertEquals(songList.getSongList().get(0).getArtist(), "ELIF");
        Assert.assertEquals(songList.getSongList().get(1).getArtist(), "Tic Tac Toe");
    }

    @Test
    void getSongEXPECT200AndREQUESTEDSONG() throws Exception {
        Song song1 = new Song();
        song1.setId(1);
        song1.setArtist("ELIF");
        song1.setTitle("EIN LETZTES MAL");
        song1.setReleased(2020);
        song1.setLabel("Roadrunner");
        mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song1)))
                .andExpect(status().isCreated()).andReturn();

        mockMvc.perform(get("/songs/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.artist", is("ELIF")))
                .andExpect(jsonPath("$.title", is("EIN LETZTES MAL")))
                .andExpect(jsonPath("$.released", is(2020)));
    }

    @Test
    void getSongEXPECT404() throws Exception {
        Song song1 = new Song();
        song1.setId(1);
        song1.setArtist("ELIF");
        song1.setTitle("EIN LETZTES MAL");
        song1.setReleased(2020);
        song1.setLabel("Roadrunner");
        mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song1)))
                .andExpect(status().isCreated()).andReturn();

        mockMvc.perform(get("/songs/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getSongEXPECT200andSONGinBODY() throws Exception {
        Song song1 = new Song();
        song1.setId(1);
        song1.setArtist("ELIF");
        song1.setTitle("EIN LETZTES MAL");
        song1.setReleased(2020);
        song1.setLabel("Roadrunner");
        mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song1)))
                .andExpect(status().isCreated()).andReturn();

        MvcResult result =
                mockMvc.perform(get("/songs/1"))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        Song song = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Song>() {
        });
        Assert.assertEquals(song.getArtist(), song1.getArtist());
        Assert.assertEquals(song.getId(), song1.getId());
        Assert.assertEquals(song.getLabel(), song1.getLabel());
        Assert.assertEquals(song.getReleased(), song1.getReleased());
        Assert.assertEquals(song.getTitle(), song1.getTitle());
    }

    @Test
    void updateSongEXPECT204CheckOutput() throws Exception {

        Song song1 = new Song();
        song1.setId(1);
        song1.setArtist("ELIF");
        song1.setTitle("EIN LETZTES MAL");
        song1.setReleased(2020);
        song1.setLabel("Roadrunner");

        Song song2 = new Song();
        song2.setId(1);
        song2.setArtist("ELIF");
        song2.setTitle("EIN ZWEITES MAL");
        song2.setReleased(2020);
        song2.setLabel("Roadrunner");

        mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song1)))
                .andExpect(status().isCreated()).andReturn();

        MvcResult result =
                mockMvc.perform(put("/songs/1").contentType(MediaType.APPLICATION_JSON).content(asJson(song2)))
                        .andExpect(status().isNoContent()).andReturn();

        MvcResult resultGet =
                mockMvc.perform(get("/songs/1"))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        Song song = mapper.readValue(resultGet.getResponse().getContentAsString(), new TypeReference<Song>() {
        });
        Assert.assertEquals(song.getArtist(), song1.getArtist());
        Assert.assertEquals(song.getId(), song1.getId());
        Assert.assertEquals(song.getLabel(), song1.getLabel());
        Assert.assertEquals(song.getReleased(), song1.getReleased());
        Assert.assertEquals(song.getTitle(), song2.getTitle());
    }

    @Test
    void updateSongEXPECT405NOID() throws Exception {

        Song song1 = new Song();
        song1.setId(1);
        song1.setArtist("ELIF");
        song1.setTitle("EIN LETZTES MAL");
        song1.setReleased(2020);
        song1.setLabel("Roadrunner");

        Song song2 = new Song();
        song2.setId(1);
        song2.setArtist("ELIF");
        song2.setTitle("EIN ZWEITES MAL");
        song2.setReleased(2020);
        song2.setLabel("Roadrunner");

        mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song1)))
                .andExpect(status().isCreated()).andReturn();

        MvcResult result =
                mockMvc.perform(put("/songs/").contentType(MediaType.APPLICATION_JSON).content(asJson(song2)))
                        .andExpect(status().isMethodNotAllowed()).andReturn();
    }

    @Test
    void updateSongEXPECT404NOTFOUND() throws Exception {

        Song song2 = new Song();
        song2.setId(1);
        song2.setArtist("ELIF");
        song2.setTitle("EIN ZWEITES MAL");
        song2.setReleased(2020);
        song2.setLabel("Roadrunner");

        MvcResult result =
                mockMvc.perform(put("/songs/2").contentType(MediaType.APPLICATION_JSON).content(asJson(song2)))
                        .andExpect(status().isNotFound()).andReturn();

    }

    @Test
    void deleteSongEXPECT204SongDeleted() throws Exception {

        Song song1 = new Song();
        song1.setId(1);
        song1.setArtist("ELIF");
        song1.setTitle("EIN LETZTES MAL");
        song1.setReleased(2020);
        song1.setLabel("Roadrunner");

        mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song1)))
                .andExpect(status().isCreated()).andReturn();

        mockMvc.perform(get("/songs/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        MvcResult result =
                mockMvc.perform(delete("/songs/1").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent()).andReturn();

        mockMvc.perform(get("/songs/1"))
                .andExpect(status().isNotFound()).andReturn();

    }

    @Test
    void deleteSongEXPECT404WrongID() throws Exception {

        Song song1 = new Song();
        song1.setId(1);
        song1.setArtist("ELIF");
        song1.setTitle("EIN LETZTES MAL");
        song1.setReleased(2020);
        song1.setLabel("Roadrunner");

        mockMvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(asJson(song1)))
                .andExpect(status().isCreated()).andReturn();

        mockMvc.perform(get("/songs/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        MvcResult result =
                mockMvc.perform(delete("/songs/2").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound()).andReturn();

        mockMvc.perform(get("/songs/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

    }

    private String asJson(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}*/