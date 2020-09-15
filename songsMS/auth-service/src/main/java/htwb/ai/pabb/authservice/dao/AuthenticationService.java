package htwb.ai.pabb.authservice.dao;

import htwb.ai.pabb.authservice.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private Map<User, String> tokenMap = new HashMap<>();

    public boolean authenticate(String token) {
        return tokenMap.containsValue(token);
    }

    public String generateToken(User user, int n) {
        String tokenElements = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz"
                + "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (tokenElements.length() * Math.random());
            sb.append(tokenElements.charAt(index));
        }

        if (!tokenMap.containsKey(user)) {
            tokenMap.put(user, sb.toString());
        } else {
            tokenMap.replace(user, sb.toString());
        }


        return sb.toString();
    }

    public User getUser(String token) {
        for (Map.Entry<User, String> entry : tokenMap.entrySet()) {
            if (token.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String getToken(String userid) {
        for (Map.Entry<User, String> entry : tokenMap.entrySet()) {
            if (userid.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

}
