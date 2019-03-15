package ch.uzh.ifi.seal.soprafs19.service;

import ch.uzh.ifi.seal.soprafs19.constant.UserStatus;
import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional
public class LoginService {

    private final Logger log = LoggerFactory.getLogger(LoginService.class);

    private final UserRepository userRepository;

    private final UserService userService;


    @Autowired
    public LoginService(@Qualifier("userRepository") UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Boolean checkUsername(User userToCheck) {
        return this.userService.existsUserByUsername(userToCheck.getUsername());
    }

    public Boolean acceptLogin(String username, String password) {
        return this.userRepository.findByUsername(username).getPassword().equals(password);
    }

    public User login(User user) {
        var loggedUser = this.userService.getUserByUsername(user.getUsername());
        loggedUser.setToken(UUID.randomUUID().toString());
        loggedUser.setStatus(UserStatus.ONLINE);
        return loggedUser;
    }

}

