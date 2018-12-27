package org.spring.mongo.repositoryImpl;

import org.hibernate.mapping.Collection;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.spring.mongo.db.entities.User;
import org.spring.mongo.repository.dbTransaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Session;

@Repository(value = "baseTransaction")
public class dbTransactionImpl implements dbTransaction {
    @PersistenceContext(unitName = "mongoEntityManager")
    EntityManager mongoEntityManager;


    @Override
    public Boolean saveObject(Object obj) {
        if(!obj.equals(null)){
         getHibernateSession().save(obj);
         getHibernateSession().flush();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<?> loadUser() {
      //  Session session = (Session) mongoEntityManager.getDelegate();
        /*FullTextEntityManager ftem = Search.getFullTextEntityManager(mongoEntityManager);
        QueryBuilder queryBuilder= ftem.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();
        org.apache.lucene.search.Query query=queryBuilder.all().createQuery();
        FullTextQuery ftQuery = ftem.createFullTextQuery(query,User.class);*/
        List<User> list=getAllUser().stream().map(this::setUser).collect(Collectors.toList());
        System.out.println(list);
        //return mongoEntityManager.createQuery(" from User user",Object.class).getResultList();
        if(!list.isEmpty() && list.size() >0) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public Object getAnySpecificClass(Class<?> cls, Map<Object, Object> parameters,Object primaryKey) {
        Object finalObj=null;
        if(cls != null ){
         if(!primaryKey.equals(null)){
             finalObj=getHibernateSession().load(cls,(Serializable) primaryKey);
         }/*else if(!parameters.isEmpty() && parameters !=null){
             Query query=getHibernateSession().createQuery("");
             Stream.of(parameters).forEach(setValue->{
                 setValue.get
             });
         }*/
        }else{
            try {
                throw new Exception("Class Not Found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return finalObj;
    }

    private User setUser(Object user){
        return (user instanceof User) ? (User)user:null;
    }
    private List<Object> getAllUser(){
      Query query =getHibernateSession().createQuery("select u from User u ",User.class);
      return query.getResultList();
    }
    public Session getHibernateSession(){
        return (Session)mongoEntityManager.getDelegate();
    }
}
