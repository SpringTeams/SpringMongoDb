package org.spring.mongo.db.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.hibernate.annotations.GenericGenerator;
import org.spring.mongo.DbForMongo.SequenceGeneratorContants;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Component(value = "userDomain")
/*@JsonSerialize(using = EntitySerializer.class)*/
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =SequenceGeneratorContants.UserDomain.US_GENERATOR)
    @SequenceGenerator(name=SequenceGeneratorContants.UserDomain.US_GENERATOR,
            sequenceName = SequenceGeneratorContants.UserDomain.US_SEQ_NAME)



    private Long id;

    private String userName;
    private String passWord;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
/*
    public String getId() {
        return id.toString();
    }
    public void setId(String id) {
        this.id = new ObjectId(id);
    }*/

/*    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;//new ObjectId(_id.toString()).toHexString();
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User() {

    }


    /*public User(String userName, String passWord,String id) {
        this.userName = userName;
        this.passWord = passWord;
        this.id = new ObjectId(id);
    }*/


}

