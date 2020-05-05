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
import java.util.Enumeration;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SongServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //private String uriToDB = null;
    private String welcome;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // Beispiel: Laden eines Initparameters aus der web.xml
        // this.uriToDB = servletConfig.getInitParameter("uriToDB");
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
                .reduce("", (accumulator, actual) -> accumulator + actual);

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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + welcome + "<h1>");

    }

    @Override
    public void destroy() {

    }

   /* @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        // to accept the json data

        String jsonData = request.getParameter("json");

        // to send out the json data

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(jsonData) ;
        out.close() ;
    }*/
}
