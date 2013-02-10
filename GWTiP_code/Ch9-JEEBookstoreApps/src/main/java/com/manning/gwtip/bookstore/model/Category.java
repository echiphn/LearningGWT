package com.manning.gwtip.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries( {
    @NamedQuery(
    name = "Category.findAllCategories",
            query = "SELECT c FROM Category c ORDER BY c.name ASC")
})
public class Category extends AbstractModelBean {
    
    private String name;
    
    public Category() {
        super();
    }
    
    @Id
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
   
    
}
