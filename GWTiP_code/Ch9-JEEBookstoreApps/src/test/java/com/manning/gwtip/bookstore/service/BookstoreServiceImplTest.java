/*
 * BookstoreServiceImplTest.java
 * JUnit based test
 *
 * Created on January 31, 2007, 6:26 PM
 */

package com.manning.gwtip.bookstore.service;

import com.manning.gwtip.bookstore.AbstractTestDAO;
import com.manning.gwtip.bookstore.model.Author;
import com.manning.gwtip.bookstore.model.Book;
import com.manning.gwtip.bookstore.model.Category;
import com.manning.gwtip.bookstore.model.Review;
import com.manning.gwtip.bookstore.model.dao.AuthorDAO;
import com.manning.gwtip.bookstore.model.dao.BookDAO;
import com.manning.gwtip.bookstore.model.dao.CategoryDAO;
import com.manning.gwtip.bookstore.test.Util;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cooper
 */
public class BookstoreServiceImplTest extends AbstractTestDAO {
    
   
    
    public BookstoreServiceImplTest(String testName) {
        super(testName);
        
    }

    public void testStoreAuthor() throws Exception {
        System.out.println("storeAuthor");
        
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName( "Doe");
        
        BookstoreServiceImpl instance =
                new BookstoreServiceImpl(authorDao, bookDao, categoryDao);
        
        Author result = instance.storeAuthor(author);
        System.out.println( result );
        
        Author change = new Author();
        change.setId( author.getId() );
        change.setFirstName( "Jane");
        change.setLastName( "Doe" );
        Author changeResult = instance.storeAuthor( change );
        System.out.println( changeResult );
       
    }

    public void testStoreBook() throws Exception {
        
        Book bookA = Util.createAjaxInAction();
        Book bookB = Util.createGwtInAction();
        Book bookC = Util.createGwtInPractice();        
        
        BookstoreServiceImpl instance = 
                new BookstoreServiceImpl(authorDao, bookDao, categoryDao);        
        
        Book resultA = instance.storeBook(bookA);
        Book resultB = instance.storeBook(bookB);
        Book resultC = instance.storeBook(bookC);        
    }

    public void xtestCreateReview() throws Exception {
        System.out.println("createReview");
        
        int bookId = 0;
        Review review = null;
        BookstoreServiceImpl instance =
                new BookstoreServiceImpl(authorDao, bookDao, categoryDao);
        
        Review expResult = null;
        Review result = instance.createReview(bookId, review);
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    public void testFindAllCategories() throws Exception {
        System.out.println("findAllCategories");
        
        BookstoreServiceImpl instance =
                new BookstoreServiceImpl(authorDao, bookDao, categoryDao);
        
        List<Category> result = instance.findAllCategories();
        this.assertEquals( "AJAX", result.get(0).getName() );
        this.assertEquals( "Java", result.get(1).getName() );
        
    }

    public void xtestFindAuthorsByName() throws Exception {
        System.out.println("findAuthorsByName");
        
        String firstName = "";
        String lastName = "";
        BookstoreServiceImpl instance = new BookstoreServiceImpl();
        
        List<Author> expResult = null;
        List<Author> result = instance.findAuthorsByName(firstName, lastName);
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    public void xtestFindBookById() throws Exception {
        System.out.println("findBookById");
        
        int bookId = 0;
        BookstoreServiceImpl instance = new BookstoreServiceImpl();
        
        Book expResult = null;
        Book result = instance.findBookById(bookId);
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    public void xtestFindBooksByAuthor() throws Exception {
        System.out.println("findBooksByAuthor");
        
        int authorId = 0;
        BookstoreServiceImpl instance = new BookstoreServiceImpl();
        
        List<Book> expResult = null;
        List<Book> result = instance.findBooksByAuthor(authorId);
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    public void xtestFindBooksByCategory() throws Exception {
        System.out.println("findBooksByCategory");
        
        String categoryName = "";
        BookstoreServiceImpl instance = new BookstoreServiceImpl();
        
        List expResult = null;
        List result = instance.findBooksByCategory(categoryName);
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }
    
}
