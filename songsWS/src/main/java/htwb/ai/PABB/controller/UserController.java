package htwb.ai.PABB.controller;

import htwb.ai.PABB.dao.IUserDAO;
import htwb.ai.PABB.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(value = "/auth")
public class UserController {

    private IUserDAO userDAO;

    final static int tokenlength = 10;

    public UserController(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> authUser(@RequestBody User tmpuser)
            throws IOException {
        HttpHeaders responseHeaders = new HttpHeaders();
        User user = userDAO.getUserByUserId(tmpuser.getUserid());

        if (user == null || user.getPassword() == null || !user.getPassword().equals(tmpuser.getPassword())) {
            return new ResponseEntity<String>("Declined!",
                    responseHeaders, HttpStatus.UNAUTHORIZED);
        } else {
            responseHeaders.add("Content-Type", "application/json");
            String json = generateToken(tokenlength);
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
        }
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
