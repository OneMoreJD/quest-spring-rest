package com.wcs.springrestquest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;
    private String description;
    
    public Book() { }
    
    public Book(String aTitle, String aAuthor, String aDescription) {
        super();
        title = aTitle;
        author = aAuthor;
        description = aDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long aId) {
        id = aId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String aTitle) {
        title = aTitle;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String aAuthor) {
        author = aAuthor;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String aDescription) {
        description = aDescription;
    }
    
    
}
