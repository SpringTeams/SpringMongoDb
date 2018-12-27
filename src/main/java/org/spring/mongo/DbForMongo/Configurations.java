package org.spring.mongo.DbForMongo;

import org.hibernate.ogm.jpa.HibernateOgmPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableJpaRepositories(basePackages = {
        "org.spring.mongo.db.entities" }, entityManagerFactoryRef = "mongoEntityManager", transactionManagerRef = "mongoTransactionManager")
public class Configurations {
// EntityManager entityManager;
    @Bean(name = "mongoEntityManager")
    public LocalContainerEntityManagerFactoryBean mongoEntityManager() throws Throwable {

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("javax.persistence.transactionType", "resource_local");
        properties.put("hibernate.ogm.datastore.provider","org.hibernate.ogm.datastore.mongodb.impl.MongoDBDatastoreProvider");
        properties.put("hibernate.ogm.datastore.host","localhost");
        properties.put("hibernate.ogm.datastore.port","27017");
        properties.put("hibernate.ogm.datastore.database", "local");
        properties.put("hibernate.ogm.datastore.create_database", "true");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comments", "true");
        //hibernate.ogm.mongodb.connection_timeout
        properties.put("hibernate.use_sql_comments", "true");
       // properties.put("hibernate.ogm.datastore.grid_dialect", "org.hibernate.ogm.dialect.mongodb.MongoDBDialect");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setPackagesToScan("org.spring.mongo.db.entities");
        entityManager.setPersistenceUnitName("mongoPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        entityManager.setPersistenceProviderClass(HibernateOgmPersistence.class);
        return entityManager;
    }


    @Bean(name = "mongoTransactionManager")
    public PlatformTransactionManager transactionManager() throws Throwable {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mongoEntityManager().getObject());
        return transactionManager;
    }
}
