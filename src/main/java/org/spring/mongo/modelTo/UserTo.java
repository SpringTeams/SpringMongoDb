package org.spring.mongo.modelTo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component(value = "userTo")
public class UserTo implements Serializable {
    private Long userId;
    private String userName;
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTo(Long userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public UserTo() {
    }
}
