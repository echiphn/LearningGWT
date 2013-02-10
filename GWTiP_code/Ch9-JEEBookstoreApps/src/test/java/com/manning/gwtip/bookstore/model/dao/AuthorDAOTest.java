package com.manning.gwtip.bookstore.model.dao;

import com.manning.gwtip.bookstore.AbstractTestDAO;
import com.manning.gwtip.bookstore.model.Author;
import java.util.List;
import javax.transaction.UserTransaction;

public class AuthorDAOTest extends AbstractTestDAO {
    
    public AuthorDAOTest(String testName) {
        super(testName);
    }

    public void testCreateAuthor() {
        System.out.println("createAuthor");
        
        AuthorDAO instance = new AuthorDAO();
        instance.setEntityManagerFactory( this.getEntityManagerFactory() );
        Author a = new Author();
        a.setFirstName( "Robert");
        a.setLastName("Cooper");
        a.setBio("Test");
        Author result = instance.store(a);
        assertEquals(a, result);
        
    }

    public void testFindAuthorsByName() throws Exception {
        System.out.println("findAuthorsByName");
       
        
        AuthorDAO instance = new AuthorDAO();
        instance.setEntityManagerFactory( this.getEntityManagerFactory() );
         Author a = new Author();
        a.setFirstName( "Charlie");
        a.setLastName("Collins");
        a.setBio("Test");
        instance.store( a );
        
        
        List<Author> result = instance.findAuthorsByName("C", "Collins");
        System.out.println( "Results Found: "+ result.size() );
        assertEquals("Charlie", result.get(0).getFirstName() );
       
        
    }
    
}
