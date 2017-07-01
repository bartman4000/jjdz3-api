package com.infoshareacademy.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class UserStore {

    private Logger LOG = LoggerFactory.getLogger(UserStore.class);

    private Map<Integer, User> base;

    public Map<Integer, User> getBase() {
        return base;
    }

    public UserStore() {
        LOG.info("initializing user store");
        base = new HashMap<Integer, User>();

        User user1 = new User("Adam", "Iksinski", 1,
            new Credentials("adam", "haslo123"));

        User user2 = new User("Karol", "Ygrekowski", 2,
            new Credentials("karoly", "123456"));

        base.put(user1.getId(), user1);
        base.put(user2.getId(), user2);
    }

    public List<User> getUsers()
    {
        return base.values().stream().collect(Collectors.toList());
    }

    public Optional<User> getUser(Integer id)
    {
        return base.values().stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public void add(User user) {
        LOG.info("Adding to store: " + user.toString());
        base.put(user.getId(), user);
    }
}
