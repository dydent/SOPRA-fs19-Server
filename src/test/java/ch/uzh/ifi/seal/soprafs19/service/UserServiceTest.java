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
 */
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
public class UserServiceTest {


    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void createUser() {
        Assert.assertNull(userRepository.findByUsername("testUsername"));

        User testUser = new User();
        testUser.setName("testName");
        testUser.setUsername("testUsername");
        testUser.setBirthday("01.01.1999");
        testUser.setPassword("testPassword");
        testUser.setCreateDate();

        User createdUser = userService.createUser(testUser);

        Assert.assertNotNull(createdUser.getToken());
        Assert.assertEquals(createdUser.getStatus(),UserStatus.ONLINE);
        Assert.assertEquals(createdUser, userRepository.findByToken(createdUser.getToken()));
        Assert.assertEquals(createdUser, userRepository.findByName(createdUser.getName()));
        Assert.assertEquals(createdUser, userRepository.findByUsername(createdUser.getUsername()));
        Assert.assertEquals(createdUser, userRepository.findByBirthday(createdUser.getBirthday()));
        Assert.assertEquals(createdUser, userRepository.findByPassword(createdUser.getPassword()));
        Assert.assertEquals(createdUser.getCreateDate(), testUser.getCreateDate());
        Assert.assertEquals(createdUser.getPassword(), userRepository.findByName(createdUser.getName()).getPassword());
    }

    @Test
    public void existsUserByUsername(){
        User testUser = new User();
        testUser.setName("name2");
        testUser.setUsername("username2");
        testUser.setBirthday("01.01.1999");
        testUser.setPassword("testPassword");
        testUser.setCreateDate();

        User createdUser = userService.createUser(testUser);

        Assert.assertEquals(userService.existsUserByUsername(createdUser.getUsername()), userRepository.existsUserByUsername(createdUser.getUsername()));

    }


    @Test
    public void getUserById(){
        User testUser = new User();
        testUser.setName("name3");
        testUser.setUsername("username3");
        testUser.setBirthday("01.01.1999");
        testUser.setPassword("testPassword");
        testUser.setCreateDate();

        User createdUser = userService.createUser(testUser);

        Assert.assertEquals(userService.getUserById(createdUser.getId()), createdUser);

    }

    @Test
    public void getUserByUsername(){
        User testUser = new User();
        testUser.setName("name4");
        testUser.setUsername("username4");
        testUser.setBirthday("01.01.1999");
        testUser.setPassword("testPassword");
        testUser.setCreateDate();

        User createdUser = userService.createUser(testUser);

        Assert.assertEquals(userService.getUserByUsername(createdUser.getUsername()), createdUser);
    }

    @Test
    public void getUserByToken(){
        User testUser = new User();
        testUser.setName("name5");
        testUser.setUsername("username5");
        testUser.setBirthday("01.01.1999");
        testUser.setPassword("testPassword");
        testUser.setCreateDate();

        User createdUser = userService.createUser(testUser);

        Assert.assertEquals(userService.getUserByToken(createdUser.getToken()), createdUser);
    }

    @Test
    public void editUser(){

        User testUser = new User();
        testUser.setName("name6");
        testUser.setUsername("username6");
        testUser.setBirthday("01.01.1999");
        testUser.setPassword("testPassword");
        testUser.setCreateDate();

        User createdUser = userService.createUser(testUser);

        testUser.setUsername(("newUsername"));
        testUser.setBirthday("11.11.1111");
        userService.editUser(testUser);

        Assert.assertEquals(createdUser.getUsername(), "newUsername");
        Assert.assertEquals(createdUser.getBirthday(), "11.11.1111");
    }


}
