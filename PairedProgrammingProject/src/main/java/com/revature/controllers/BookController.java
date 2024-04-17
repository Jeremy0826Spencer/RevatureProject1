package com.revature.controllers;

import com.revature.daos.BookDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Book;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController{

    private BookDAO bookDAO;
    private UserDAO userDAO;

    @Autowired
    public BookController(BookDAO bookDAO, UserDAO userDAO){
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){

        List<Book> books = bookDAO.findAll();

        return ResponseEntity.ok(books);

    }

    @PostMapping("/{userId}")
    public ResponseEntity<Book> insertBook(@RequestBody Book book, @PathVariable int userId){

        User u = userDAO.findById(userId).get();

        book.setUser(u);

        Book b = bookDAO.save(book);

        return ResponseEntity.ok(b);

    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable int bookId){
        Book b = bookDAO.findById(bookId).get();

        if(b == null){

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(b);

    }

    @GetMapping("/user_books/{userId}")
    public ResponseEntity<List<Book>> getAllUserBooks(@PathVariable int userId){

        User u = userDAO.findById(userId).get();

        List<Book> bookList = bookDAO.findAllByUser(u);

        return ResponseEntity.ok(bookList);


    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Object> deleteBook(@PathVariable int bookId){
        Optional<Book> b = bookDAO.findById(bookId);

        if(b.isEmpty()){
            return ResponseEntity.status(404).body("No book found with book id of: " + bookId);
        }

        Book bookToDelete = b.get();

        bookDAO.deleteById(bookId);

        return ResponseEntity.accepted().body("Book " + bookToDelete.getBookName() + " has been deleted");

    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<Object> updateBookDescription(@RequestBody String bookDescription, @PathVariable int bookId){
        Optional<Book> b = bookDAO.findById(bookId);

        if(b.isEmpty()){
            return ResponseEntity.status(404).body("No book found with book id of: " + bookId);
        }

        Book book = b.get();

        book.setDescription(bookDescription);

        bookDAO.save(book);

        return ResponseEntity.accepted().body(book);

    }

}
