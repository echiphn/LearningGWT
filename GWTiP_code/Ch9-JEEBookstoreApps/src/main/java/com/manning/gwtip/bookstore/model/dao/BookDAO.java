package com.manning.gwtip.bookstore.model.dao;

import com.manning.gwtip.bookstore.model.Book;
import com.manning.gwtip.bookstore.model.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class BookDAO extends AbstractDAO {
    
    public BookDAO() {
        super();
    }
    
    public Book store(Book book){
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            
            for( int i=0; book.getCategories() != null && i < book.getCategories().size(); i++ ){
                try{
                    book.getCategories().set( i, 
                        em.merge(book.getCategories().get(i)) 
                        );
                } catch(Exception e){
                        e.printStackTrace();
                        em.persist( book.getCategories().get(i) );
                }
            }
            
            if( book.getId() == null ){
                em.persist( book );
            } else {
                book = em.merge(book);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return book;
    }
    
    public Book findBookById(int bookId){
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("Book.findBookById");
            q.setParameter("id", bookId );
            return (Book) q.getSingleResult();
        } catch( NoResultException e){
            return null;
        } finally {
            if (em != null) {
                em.getTransaction().commit();
                em.close();
            }
        }
        
    }
    
    
    
    public List<Book> findBooksByAuthor(int authorId){
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("Book.findBooksByAuthor");
            q.setParameter("id", authorId);
            return q.getResultList();
            
        } finally {
            if (em != null) {
                em.getTransaction().commit();
                em.close();
            }
        }
        
    }
    
     public List<Book> findAllBooks(){
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("Book.findAllBooks");
            return q.getResultList();
            
        } finally {
            if (em != null) {
                em.getTransaction().commit();
                em.close();
            }
        }
        
    }
     
    public List<Book> findBooksByCategory(String categoryName){
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("Book.findBooksByCategory");
            q.setParameter("name", categoryName);
            return q.getResultList();
            
        } finally {
            if (em != null) {
                em.getTransaction().commit();
                em.close();
            }
        }
    }
    
    public void delete( Book book ){
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            book.setCategories( null );
            book = em.merge( book );
            em.remove( book );
            
            
        } finally {
            if (em != null) {
                em.getTransaction().commit();
                em.close();
            }
        }
        
    }
    
}
