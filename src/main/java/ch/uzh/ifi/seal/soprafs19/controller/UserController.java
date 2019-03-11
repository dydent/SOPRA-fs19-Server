package ch.uzh.ifi.seal.soprafs19.controller;

import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.repository.UserRepository;
import ch.uzh.ifi.seal.soprafs19.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    private final UserService service;
    // Constructor
    UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    Iterable<User> all() {
        return service.getUsers();
    }

    // create  and return user with Request body from post request of the server
    @PostMapping("/users")
    User createUser(@RequestBody User newUser) {
        if (this.service.existsUserByUsername(newUser.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");        }
        else {
            return this.service.createUser(newUser);
        }
    }

    }

