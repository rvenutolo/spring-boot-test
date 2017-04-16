package org.venutolo.spring.test.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.venutolo.spring.test.User;
import org.venutolo.spring.test.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private static final Log logger = LogFactory.getLog(UserRestController.class);

    private final UserService service;

    @Autowired
    public UserRestController(final UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers() {
        logger.debug("Getting users");
        final List<User> users = service.getUsers();
        logger.debug("Got users");
        return users;
    }

}
