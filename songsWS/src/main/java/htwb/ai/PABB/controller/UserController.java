package htwb.ai.PABB.controller;
/*
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.PABB.dao.IUserDAO;
import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController("UserController")
@RequestMapping(value = "/auth")
public class UserController {

    private IUserDAO userDAO;

    public UserController(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> authUser(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password)
            throws IOException {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        User user = userDAO.getUserByUserId(userId);

        if (user == null || user.getUserId() == null ||
                user.getPassword() == null) {
            return new ResponseEntity<String>("Declined: No such user!",
                    HttpStatus.UNAUTHORIZED);
        }

        if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
            String token = generateToken(10);
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Declined!!", HttpStatus.UNAUTHORIZED);
    }

    private String generateToken(int n) {

        String tokenElements = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz"
                + "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (tokenElements.length() * Math.random());
            sb.append(tokenElements.charAt(index));
        }

        return sb.toString();
    }

}
*/