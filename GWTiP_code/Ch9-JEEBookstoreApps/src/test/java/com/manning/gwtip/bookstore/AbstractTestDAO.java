package com.manning.gwtip.bookstore;

import com.manning.gwtip.bookstore.model.dao.AuthorDAO;
import com.manning.gwtip.bookstore.model.dao.BookDAO;
import com.manning.gwtip.bookstore.model.dao.CategoryDAO;

import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AbstractTestDAO extends TestCase {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(
            "bookstore");
    protected AuthorDAO authorDao;
    protected BookDAO bookDao;
    protected CategoryDAO categoryDao;
    Connection conn;

    public AbstractTestDAO(String testName) {
        super(testName);

        EntityManagerFactory factory = this.getEntityManagerFactory();
        this.authorDao = new AuthorDAO();
        this.authorDao.setEntityManagerFactory(factory);
        this.bookDao = new BookDAO();
        this.bookDao.setEntityManagerFactory(factory);
        this.categoryDao = new CategoryDAO();
        this.categoryDao.setEntityManagerFactory(factory);
    }

    protected EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }

    public void testSomething()
    {    
    }
}
