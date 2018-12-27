package org.spring.mongo.controllers;

import org.spring.mongo.modelTo.UserTo;
import org.spring.mongo.service.EntireUserDetailsOperation;
import org.spring.mongo.serviceImpl.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "spring/MongoDb")
public class BaseControllers {
    @Autowired
    private EntireUserDetailsOperation saveUser;
    @Autowired
    private UserTo userTo;
    @Autowired
    private BasicService basicService;

    @PostMapping(value = "/saveUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus saveUserObject(@RequestBody UserTo userTo){
       // userTo.setUserName("Rakesh");
        //userTo.setPassword("Vanitha06@6");
        if(saveUser.saveUser(userTo)){
            return HttpStatus.OK;
        }
        return HttpStatus.FORBIDDEN;
    }
    @GetMapping(value = "/getAllUsers",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserTo> getUsers(){
        return saveUser.loadUsers();
    }
    @GetMapping(value = "/getUser/{value}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserTo> getSpecificUser(@PathVariable String value){
        if(!value.isEmpty() && !value.equals(null)){
            basicService.isCharacter(value);
        }
    }

}
