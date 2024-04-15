package com.revature.controllers;

import com.revature.daos.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController{

    private BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }


}
