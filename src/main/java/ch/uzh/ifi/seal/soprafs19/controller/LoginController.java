package ch.uzh.ifi.seal.soprafs19.controller;

import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.entity.UserTransmission;
import ch.uzh.ifi.seal.soprafs19.service.LoginService;
import ch.uzh.ifi.seal.soprafs19.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController {

    private final LoginService loginService;

    private final UserService userService;

    LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService= userService;
    }

    @PostMapping("/login")
    UserTransmission login(@RequestBody User checkUser) {
        if (this.loginService.checkUsername(checkUser)) {
            if (this.loginService.acceptLogin(checkUser.getUsername(), checkUser.getPassword())) {
                return new UserTransmission(this.loginService.login(checkUser));
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password is wrong");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please register first");
        }
    }
}



