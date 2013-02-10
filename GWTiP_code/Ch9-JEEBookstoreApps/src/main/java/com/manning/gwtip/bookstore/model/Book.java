package com.manning.gwtip.bookstore.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries( {
        @NamedQuery(
        name = "Book.findBookById", 
        query = "SELECT b FROM Book b " +
                "WHERE " +
                "    b.id = :id"),
        @NamedQuery(
        name = "Book.findBooksByCategory", 
        query = "SELECT b FROM Book b, IN(b.categories) c " +
                "WHERE " +
                "    c.name = :name"),
        @NamedQuery(
        name = "Book.findBooksByAuthor", 
        query = "SELECT b FROM Book b, IN(b.authors) a " +
                "WHERE " +
                "    a.id = :id"),
        @NamedQuery(
        name = "Book.findAllBooks", 
        query = "SELECT b FROM Book b ORDER BY b.title")
        })
public class Book extends AbstractModelBean{
    
    private Integer id;
    private String title;
    private List<Author> authors;
    private String description;
    private String image;
    private List<Review> reviews;
    private List<Category> categories;
    
    public Book() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
    
    public float calculateRating(){
        float total = 0;
        for( Review r : this.getReviews() ){
            total += (float) r.getRating();
        }
        return this.getReviews().isEmpty() ? 0 :
            (float) Math.round( 
                total * 10f / (float) this.getReviews().size() ) 
                / 10f; 
    }

    @ManyToMany(cascade=CascadeType.ALL)
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
