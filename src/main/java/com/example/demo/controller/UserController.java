package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    //Add User
    @PostMapping("api/users")
    public ResponseEntity<User> insertUser(@RequestBody User user){
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
    }

    //Get All Users
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userRepo.findAll(),HttpStatus.OK);
    }

    //Get A User
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()){
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Update A User
    @PutMapping("api/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user){
        Optional<User> user1 = userRepo.findById(id);
        if (user1.isPresent()){
            user1.get().setName(user.getName());
            return  new ResponseEntity<>(userRepo.save(user1.get()),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //Delete A User
    @DeleteMapping ("api/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()){
            userRepo.deleteById(id);
            return  new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



}
