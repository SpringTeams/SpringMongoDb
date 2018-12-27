package org.spring.mongo.serviceImpl;

import org.spring.mongo.db.entities.User;
import org.spring.mongo.modelTo.UserTo;
import org.spring.mongo.repository.dbTransaction;
import org.spring.mongo.service.EntireUserDetailsOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "saveUser")
public class EntireUserDetailsOperationImpl implements EntireUserDetailsOperation {
    @Autowired
    private dbTransaction baseTransaction;
    @Autowired
    private User userDomain;


    @Override
    @Transactional("mongoTransactionManager")
    public Boolean saveUser(UserTo userTo) {
        if (!userTo.equals(null)) {
            userDomain.setUserName(userTo.getUserName());
            userDomain.setPassWord(userTo.getPassword());
            baseTransaction.saveObject(userDomain);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    @Transactional("mongoTransactionManager")
    public List<UserTo> loadUsers() {
          List<?> getList=baseTransaction.loadUser();
        if (!getList.isEmpty() && getList.size() >0) {
            List<UserTo> getAllUserList=getList.stream().map(this::apply).collect(Collectors.toList());
            return getAllUserList;
        }
        return null;
    }

    @Override
    public UserTo getSpecificDetails(Boolean isCharacter, Object value) {
      if(!isCharacter){
          Long id=Long.valueOf(String.valueOf(value));
      }else{
          String userName=String.valueOf(value);
      }
    }

    private UserTo apply(Object obj) {
        User user = (User) obj;
        UserTo userTo=new UserTo();
        userTo.setUserId(user.getId());
        userTo.setUserName(user.getUserName());
        userTo.setPassword(user.getPassword());
        return userTo;
    }
}
