package ch.uzh.ifi.seal.soprafs19.controller;

import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.entity.UserTransmission;
import ch.uzh.ifi.seal.soprafs19.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/users")
    User createUser(@RequestBody User newUser) {
        if (this.service.existsUserByUsername(newUser.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        } else {
            return this.service.createUser(newUser);
        }
    }

    @GetMapping("users/{id}")
    UserTransmission getUser(@PathVariable("id") long id) {
        var user = this.service.getUserById(id);
        if (user != null) {
            return new UserTransmission(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @CrossOrigin(origins = {"http://localhost:3000", "https://sopra-fs19-boner-tobias.herokuapp.com"})
    @PutMapping("/edit")
    boolean editUser(@RequestBody User editUser) {

        if (this.service.existsUserByUsername(editUser.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        User user = service.getUserById(editUser.getId());
        if(user != null) {
            this.service.editUser(editUser);
            return true;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this ID not found");
        }
    }

}



