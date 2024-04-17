package com.revature.controllers;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> users = userDAO.findAll();

        return ResponseEntity.ok(users);

    }

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user){

        User u = userDAO.save(user);

        if(u == null){
            return  ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(u);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int userId){

        User u = userDAO.findById(userId).get();

        u.setUserName(user.getUserName());
        u.setPassword(user.getPassword());

        userDAO.save(u);

        return ResponseEntity.accepted().body(u);

    }

}
