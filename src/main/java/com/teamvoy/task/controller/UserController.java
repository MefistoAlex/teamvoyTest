package com.teamvoy.task.controller;

import com.teamvoy.task.entity.UserEntity;
import com.teamvoy.task.exception.UserAlreadyExistException;
import com.teamvoy.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {

           userService.registration(user);
            return ResponseEntity.ok().body("User added successfully");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}


