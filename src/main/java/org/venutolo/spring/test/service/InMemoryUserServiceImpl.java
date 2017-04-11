package org.venutolo.spring.test.service;

import org.springframework.stereotype.Service;
import org.venutolo.spring.test.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InMemoryUserServiceImpl implements UserService {

    private final Set<User> users;

    public InMemoryUserServiceImpl() {
        this.users = new HashSet<>();
        users.add(new User("Todd", "Bonzalez", 20, 1.28484362498f, true));
        users.add(new User("Mike", "Truk", 24, 1.549184378531f, true));
        users.add(new User("Karl", "Dandelton", 34, 1.1684343798f, false));
        users.add(new User("Raul", "Chamgerlain", 45, 1.781918132f, true));
        users.add(new User("Glenallen", "Mixon", 54, 1.549751291987f, true));
    }

    @Override
    public boolean addUser(final User user) {
        return users.add(user);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

}
