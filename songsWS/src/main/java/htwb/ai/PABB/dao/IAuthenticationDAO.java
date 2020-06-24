package htwb.ai.PABB.dao;

import htwb.ai.PABB.model.User;

public interface IAuthenticationDAO {

    public boolean authenticate(String token);
    public String generateToken(User user, int n);
    public User getUser(String token);
}
