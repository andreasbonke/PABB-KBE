package htwb.ai.PABB.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.http.HttpRequest;
import java.util.*;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SongServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //private String uriToDB = null;
    private String welcome;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        welcome = "Welcome";
    }

    //@Override
    public void doGet2(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        // alle Parameternamen (keys)
        Enumeration<String> paramNames = request.getParameterNames();

        String responseStr = "";
        String param = "";
        while (paramNames.hasMoreElements()) {
            param = paramNames.nextElement();
            responseStr = responseStr + param + "="
                    + request.getParameter(param) + "\n";
        }
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            responseStr += "\n Your request will be sent to ";
            out.println(responseStr);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = request.getReader().lines()
                .reduce("", (acc, a) -> acc + a);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = body;
        Song song = mapper.readValue(jsonInString, Song.class);
        ConnectSystem connectSystem = new ConnectSystem();
        connectSystem.addSong(song);
        PrintWriter out = response.getWriter();
        out.println(body);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        //HashMap<String, String> map = new HashMap<String, String>();
        Enumeration<String> paramNames = request.getParameterNames();
        /*while (paramNames.hasMoreElements()) {
            String parameter = paramNames.nextElement();

            map.put(parameter, request.getHeader(parameter));
            //out.println("Parameter  " + parameter);
            //out.println("Value  " + request.getParameter(parameter));
        }*/
        ConnectSystem connectSystem = new ConnectSystem();
        String paramType = paramNames.nextElement();
        if (paramType.equals("songId")) {
            int id = Integer.parseInt(request.getParameter(paramType));
            Song song = connectSystem.getSong(id);

            ObjectMapper mapper = new ObjectMapper();
            //String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(song);
            out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(song) + "    " + paramType);
        } else if(paramType.equals("all")) {
            List<Song> songList = connectSystem.getSongs();
            ObjectMapper mapper = new ObjectMapper();
            out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(songList));
        }

    }

    @Override
    public void destroy() {

    }
}
