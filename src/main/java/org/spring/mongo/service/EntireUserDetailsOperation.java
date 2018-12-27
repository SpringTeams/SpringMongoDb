package org.spring.mongo.service;

import org.spring.mongo.modelTo.UserTo;

import java.util.List;

public interface EntireUserDetailsOperation {
    public Boolean saveUser(UserTo userTo);
    public List<UserTo> loadUsers();
    public UserTo getSpecificDetails(Boolean isCharacter,Object value);
}
