package com.arohau.springjwtbasedauth.repositories;

import com.arohau.springjwtbasedauth.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository {
    private static final Map<String, User> usersMap = Map.of(
            "nd@m", new User("nd@m", "1234", "andrei", "horner"),
            "rona@m", new User("roma@m", "4321", "roma", "horner")
    );

    public User findUserByEmail(String email){
        User user = null;
        if (usersMap.containsKey(email)) {
            user = usersMap.get(email);
        }
        return user;
    }
}
