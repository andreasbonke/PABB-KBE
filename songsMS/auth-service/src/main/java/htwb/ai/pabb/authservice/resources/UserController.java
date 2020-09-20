package htwb.ai.pabb.authservice.resources;

import htwb.ai.pabb.authservice.dao.AuthenticationService;
import htwb.ai.pabb.authservice.dao.UserService;
import htwb.ai.pabb.authservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    final static int tokenlength = 10;

    /**
     * Nimmt POST Anfragen entgegen und authentifiziert einen User, indem einen Token zurückgegeben wird
     *
     * @param tmpuser
     * @return
     */
    @PostMapping
    public ResponseEntity<String> auth(@RequestBody User tmpuser) {
        HttpHeaders responseHeaders = new HttpHeaders();
        User user = userService.getUserById(tmpuser.getUserid());

        if (user == null || user.getPassword() == null || !user.getPassword().equals(tmpuser.getPassword())) {
            return new ResponseEntity<>("Declined!", HttpStatus.UNAUTHORIZED);
        } else {
            responseHeaders.add("Content-Type", "application/json");
            String json = authenticationService.generateToken(user, tokenlength);
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
        }
    }
}
