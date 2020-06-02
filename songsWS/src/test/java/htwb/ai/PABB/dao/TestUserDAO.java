package htwb.ai.PABB.dao;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import htwb.ai.PABB.dao.IUserDAO;
import htwb.ai.PABB.model.User;

public class TestUserDAO implements IUserDAO {

    private Map<String, User> myUsers= new ConcurrentHashMap<String, User>();;

    public TestUserDAO() {

        User tom = new User();
        tom.setUserid("babo");
        tom.setFirstname("tom");
        tom.setLastname("tomi");
        tom.setPassword("geheim");


        myUsers.put(tom.getUserid(), tom);
    }

    @Override
    public User getUserByUserId (String userid) {
        return myUsers.get(userid);
    }
}