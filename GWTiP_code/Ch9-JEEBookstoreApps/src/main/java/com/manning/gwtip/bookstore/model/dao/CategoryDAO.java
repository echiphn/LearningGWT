/*
 * CategoryDAO.java
 *
 * Created on January 16, 2007, 10:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.manning.gwtip.bookstore.model.dao;

import com.manning.gwtip.bookstore.model.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author cooper
 */
public class CategoryDAO extends AbstractDAO  {
    
    
    /** Creates a new instance of CategoryDAO */
    public CategoryDAO() {
        super();
    }
    
    
    public List<Category> findAllCategories(){
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        try {
             em.getTransaction().begin();
             Query q = em.createNamedQuery("Category.findAllCategories");
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
