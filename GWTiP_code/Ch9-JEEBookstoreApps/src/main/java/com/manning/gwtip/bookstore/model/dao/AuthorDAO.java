package com.manning.gwtip.bookstore.model.dao;

import com.manning.gwtip.bookstore.model.Author;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AuthorDAO extends AbstractDAO{
    
     
    public AuthorDAO() {
        super();
    }

    public Author store( Author author ){
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            if( author.getId() == null ){
                em.persist( author );
            } else {
                author = em.merge(author);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
                
            }
        }
        return author;
    }
    
    public List<Author> findAuthorsByName( String firstName, String lastName ){
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        try {
             em.getTransaction().begin();
             Query q = em.createNamedQuery("Author.findAuthorsByName");
             q.setParameter("firstName", (firstName == null ? "": firstName )+"%");
             q.setParameter("lastName", (lastName == null ? "": lastName )+"%");
             return q.getResultList();
             
        }
        finally {
            if (em != null) {
                em.getTransaction().commit();
                em.close();
            }
        }
    }
   public List<Author> findAllAuthors( ){
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        try {
             em.getTransaction().begin();
             Query q = em.createNamedQuery("Author.findAllAuthors");
             return q.getResultList();
             
        }
        finally {
            if (em != null) {
                em.getTransaction().commit();
                em.close();
            }
        }
    }
   
  
}
