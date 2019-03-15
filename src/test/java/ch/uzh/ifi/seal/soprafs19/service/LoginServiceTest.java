package ch.uzh.ifi.seal.soprafs19.service;

import ch.uzh.ifi.seal.soprafs19.Application;
import ch.uzh.ifi.seal.soprafs19.constant.UserStatus;
import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Test class for the UserResource REST resource.
 *
 * @see UserService
 * @see LoginService
 */
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
public class LoginServiceTest {


    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;


    @Test
    public void checkUsername() {
        Assert.assertNull(userRepository.findByUsername("testUsername"));

        User testUser = new User();
        testUser.setName("name1");
        testUser.setUsername("username1");
        testUser.setBirthday("01.01.1999");
        testUser.setPassword("testPassword");
        testUser.setCreateDate();

        User secondTestUser = new User();
        secondTestUser.setName("name2");
        secondTestUser.setUsername("username2");
        secondTestUser.setBirthday("01.01.1999");
        secondTestUser.setPassword("testPassword");
        secondTestUser.setCreateDate();

        User createdUser = userService.createUser(testUser);

        Assert.assertEquals(loginService.checkUsername(createdUser), true);
        Assert.assertEquals(loginService.checkUsername(secondTestUser), false);

    }
    @Test
    public void acceptLogin(){
        User testUser = new User();
        testUser.setName("name3");
        testUser.setUsername("username3");
        testUser.setBirthday("01.01.1999");
        testUser.setPassword("password3");
        testUser.setCreateDate();

        User secondTestUser = new User();
        secondTestUser.setName("name4");
        secondTestUser.setUsername("username4");
        secondTestUser.setBirthday("01.01.1999");
        secondTestUser.setPassword("password4");
        secondTestUser.setCreateDate();

        User createdUser = userService.createUser(testUser);
        User secondCreatedUser = userService.createUser(secondTestUser);

        Assert.assertEquals(loginService.acceptLogin(createdUser.getUsername(), createdUser.getPassword()), true);
        Assert.assertEquals(loginService.acceptLogin(createdUser.getUsername(), secondCreatedUser.getPassword()), false);
        Assert.assertEquals(loginService.acceptLogin(secondCreatedUser.getUsername(), createdUser.getPassword()), false);
        Assert.assertEquals(loginService.acceptLogin(secondCreatedUser.getUsername(), secondCreatedUser.getPassword()), true);

    }

    @Test
    public void login(){
        User testUser = new User();
        testUser.setName("name5");
        testUser.setUsername("username5");
        testUser.setBirthday("01.01.1999");
        testUser.setPassword("testPassword");
        testUser.setCreateDate();

        User createdUser = userService.createUser(testUser);

        loginService.login(createdUser);

        Assert.assertEquals(userRepository.findByUsername(createdUser.getUsername()).getStatus(), UserStatus.ONLINE );
        Assert.assertNotNull(userRepository.findByUsername(createdUser.getUsername()).getToken());

    }


}