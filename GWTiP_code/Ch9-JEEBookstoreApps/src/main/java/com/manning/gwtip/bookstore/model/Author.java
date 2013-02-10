package com.manning.gwtip.bookstore.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries( {
        @NamedQuery(
        name = "Author.findAuthorsByName", 
        query = "SELECT a FROM Author a " +
                "WHERE " +
                "    a.firstName LIKE :firstName " +
                "    AND a.lastName LIKE :lastName " +
                "ORDER BY a.lastName ASC, a.firstName ASC"),
        @NamedQuery(
        name = "Author.findAllAuthors", 
        query = "SELECT a FROM Author a " +
                "ORDER BY a.lastName ASC, a.firstName ASC")
        })
public class Author extends AbstractModelBean{
    
    private Integer id;
    private String firstName;
    private String lastName;
    private String bio;
    
    public Author() {
        super();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Lob
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    
    
}
