package com.manning.gwtip.bookstore.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.manning.gwtip.bookstore.client.model.Author;
import com.manning.gwtip.bookstore.client.model.Book;
import com.manning.gwtip.bookstore.client.model.Review;
import com.manning.gwtip.bookstore.client.remote.BookstoreRemoteException;
import com.manning.gwtip.bookstore.service.BookstoreService;

import com.totsp.gwt.beans.server.BeanMapping;

import org.springframework.beans.factory.BeanFactory;

import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;


public class BookstoreServiceServlet extends RemoteServiceServlet implements com.manning.gwtip.bookstore.client.remote.BookstoreService {
    private BookstoreService service;
    private Properties mappingProperties = new Properties();

    public BookstoreServiceServlet() {
        super();
        mappingProperties.setProperty(
                "com.manning.gwtip.bookstore.model.*",
                "com.manning.gwtip.bookstore.client.model.*");
       
    }

    public Review createReview(int bookId, Review review)
        throws BookstoreRemoteException {
        try {
            return (Review) BeanMapping.convert(mappingProperties,
                service.createReview(bookId,
                    (com.manning.gwtip.bookstore.model.Review) BeanMapping.convert(
                        mappingProperties, review)));
        } catch(Exception e) {
            throw new BookstoreRemoteException(e.toString());
        }
    }

    public void deleteBook(Book book) throws BookstoreRemoteException {
        try {
            service.deleteBook((com.manning.gwtip.bookstore.model.Book) BeanMapping.convert(
                    mappingProperties, book));
        } catch(Exception e) {
            throw new BookstoreRemoteException(e.toString());
        }
    }

    public List findAllBooks() throws BookstoreRemoteException {
        try {
            List serverBooks = service.findAllBooks();
            List clientBooks = new ArrayList();

            for(Object o : serverBooks) {
                clientBooks.add(BeanMapping.convert(mappingProperties, o));
            }

            return clientBooks;
        } catch(Exception e) {
            throw new BookstoreRemoteException(e.toString());
        }
    }

    public List findAllCategories() throws BookstoreRemoteException {
        try {
            List serverCategories = service.findAllCategories();
            List clientCategories = new ArrayList();

            for(Object o : serverCategories) {
                clientCategories.add(BeanMapping.convert(mappingProperties, o));
            }

            return clientCategories;
        } catch(Exception e) {
            throw new BookstoreRemoteException(e.toString());
        }
    }

    public List findAuthorsByName(String firstName, String lastName)
        throws BookstoreRemoteException {
        try {
            List serverAuthors = service.findAuthorsByName(firstName, lastName);
            List clientAuthors = new ArrayList();

            for(Object o : serverAuthors) {
                clientAuthors.add(BeanMapping.convert(mappingProperties, o));
            }

            return clientAuthors;
        } catch(Exception e) {
            throw new BookstoreRemoteException(e.toString());
        }
    }
    
    public List findAllAuthors()
        throws BookstoreRemoteException {
        try {
            List serverAuthors = service.findAllAuthors();
            List clientAuthors = new ArrayList();

            for(Object o : serverAuthors) {
                clientAuthors.add(BeanMapping.convert(mappingProperties, o));
            }

            return clientAuthors;
        } catch(Exception e) {
            throw new BookstoreRemoteException(e.toString());
        }
    }

    public Book findBookById(int bookId) throws BookstoreRemoteException {
        try {
            return (Book) BeanMapping.convert(mappingProperties,
                service.findBookById(bookId));
        } catch(Exception e) {
            throw new BookstoreRemoteException(e.toString());
        }
    }

    public List findBooksByAuthor(int authorId) throws BookstoreRemoteException {
        try {
            List serverBooks = service.findBooksByAuthor(authorId);
            List clientBooks = new ArrayList();

            for(Object o : serverBooks) {
                clientBooks.add(BeanMapping.convert(mappingProperties, o));
            }

            return clientBooks;
        } catch(Exception e) {
            throw new BookstoreRemoteException(e.toString());
        }
    }

    public List findBooksByCategory(String categoryName)
        throws BookstoreRemoteException {
        try {
            List serverBooks = service.findBooksByCategory(categoryName);
            List clientBooks = new ArrayList();

            for(Object o : serverBooks) {
                clientBooks.add(BeanMapping.convert(mappingProperties, o));
            }

            return clientBooks;
        } catch(Exception e) {
            throw new BookstoreRemoteException(e.toString());
        }
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        BeanFactory factory = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        this.service = (BookstoreService) factory.getBean("BookstoreService");
    }

    public void setService(BookstoreService service) {
        this.service = service;
    }

    public Author storeAuthor(Author author) throws BookstoreRemoteException {
        try {
            return (Author) BeanMapping.convert(mappingProperties,
                service.storeAuthor(
                    (com.manning.gwtip.bookstore.model.Author) BeanMapping.convert(
                        mappingProperties, author)));
        } catch(Exception e) {
            throw new BookstoreRemoteException(e.toString());
        }
    }

    public Book storeBook(Book book) throws BookstoreRemoteException {
        try {
            return (Book) BeanMapping.convert(mappingProperties,
                service.storeBook(
                    (com.manning.gwtip.bookstore.model.Book) BeanMapping.convert(
                        mappingProperties, book)));
        } catch(Exception e) {
            e.printStackTrace();
            throw new BookstoreRemoteException(e.toString());
        }
    }
}
