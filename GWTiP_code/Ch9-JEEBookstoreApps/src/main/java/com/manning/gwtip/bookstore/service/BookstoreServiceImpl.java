package com.manning.gwtip.bookstore.service;

import com.manning.gwtip.bookstore.model.Author;
import com.manning.gwtip.bookstore.model.Book;
import com.manning.gwtip.bookstore.model.Category;
import com.manning.gwtip.bookstore.model.Review;
import com.manning.gwtip.bookstore.model.dao.AuthorDAO;
import com.manning.gwtip.bookstore.model.dao.BookDAO;
import com.manning.gwtip.bookstore.model.dao.CategoryDAO;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookstoreServiceImpl implements BookstoreService {
    private static final Logger LOG = Logger.getLogger( BookstoreServiceImpl.class.getName());
    private AuthorDAO authorDao;
    private BookDAO bookDao;
    private CategoryDAO categoryDao;
    

    public BookstoreServiceImpl(
        AuthorDAO author, BookDAO book, CategoryDAO category) {
        super();
        this.authorDao = author;
        this.bookDao = book;
        this.categoryDao = category;
    }

    public BookstoreServiceImpl() {
        super();
    }

    public Author storeAuthor(Author author) throws BookstoreServiceException {
        try{
            return authorDao.store( author );
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion creating author", e);
            throw new BookstoreServiceException( "Exception creating author", e);
        }
    }

    public Book storeBook(Book book)  throws BookstoreServiceException{
        try{
            return bookDao.store(book);
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion creating book", e);
            throw new BookstoreServiceException( "Exception creating book", e);
        }
    }

    
    public Review createReview(int bookId, Review review) throws BookstoreServiceException{
        try{
            Book b =bookDao.findBookById( bookId );
            if( b == null){
                throw new BookstoreServiceException("Book not found");
            }
            List<Review> reviews = b.getReviews() == null ?
                new ArrayList<Review>() :
                b.getReviews();
            reviews.add( review );
            bookDao.store(b);
            return review;
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion creating book", e);
            throw new BookstoreServiceException( "Exception creating book", e);
        }
    }

    public List<Category> findAllCategories() throws BookstoreServiceException {
        try{
            return categoryDao.findAllCategories();
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion getting categories", e);
            throw new BookstoreServiceException( "Exception getting categories", e);
        }
    }

    public List<Author> findAuthorsByName(String firstName, String lastName) throws BookstoreServiceException {
        try{
            return authorDao.findAuthorsByName(firstName, lastName );
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion getting categories", e);
            throw new BookstoreServiceException( "Exception getting categories", e);
        }
    }

    public Book findBookById(int bookId) throws BookstoreServiceException {
        try{
            return bookDao.findBookById( bookId );
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion getting book", e);
            throw new BookstoreServiceException( "Exception getting book", e);
        }
    }

    public List<Book> findBooksByAuthor(int authorId) throws BookstoreServiceException {
        try{
            return bookDao.findBooksByAuthor( authorId );
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion getting book by category", e);
            throw new BookstoreServiceException( "Exception getting book by category", e);
        }
    }

    
    public List<Book> findAllBooks() throws BookstoreServiceException {
        try{
            return bookDao.findAllBooks();
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion getting book by category", e);
            throw new BookstoreServiceException( "Exception getting book by category", e);
        }
    }
    
    public List findBooksByCategory(String categoryName) throws BookstoreServiceException {
        try{
            return bookDao.findBooksByCategory( categoryName);
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion getting book by category by category", e);
            throw new BookstoreServiceException( "Exception getting book by category", e);
        }
    }

    public void deleteBook(Book book) throws BookstoreServiceException {
         try{
             bookDao.delete(book);
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion deleting book", e);
            throw new BookstoreServiceException( "Exception creating book", e);
        }
    }

    public List<Author> findAllAuthors() throws BookstoreServiceException {
        try{
            return authorDao.findAllAuthors();
        } catch(Exception e){
            LOG.log( Level.WARNING, "Excetion getting authors", e);
            throw new BookstoreServiceException( "Exception getting authors", e);
        }
    }
}
