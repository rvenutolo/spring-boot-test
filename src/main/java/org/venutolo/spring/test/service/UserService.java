package org.venutolo.spring.test.service;

import org.venutolo.spring.test.User;

import java.util.List;

public interface UserService {

    // boolean returns if user was added,
    // if false, means duplicate user
    boolean addUser(User user);

    List<User> getUsers();

}
