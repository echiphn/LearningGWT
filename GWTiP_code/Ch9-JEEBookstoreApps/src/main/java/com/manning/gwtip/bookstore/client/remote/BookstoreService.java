package com.manning.gwtip.bookstore.client.remote;

import com.google.gwt.user.client.rpc.RemoteService;

import com.manning.gwtip.bookstore.client.model.Author;
import com.manning.gwtip.bookstore.client.model.Book;
import com.manning.gwtip.bookstore.client.model.Review;

import java.util.List;


public interface BookstoreService extends RemoteService {
    public Review createReview(int bookId, Review review)
        throws BookstoreRemoteException;

    public void deleteBook(Book book) throws BookstoreRemoteException;

    /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Book>
     */
    public List findAllBooks() throws BookstoreRemoteException;

    /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Category>
     */
    public List findAllCategories() throws BookstoreRemoteException;

     /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Author>
     */
    public List findAllAuthors() throws BookstoreRemoteException;
    /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Author>
     */
    public List findAuthorsByName(String firstName, String lastName)
        throws BookstoreRemoteException;

    public Book findBookById(int bookId) throws BookstoreRemoteException;

    /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Book>
     */
    public List findBooksByAuthor(int authorId) throws BookstoreRemoteException;

    public List findBooksByCategory(String categoryName)
        throws BookstoreRemoteException;

    public Author storeAuthor(Author author) throws BookstoreRemoteException;

    public Book storeBook(Book book) throws BookstoreRemoteException;
}
