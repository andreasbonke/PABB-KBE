package htwb.ai.pabb.authservice.dao;

import htwb.ai.pabb.authservice.models.User;
import htwb.ai.pabb.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;


    public User getUserById(String userId){
        //return users.stream().filter(user -> user.getUserid().equals(userId)).findFirst().get();

        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users.stream().filter(user -> user.getUserid().equals(userId)).findFirst().get();
    }
}
