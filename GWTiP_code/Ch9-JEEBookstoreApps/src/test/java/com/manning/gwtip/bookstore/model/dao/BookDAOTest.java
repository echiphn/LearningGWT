/*
 * BookDAOTest.java
 * JUnit based test
 *
 * Created on January 20, 2007, 6:43 PM
 */

package com.manning.gwtip.bookstore.model.dao;

import com.manning.gwtip.bookstore.AbstractTestDAO;
import com.manning.gwtip.bookstore.model.Author;
import com.manning.gwtip.bookstore.test.Util;
import junit.framework.*;
import com.manning.gwtip.bookstore.model.Book;
import com.manning.gwtip.bookstore.model.Category;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cooper
 */
public class BookDAOTest extends AbstractTestDAO {
    
    public BookDAOTest(String testName) {
        super(testName);
    }

    public void testCreateBook() {
        System.out.println("createBook");
        
        BookDAO instance = new BookDAO();
        instance.setEntityManagerFactory( this.getEntityManagerFactory() );
        
        Book bookA = Util.createAjaxInAction();
        Book bookB = Util.createGwtInAction();
        Book bookC = Util.createGwtInPractice();
        
        Book resultA = instance.store(bookA);
        assertEquals(bookA.getTitle(), resultA.getTitle()); 
        
        Book resultB = instance.store(bookB);
        assertEquals(bookB.getTitle(), resultB.getTitle());   
        
        Book resultC = instance.store(bookC);
        assertEquals(bookC.getTitle(), resultC.getTitle());                
    }

    public void testFindBookById() {
        System.out.println("findBookById");
        
        int bookId = 1;
        BookDAO instance = new BookDAO();
        instance.setEntityManagerFactory( this.getEntityManagerFactory() );
        
        Book expResult = null;
        Book result = instance.findBookById(bookId);
    }
    
    public void testFindBooksByCategory() {
        System.out.println("findBooksByCategory");
        
        String categoryName = "Java";
        BookDAO instance = new BookDAO();
        instance.setEntityManagerFactory( this.getEntityManagerFactory() );
        
        List<Book> result = instance.findBooksByCategory(categoryName);
        Assert.assertNotNull(result);
    }
    
}
