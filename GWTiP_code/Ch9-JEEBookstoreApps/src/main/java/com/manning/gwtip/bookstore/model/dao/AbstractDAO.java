package com.manning.gwtip.bookstore.model.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class AbstractDAO {
    
    @PersistenceUnit
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
            "bookstore");
    
    public AbstractDAO() {
        super();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
       // this.entityManagerFactory = entityManagerFactory;
    }

    
    
}
