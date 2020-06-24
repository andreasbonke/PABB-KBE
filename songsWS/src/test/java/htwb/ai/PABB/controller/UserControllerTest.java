package htwb.ai.PABB.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.PABB.dao.TestUserDAO;
import htwb.ai.PABB.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/*class UserControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(
                new UserController(new TestUserDAO())).build();
    }

    @Test
    void postAuthUserShouldReturnOKAndGeneratedKey() throws Exception {

        User tom = new User();
        tom.setUserid("babo");
        tom.setFirstname("tom");
        tom.setLastname("tomi");
        tom.setPassword("geheim");

        System.out.println(asJson(tom));

        MvcResult result =
                mockMvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON).content(asJson(tom)))
                        .andExpect(status().isOk())
                        .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertTrue(content.length() == 10);
    }

    @Test
    void postAuthUserShouldReturn401ForNonExistingId() throws Exception {
        User tom = new User();
        tom.setUserid("baboo");
        tom.setFirstname("tom");
        tom.setLastname("tomi");
        tom.setPassword("geheim");

        System.out.println(asJson(tom));

        mockMvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON).content(asJson(tom)))
                .andExpect(status().isUnauthorized())
                .andReturn();

    }

    @Test
    void postAuthUserShouldReturn400ForNULLUser() throws Exception {
        User tom = null;
        System.out.println(asJson(tom));

        mockMvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON).content(asJson(tom)))
                .andExpect(status().isBadRequest())
                .andReturn();
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