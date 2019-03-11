package ch.uzh.ifi.seal.soprafs19.entity;

import ch.uzh.ifi.seal.soprafs19.constant.UserStatus;

import java.util.Date;

public class UserTransmission {

    public UserTransmission(User user, Boolean passToken){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setStatus(user.getStatus());
        this.setCreationDate(user.getCreationDate());
        this.setBirthday(user.getBirthday());

        if (passToken) {
            this.setToken(user.getToken());
        }

    }
    private Long id;

    private String username;

    private String token;

    private UserStatus status;

    private Date creationDate;

    private String birthday;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
