package org.spring.mongo.repository;

import org.spring.mongo.db.entities.User;

import java.util.List;
import java.util.Map;

public interface dbTransaction {
    public Boolean saveObject(Object obj);
    public List<?> loadUser();
    public Object getAnySpecificClass(Class<?> cls, Map<Object,Object> parameters,Object primaryKey);
}
