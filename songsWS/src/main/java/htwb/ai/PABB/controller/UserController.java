package htwb.ai.PABB.controller;

import htwb.ai.PABB.dao.IAuthenticationDAO;
import htwb.ai.PABB.dao.IUserDAO;
import htwb.ai.PABB.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(value = "/songsWS-PABB/rest/auth")
public class UserController {

    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private IAuthenticationDAO authentication;

    final static int tokenlength = 10;

    /*public UserController(IUserDAO userDAO, IAuthenticationDAO authentication) {
        this.authentication = authentication;
        this.userDAO = userDAO;
    }*/

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
            String json = authentication.generateToken(user,tokenlength);
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
        }
    }


}
