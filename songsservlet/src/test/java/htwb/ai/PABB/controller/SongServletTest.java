package htwb.ai.PABB.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;

import javax.servlet.ServletException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SongServletTest {

    private SongServlet servlet;
    private MockServletConfig config;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    private final static String URITODB_STRING = "uriToDB:test.server";

    @BeforeEach
    void setUp() throws ServletException {
        servlet = new SongServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        config = new MockServletConfig();
        config.addInitParameter("uriToDB", URITODB_STRING);
        servlet.init(config); //throws ServletException
    }

    // Uri ist richtig
    @Test
    void initShouldSetDBComponentURI() {
        assertEquals(URITODB_STRING,servlet.getUriToDB());
    }

    //Uri ist falsch


    @Test
    void doGetShouldEchoParameters() throws ServletException, IOException {
        request.addParameter("username","eve");
        request.addParameter("password", "adam");

        servlet.doGet(request,response);

        assertEquals("text/plain", response.getContentType());
        assertTrue(response.getContentAsString().contains("username=eve"));
        assertTrue(response.getContentAsString().contains("password=adam"));
        assertTrue(response.getContentAsString().contains(URITODB_STRING));


    }

    @Test
    void doPostShouldEchoBody() throws ServletException, IOException {
        request.setContent("ubububu".getBytes());
        servlet.doPost(request,response);
        assertEquals("text/plain", response.getContentType());
        assertTrue(response.getContentAsString().contains("ubububu"));
    }

}