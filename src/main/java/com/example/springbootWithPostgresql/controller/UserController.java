package com.example.springbootWithPostgresql.controller;


import com.example.springbootWithPostgresql.entity.UserEntity;
import com.example.springbootWithPostgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAllUser(){
        List<UserEntity> users = userService.getAllUser();
        System.out.println("users : "+users);
        return users;
    }
    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable Long userId){
        UserEntity user = userService.getUserById(userId);
        System.out.println("userId : "+userId+" : user : "+user);
        return user;
    }

    @PostMapping
    public String saveUser(@RequestBody UserEntity user){
        userService.saveUser(user);
        return "user saved successfuly.";
    }

    @PutMapping("/{userId}")
    public String updateUser(@RequestBody UserEntity user, @PathVariable Long userId){
        userService.updateUser(user, userId);
        return "user updated successfully.";
    }

    @DeleteMapping("/{userId}")
    public String deleteUseryId(@PathVariable Long userId){
        userService.deleteUseryId(userId);
        return "user deleted successfully.";
    }

}
