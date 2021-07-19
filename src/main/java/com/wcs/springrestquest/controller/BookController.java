package com.wcs.springrestquest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wcs.springrestquest.entity.Book;
import com.wcs.springrestquest.repository.BookRepository;

@Controller
public class BookController {

    @Autowired
    BookRepository repo;
    
    @GetMapping("/")
    @ResponseBody
    public List<Book> getBooks() {
        return repo.findAll();
    }
    
    @PostMapping("/create")
    @ResponseBody
    public Book createBook(@RequestBody Book body) {
        Book newBook = repo.save(body);
        return newBook;
    }
    
    @PutMapping("/update/{id}")
    @ResponseBody
    public Book updateBook(@PathVariable("id") long id, @RequestBody Book body) {
        Optional<Book> optionalBook = repo.findById(id);

        if (optionalBook.isPresent()) {
          Book book = optionalBook.get();
          book.setTitle(body.getTitle());
          book.setAuthor(body.getAuthor());
          book.setDescription(body.getDescription());
          return repo.save(book);
        } else {
          return null;
        }
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Long> deleteBook(@PathVariable long id) {
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/search")
    @ResponseBody
    public List<Book> search(@RequestBody Map<String, String> body) {
        String criteria = body.get("text");
        return repo.findByTitleContainingOrDescriptionContaining(criteria, criteria);
    }
}
