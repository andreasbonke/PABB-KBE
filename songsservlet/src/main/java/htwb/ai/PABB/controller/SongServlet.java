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

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        ConnectSystem connectSystem = new ConnectSystem();
        ObjectMapper mapper = new ObjectMapper();

        String body = request.getReader().lines()
                .reduce("", (acc, a) -> acc + a);

        if (body != null) {
            //String jsonInString = body;
            try {
                Song song = mapper.readValue(body, Song.class);
                if (song.getTitle() != null) {
                    if (song.getTitle().isEmpty()) {
                        out.println("Wrong Body");
                        response.setStatus(400);
                    } else {
                        connectSystem.addSong(song);
                        //out.println(body);
                        //out.println("http://localhost:8080/songsservlet-PABB/songs?songId=" + song.getId());
                        response.setStatus(201);
                        response.setHeader("Location", "/songsservlet-PABB/songs?songId=" + song.getId());
                    }
                } else {
                    out.println("Wrong Body");
                    response.setStatus(400);
                }
            } catch (Exception e) {
                out.println("Wrong Body");
                response.setStatus(400);
            }
        } else {
            out.println("No Body");
            response.setStatus(400);
        }
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        Enumeration<String> paramNames = request.getParameterNames();
        Enumeration<String> paramNames2 = request.getParameterNames();
        ConnectSystem connectSystem = new ConnectSystem();
        int counter = 0;
        // paramNames.asIterator().forEachRemaining(c -> c, counter+1);
        while (paramNames2.hasMoreElements()) {
            paramNames2.nextElement();
            counter++;
        }
        if (counter == 1) {
            String paramType = paramNames.nextElement();
            if (paramType.equals("songId")) {
                if (request.getParameter(paramType).matches("-?\\d+")) {
                    int id = Integer.parseInt(request.getParameter(paramType));
                    Song song = connectSystem.getSong(id);
                    if (song != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(song));
                        response.setStatus(200);
                    } else {
                        out.println("No Song with this ID");
                        response.setStatus(404);
                    }
                } else {
                    response.setStatus(400);
                    out.println("songId must be a number");
                }
            } else if (paramType.equals("all")) {
                List<Song> songList = connectSystem.getSongs();
                ObjectMapper mapper = new ObjectMapper();
                out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(songList));
                response.setStatus(200);
            } else {
                response.setStatus(400);
                out.println("wrong parameters");
            }
        } else {
            response.setStatus(400);
            out.println("too many parameters");
        }
    }

}
