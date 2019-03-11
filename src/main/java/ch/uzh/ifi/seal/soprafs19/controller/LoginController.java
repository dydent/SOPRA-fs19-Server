package ch.uzh.ifi.seal.soprafs19.controller;

import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.entity.UserTransmission;
import ch.uzh.ifi.seal.soprafs19.repository.UserRepository;
import ch.uzh.ifi.seal.soprafs19.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController {

    private final LoginService service;

    LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping("/login")
    UserTransmission login(@RequestBody User checkUser) {
        if (this.service.checkUsername(checkUser)) {
            if (this.service.acceptLogin(checkUser.getUsername(), checkUser.getPassword())) {
                return new UserTransmission(this.service.login(checkUser), true);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password is wrong");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please register first");
        }
    }
}


