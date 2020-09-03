package htwb.ai.pabb.authservice.resources;

import htwb.ai.pabb.authservice.models.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

   @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public String auth(@RequestBody User user){
        return new String("Token");
    }
}
