package com.manning.gwtip.bookstore.test;

import com.manning.gwtip.bookstore.model.Author;
import com.manning.gwtip.bookstore.model.Book;
import com.manning.gwtip.bookstore.model.Category;
import java.util.ArrayList;
import java.util.List;


public class Util
{
    
    private static Book createBook(String title, List<Author> authors, String image)
    {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(authors);        
        book.setCategories(new ArrayList());
        Category a = new Category();
        a.setName("AJAX");
        Category b = new Category();
        b.setName("Java");
        book.getCategories().add(a);
        book.getCategories().add(b);
        book.setDescription("Book Description");
        book.setImage(image);
        return book;        
    }    
    
    public static Book createAjaxInAction()
    {
        String title = "AJAX in Action";
        
        Author a = new Author();
        a.setFirstName( "Dave");
        a.setLastName( "Crane");
        a.setBio( "Dave Crane has pushed the boundaries of DHTML," +
                " and latterly Ajax, on digital TV set-top boxes," +
                " in home automation and banking and financial systems. " +
                "He lives in Gloucestershire, UK.");
        
        Author b = new Author();
        b.setFirstName( "Eric" );
        b.setLastName("Pascarello");
        b.setBio( "Eric Pascarello is an ASP.NET developer and a" +
                " moderator of the HTML and JavaScript forum at" +
                " JavaRanch. He lives in Laurel, MD.");
        
        List<Author> authors = new ArrayList<Author>();
        authors.add(a);
        authors.add(b);
        
        return createBook(title, authors, "ajaxia.jpg");
    }
    
    public static Book createGwtInPractice()
    {       
        String title = "GWT in Practice";
        
        Author a = new Author();
        a.setFirstName( "Robert");
        a.setLastName( "Cooper");
        a.setBio( "Test");
        
        Author b = new Author();
        b.setFirstName( "Charlie");
        b.setLastName("Collins");
        b.setBio( "Test");
       
        List<Author> authors = new ArrayList<Author>();
        authors.add(a);
        authors.add(b);
        
        return createBook(title, authors, "gwtip.jpg");
    }
    
    public static Book createGwtInAction()
    {
        String title = "GWT in Action";
        
        Author a = new Author();
        a.setFirstName( "Robert");
        a.setLastName( "Hanson");
        a.setBio( "Test");
        
        Author b = new Author();
        b.setFirstName( "Adam");
        b.setLastName("Tacy");
        b.setBio( "Test");
        
        List<Author> authors = new ArrayList<Author>();
        authors.add(a);
        authors.add(b);
        
        return createBook(title, authors, "gwtia.jpg");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}