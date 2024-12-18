package htwb.ai.PABB.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;

public class GitVersionServletTest {

    private GitVersionServlet servlet;
    private MockServletConfig config;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() throws ServletException {
        servlet = new GitVersionServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        config = new MockServletConfig();
        servlet.init(config); // throws ServletException
    }

    @Test
    public void initShouldLoadGitProperties() {
        assertTrue(servlet.getGitVersion().contains("git.dirty"));
    }

    @Test
    public void doGetShouldGetGitVersion() {
        try {
            servlet.doGet(request, response);
            assertEquals("application/json", response.getContentType());
            System.out.println(response.getContentAsString());
            assertTrue(response.getContentAsString().contains("git.commit.id"));
            assertTrue(response.getContentAsString().contains("git.commit.time"));
        } catch (Exception e) {
            fail("No exception should be thrown");
            e.printStackTrace();
        }
    }
}
